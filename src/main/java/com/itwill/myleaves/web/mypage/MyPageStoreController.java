package com.itwill.myleaves.web.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/store")
public class MyPageStoreController {
	
	@GetMapping("/wish_list")
	public void wishList(Model model) {
	}
	
	@GetMapping("/order_list")
	public void orderList(Model model) {
	}
	
	
}
