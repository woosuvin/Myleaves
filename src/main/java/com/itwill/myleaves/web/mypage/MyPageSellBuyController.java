package com.itwill.myleaves.web.mypage;

import java.util.ArrayList;
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
	@PreAuthorize("hasRole('MEMBER')")
	@GetMapping("/buyWish")
	public void read(BuyWish wishList, Model model, @PageableDefault(page = 0, size = 8) Pageable pageable) {
//		log.info("read()");
		Page<BuyWish> buyWishlist = mypageService.read(wishList, pageable);
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
		
		int totalPage = buyWishlist.getTotalPages()-1;
		int nowPage = buyWishlist.getPageable().getPageNumber()+1; //지금 페이지 0 + 1 => 1 페이지부터 시작
		int startPage = Math.max(nowPage-4, 1);
		int endPage = Math.min(nowPage+5, buyWishlist.getTotalPages());
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
	}
	
	// 분양 리스트
	@PreAuthorize("hasRole('MEMBER')")
	@GetMapping("/sellList")
	public void sellList(String userId, Model model, @PageableDefault(page = 0, size = 8) Pageable pageable) {
//		log.info("read(userId={})", userId);
		
		Page<Sell> sellList = sellService.readSellList(userId, pageable);

		Map<Long, String> thumbnails = new HashMap<>();
		
        for(Sell sell: sellList){
        	thumbnails.put(sell.getSellId(), Base64.getEncoder().encodeToString(sell.getThumbnail()));
        }
        model.addAttribute("images", thumbnails);
		
		model.addAttribute("sell", sellList);
		
		int totalPage = sellList.getTotalPages()-1;
		int nowPage = sellList.getPageable().getPageNumber()+1; //지금 페이지 0 + 1 => 1 페이지부터 시작
		int startPage = Math.max(nowPage-4, 1);
		int endPage = Math.min(nowPage+5, sellList.getTotalPages());
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
	}
	
	// 입양 리스트
	@PreAuthorize("hasRole('MEMBER')")
	@GetMapping("/buyList")
	public void buyList(String buyerId, Model model, @PageableDefault(page = 0, size = 8) Pageable pageable) {
//	    log.info("read(buyerId={})", buyerId);

	    Page<Buy> buyList = sellService.readBuyList(buyerId, pageable);
	    
	    Map<Long, String> thumbnails = new HashMap<>();
		
        for(Buy buy: buyList){
        	thumbnails.put(buy.getSellId(), Base64.getEncoder().encodeToString(buy.getThumbnail()));
        }
        model.addAttribute("images", thumbnails);
	    
	    model.addAttribute("buy", buyList);
	    
	    int totalPage = buyList.getTotalPages()-1;
		int nowPage = buyList.getPageable().getPageNumber()+1; //지금 페이지 0 + 1 => 1 페이지부터 시작
		int startPage = Math.max(nowPage-4, 1);
		int endPage = Math.min(nowPage+5, buyList.getTotalPages());
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
	}
	
}
