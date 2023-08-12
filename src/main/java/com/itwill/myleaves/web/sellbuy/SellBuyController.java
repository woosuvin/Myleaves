package com.itwill.myleaves.web.sellbuy;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.itwill.myleaves.dto.sellbuy.SellCreateDto;
import com.itwill.myleaves.dto.sellbuy.SellUpdateDto;
import com.itwill.myleaves.repository.sellbuy.BuyWish;
import com.itwill.myleaves.repository.sellbuy.Sell;
import com.itwill.myleaves.service.mypage.MypageSellBuyService;
import com.itwill.myleaves.service.sellbuy.SellService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SellBuyController {

	private final SellService sellService;
	private final MypageSellBuyService mypageService;
	
	/**
	 * 입양 메뉴 리스트
	 * @param model
	 */
//	@GetMapping("/buy/list")
//	public void read(Model model) {
////		log.info("read()");
//		List<Sell> list = sellService.read();
//		
//		Map<Long, String> thumbnails = new HashMap<>();
//        for(Sell sell: list){
//        	thumbnails.put(sell.getSellId(), Base64.getEncoder().encodeToString(sell.getThumbnail()));
//        }
//      model.addAttribute("images", thumbnails);
//		model.addAttribute("sells", list);
//	}
	
	@GetMapping("/buy/list")
	public void read(Model model, @PageableDefault(page = 0, size = 8) Pageable pageable) {
		Page<Sell> list = sellService.readWPaging(pageable);
		model.addAttribute("sells", list);
		
		Map<Long, String> thumbnails = new HashMap<>();
		for(Sell sell: list){
			thumbnails.put(sell.getSellId(), Base64.getEncoder().encodeToString(sell.getThumbnail()));
		}
		model.addAttribute("images", thumbnails);

		int totalPage = list.getTotalPages()-1;
		int nowPage = list.getPageable().getPageNumber()+1; //지금 페이지 0 + 1 => 1 페이지부터 시작
		int startPage = Math.max(nowPage-4, 1);
		int endPage = Math.min(nowPage+5, list.getTotalPages());
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
	}

	
	@PreAuthorize("hasRole('MEMBER')")
	@GetMapping("/sell/create")
	public void create() {
//		log.info("create() GET");
	}

	@PreAuthorize("hasRole('MEMBER')")
	@PostMapping("/sell/create")
	public String create(SellCreateDto dto) throws IOException {
//		log.info("create(dto={}) POST", dto);

		dto.setThumbnail(dto.getFile().getBytes());
		
		sellService.create(dto);
		return "redirect:/buy/list";
	}
	
	/**
	 * 
	 * @param sellId
	 * @param model
	 */
	@PreAuthorize("hasRole('MEMBER')")
	@GetMapping("/buy/detail")
	public void readDetail(String userId, long sellId, Model model) {
//		log.info("read(sellId={}", sellId);
		
		List<BuyWish> buyWishlist = mypageService.readBuyWish(sellId);
//		log.info("{}", buyWishlist);
		
		Boolean result = null;
		for (BuyWish x : buyWishlist) {
		    if (x.getUserId().equals(userId)) {
		        result = true;
		        break;
		    } else {
		        result = false;
		    }
		}
		
		Sell sell = sellService.read(sellId);
		String image = Base64.getEncoder().encodeToString(sell.getThumbnail());
		
		model.addAttribute("image", image);
		model.addAttribute("wish", result);
		model.addAttribute("sell", sell);
	}
	
	@PreAuthorize("hasRole('MEMBER')")
	@GetMapping("sell/modify")
	public void read(Long sellId, Model model) throws IOException {
//		log.info("read(sellId={})", sellId);

		Sell sell = sellService.read(sellId);
		String image = Base64.getEncoder().encodeToString(sell.getThumbnail());
		
		model.addAttribute("image", image);
		model.addAttribute("sell", sell);
	}

	@PreAuthorize("hasRole('MEMBER')")
	@PostMapping("/sell/update")
	public String update(SellUpdateDto dto) throws IOException {
//		log.info("update(dto={})", dto);
//		log.info(dto.getFile().getBytes()[0] + ", " + dto.getFile().getBytes()[1]);
		
		sellService.update(dto);
		return "redirect:/buy/detail?sellId=" + dto.getSellId();
	}

	@PreAuthorize("hasRole('MEMBER')")
	@PostMapping("/sell/delete")
	public String delete(long sellId) {
//		log.info("delete(sellId={})", sellId);
		sellService.delete(sellId);
//		log.info("삭제 결과={}", sellId);
		return "redirect:/buy/list";
	}
	
}