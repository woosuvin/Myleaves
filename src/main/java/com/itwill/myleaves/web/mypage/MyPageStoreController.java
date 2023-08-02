package com.itwill.myleaves.web.mypage;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.myleaves.repository.store.Store;
import com.itwill.myleaves.service.store.StoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/store")
public class MyPageStoreController {
	private final StoreService storeService;
	
	@GetMapping("/wish_list")
	public void wishList(Model model) {
		log.info("storeList:GET");
		
		List<Store> list = storeService.read();
		model.addAttribute("stores", list);
	}
	
	@GetMapping("/order_list")
	public void orderList(Model model) {
	}
	
	
}
