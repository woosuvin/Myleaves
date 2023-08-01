package com.itwill.myleaves.web.store;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.myleaves.repository.store.Store;
import com.itwill.myleaves.service.store.StoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 수빈
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {
	
	private final StoreService storeService;
	
	@GetMapping("/list")
	public void read(Model model) {
		log.info("storeList:GET");
		
		List<Store> list = storeService.read();
		model.addAttribute("stores", list);
	}
	
	@GetMapping("/detail")
	public void read(long itemId, Model model) {
		log.info("read(itemId={})", itemId);
		
		Store store = storeService.read(itemId); // itemId로 store 테이블에서 검색
		
		model.addAttribute("store", store); // model에 저장
	}
}
