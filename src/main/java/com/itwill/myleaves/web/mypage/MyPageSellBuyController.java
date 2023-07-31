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
@RequestMapping("/mypage/sellbuy")
public class MyPageSellBuyController {

	@GetMapping("/wish_list")
	public void wishList(Model model) {
	}
	
	@GetMapping("/sell_list")
	public void sellList(Model model) {
	}
	
	@GetMapping("/buy_list")
	public void buyList(Model model) {
	}
}
