package com.itwill.myleaves.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	
	/**
	 * 사용자 home page
	 * @param model
	 * @return
	 */
	@GetMapping("/")
	public String home(Model model) {
		log.info("home");
		
		return "/main/home";
	}
	
	
}
