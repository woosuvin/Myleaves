package com.itwill.myleaves.web.mngr;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.myleaves.dto.store.StoreCreateDto;
import com.itwill.myleaves.dto.store.StoreUpdateDto;
import com.itwill.myleaves.repository.store.Store;
import com.itwill.myleaves.service.store.StoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 수빈
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mngr/store")
public class MngrStoreController {
	
	private final StoreService storeService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/list")
	public void mngrHome(Model model) {
		log.info("mngrStoreList");
		
		List<Store> list = storeService.read();
		model.addAttribute("stores", list);
	}
	
	@GetMapping("/create")
	public void create() {
		log.info("storeCreate:GET");
	}
	
	@PostMapping("/create")
	public String create(StoreCreateDto dto) {
		log.info("create(dto={}):POST", dto);
		
		storeService.create(dto); // form에서 submit된 내용을 db에 insert
		
		return "redirect:/mngr/store/list"; // list로 redirect
	}
	
	@GetMapping({"/detail", "/modify"} )
	public void read(long itemId, Model model) {
		log.info("read(itemId={})", itemId);
		
		Store store = storeService.read(itemId); // itemId로 store 테이블에서 검색
		
		model.addAttribute("store", store); // model에 저장
	}
	
	@PostMapping("/update")
	public String update(StoreUpdateDto dto) {
		storeService.update(dto);
		return "redirect:/mngr/store/list";
	}
	
	@PostMapping("/delete")
	public String delete(long itemId) {
		log.info("delete(itemId={})", itemId);
		storeService.delete(itemId);
		return "redirect:/mngr/store/list";
	}
}
