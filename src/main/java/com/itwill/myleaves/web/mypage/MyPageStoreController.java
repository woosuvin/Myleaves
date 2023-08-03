package com.itwill.myleaves.web.mypage;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.myleaves.repository.store.Store;
import com.itwill.myleaves.repository.store.StoreWish;
import com.itwill.myleaves.service.store.MypageStoreService;
import com.itwill.myleaves.service.store.StoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/store")
public class MyPageStoreController {
	private final MypageStoreService storeService;
	
	@GetMapping("/storeWish")
	public void wishList(StoreWish storeWishList, Model model) {
		log.info("read()");
		
		List<Store> list = storeService.read(storeWishList);
		
		model.addAttribute("wishStore", list);
	
	}
	
	@GetMapping("/orderList")
	public void orderList(Model model) {
	}
	
	
}
