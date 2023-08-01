package com.itwill.myleaves.web.cart;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/cart")
public class CartController {
	
	@GetMapping("/list")
	public void read(Model model) {
		log.info("cart list:GET");
	}
	
	@GetMapping("/order")
	public void order(Model model) {
		log.info("order:GET");
	}
	
}
