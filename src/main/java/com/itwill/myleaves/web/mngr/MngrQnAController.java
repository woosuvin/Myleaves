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
@RequestMapping("/mngr/qna")
public class MngrQnAController {
	
	
	@GetMapping({"/list", "/modify"})
	public void mngrqna(Model model) {
		log.info("mngrqna list");		
	}
	/*
	 * 어차피 나중에 modify 나눠야 힘
	 */
}
