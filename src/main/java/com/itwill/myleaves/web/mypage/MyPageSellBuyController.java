package com.itwill.myleaves.web.mypage;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.myleaves.repository.sellbuy.Sell;
import com.itwill.myleaves.repository.sellbuy.Buy;
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

	// 입양 위시리스트
	@GetMapping("/buyWish")
	public void read(BuyWish wishList, Model model) {
		log.info("read()");
		List<BuyWish> buyWishlist = mypageService.read(wishList);
		List<Sell> sellList = new ArrayList<>();
		
		for(BuyWish b : buyWishlist) {
			Sell sell = sellService.read(b.getSellId());
			sellList.add(sell);
		}
		Map<Long, String> thumbnails = new HashMap<>();
		
        for(Sell sell: sellList){
        	thumbnails.put(sell.getSellId(), Base64.getEncoder().encodeToString(sell.getThumbnail()));
        }
        model.addAttribute("images", thumbnails);
//		model.addAttribute("buyWish", buyWishlist);
		model.addAttribute("sell", sellList);
	}
	
	// 분양 리스트
	@GetMapping("/sellList")
	public void sellList(String userId, Model model) {
		log.info("read(userId={})", userId);
		
		List<Sell> sellList = sellService.readSellList(userId);

		Map<Long, String> thumbnails = new HashMap<>();
		
        for(Sell sell: sellList){
        	thumbnails.put(sell.getSellId(), Base64.getEncoder().encodeToString(sell.getThumbnail()));
        }
        model.addAttribute("images", thumbnails);
		
		model.addAttribute("sell", sellList);
	}
	
	// 입양 리스트
	@GetMapping("/buyList")
	public void buyList(String buyerId, Model model) {
	    log.info("read(buyerId={})", buyerId);

	    List<Buy> buyList = sellService.readBuyList(buyerId);
	    Map<Long, String> thumbnails = new HashMap<>();
		
        for(Buy buy: buyList){
        	thumbnails.put(buy.getSellId(), Base64.getEncoder().encodeToString(buy.getThumbnail()));
        }
        model.addAttribute("images", thumbnails);
	    
	    model.addAttribute("buy", buyList);
	}
	
}
