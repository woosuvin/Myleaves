package com.itwill.myleaves.web.sellbuy;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.itwill.myleaves.dto.sellbuy.SellCreateDto;
import com.itwill.myleaves.dto.sellbuy.SellUpdateDto;
import com.itwill.myleaves.repository.sellbuy.BuyWish;
import com.itwill.myleaves.repository.sellbuy.BuyWishRepository;
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
		log.info("read()");
		List<Sell> list = sellService.read();
		
//		Map<Long, String> productBase64Images = new HashMap<>();
//        for(Sell sell: list){               
//            productBase64Images.put(sell.getSellId(), Base64.getEncoder().encodeToString(sell.getThumbnail()));
//        }
//        model.addAttribute("images", productBase64Images);
		model.addAttribute("sells", list);
	}

//	@GetMapping("/display/image/{sellId}")
//    public ResponseEntity<byte[]> displayItemImage(@PathVariable int sellId) {
//    	Sell sell = sellService.read(sellId);
//        byte[] image = sell.getThumbnail();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_JPEG);
//        return new ResponseEntity<>(image, headers, HttpStatus.OK);
//    }
	
	@GetMapping("/sell/create")
	public void create() {
		log.info("create() GET");
	}

	@PostMapping("/sell/create")
	public String create(SellCreateDto dto) {
		log.info("create(dto={}) POST", dto);

//		MultipartFile file = dto.getFile();
//
//		if (file != null && !file.isEmpty()) {
//			byte[] bytes;
//			try {
//				bytes = file.getBytes();
//				dto.setThumbnail(bytes);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//		}

		sellService.create(dto);
		return "redirect:/buy/list";
	}
	
	/**
	 * 
	 * @param sellId
	 * @param model
	 */
	@GetMapping("/buy/detail")
	public void readDetail(String userId, Long sellId, Model model) {
		log.info("read(sellId={}", sellId);
		
		List<BuyWish> buyWishlist = mypageService.readBuyWish(sellId);
		log.info("{}", buyWishlist);
		
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
		
		model.addAttribute("wish", result);
		model.addAttribute("sell", sell);
	}
	
	@GetMapping("sell/modify")
	public void read(Long sellId, Model model) {
		log.info("read(sellId={})", sellId);

		Sell sell = sellService.read(sellId);
		
		model.addAttribute("sell", sell);
	}

	@PostMapping("/sell/update")
	public String update(SellUpdateDto dto) {
		log.info("update(dto={})", dto);

		MultipartFile file = dto.getFile();

		if (file != null && !file.isEmpty()) {
			byte[] bytes;
			try {
				bytes = file.getBytes();
				dto.setThumbnail(bytes);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		sellService.update(dto);
		return "redirect:/buy/detail?sellId=" + dto.getSellId();
	}

	@PostMapping("/sell/delete")
	public String delete(long sellId) {
		log.info("delete(sellId={})", sellId);
		sellService.delete(sellId);
		log.info("삭제 결과={}", sellId);
		return "redirect:/buy/list";
	}

}
