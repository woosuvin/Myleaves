package com.itwill.myleaves.web.mngr;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/mngr/faq")
public class MngrFAQController {
	
	// 어차피 나눠야 함 ㅋ 
	@GetMapping({"/list", "/create","/detail","/modify"})
	public void mngrfaq(Model model) {
		log.info("mngrfaq");		
	}
	
}
