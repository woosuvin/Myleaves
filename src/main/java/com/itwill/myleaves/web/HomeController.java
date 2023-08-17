package com.itwill.myleaves.web;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.itwill.myleaves.repository.sellbuy.Sell;
import com.itwill.myleaves.repository.store.Store;
import com.itwill.myleaves.service.sellbuy.SellService;
import com.itwill.myleaves.service.store.StoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 정지언
@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
	
	private final SellService sellService;
	private final StoreService storeService;
	
	/**
	 * 사용자 home page
	 * @param model
	 * @return
	 */
	@GetMapping("/")
	public String home(Model model) {
		log.info("home");
		List<Sell> sellList = sellService.read();
		model.addAttribute("sells", sellList);
		
		Map<Long, String> sellThumbnails = new HashMap<>();
		for(Sell sell: sellList){
			sellThumbnails.put(sell.getSellId(), Base64.getEncoder().encodeToString(sell.getThumbnail()));
		}
		model.addAttribute("sellImages", sellThumbnails);
		
		List<Store> storeList = storeService.read();
		model.addAttribute("stores", storeList);
		
		Map<Long, String> storeThumbnails = new HashMap<>();
		for(Store store: storeList){
			storeThumbnails.put(store.getItemId(), Base64.getEncoder().encodeToString(store.getThumbnail()));
        }
        model.addAttribute("storeImages", storeThumbnails);
		
		
		return "/main/home";
	}
	
	
}
