package com.itwill.myleaves.web.sellbuy;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	@GetMapping("/buy/list")
	public void read(Model model) {
//		log.info("read()");
		List<Sell> list = sellService.read();
		
		Map<Long, String> thumbnails = new HashMap<>();
        for(Sell sell: list){
        	thumbnails.put(sell.getSellId(), Base64.getEncoder().encodeToString(sell.getThumbnail()));
        }
        model.addAttribute("images", thumbnails);
		model.addAttribute("sells", list);
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