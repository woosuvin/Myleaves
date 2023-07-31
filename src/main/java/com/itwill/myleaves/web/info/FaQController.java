package com.itwill.myleaves.web.info;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/info/faq")
public class FaQController {
	/*
	 * FaQ 메인 페이지
	 */
	@GetMapping
	public String readFaQ(Model model) {
		log.info("FaQ read()");
		
		return "/faq/read";
	}
}
