package com.itwill.myleaves.web.mypage;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.myleaves.repository.sellbuy.Sell;
import com.itwill.myleaves.repository.sellbuy.BuyWish;
import com.itwill.myleaves.service.mypage.MypageSellBuyService;
import com.itwill.myleaves.service.sellbuy.SellService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/sellbuy")
public class MyPageSellBuyController {
	
	private final SellService sellService;
	private final MypageSellBuyService mypageService;

	@GetMapping("/buyWish")
	public void read(BuyWish wishList, Model model) {
		log.info("read()");
		
		List<BuyWish> list = mypageService.read(wishList);
		
//		Map<Long, String> productBase64Images = new HashMap<>();
//        for(Sell sell: list){               
//            productBase64Images.put(sell.getSellId(), Base64.getEncoder().encodeToString(sell.getThumbnail()));
//        }
//        model.addAttribute("images", productBase64Images);
		model.addAttribute("wishSell", list);
	}
	
	@GetMapping("/sellList")
	public void sellList(Model model) {
		log.info("read()");
		List<Sell> list = sellService.read();
		
		Map<Long, String> productBase64Images = new HashMap<>();
        for(Sell sell: list){               
            productBase64Images.put(sell.getSellId(), Base64.getEncoder().encodeToString(sell.getThumbnail()));
        }
        model.addAttribute("images", productBase64Images);
		model.addAttribute("sells", list);
	}
	
	@GetMapping("/buyList")
	public void read(Model model) {
		log.info("read()");
		List<Sell> list = sellService.read();
		
		Map<Long, String> productBase64Images = new HashMap<>();
        for(Sell sell: list){               
            productBase64Images.put(sell.getSellId(), Base64.getEncoder().encodeToString(sell.getThumbnail()));
        }
        model.addAttribute("images", productBase64Images);
		model.addAttribute("sells", list);
	
	}
	
}
