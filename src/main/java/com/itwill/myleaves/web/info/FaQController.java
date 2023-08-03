package com.itwill.myleaves.web.info;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.myleaves.repository.faq.FaQ;
import com.itwill.myleaves.service.faq.FaQService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/info/faq")
public class FaQController {
	
	private final FaQService faqService;
	
	/*
	 * FaQ 메인 리스트 페이지
	 * 로그인??... 굳이
	 */
	@GetMapping
	public String readFaQ(Model model) {
		log.info("FaQ read()");
		
		List<FaQ> list = faqService.read();
		
		model.addAttribute("faqs", list);
		
		return "info/faq/read";
	}
	
	
}
