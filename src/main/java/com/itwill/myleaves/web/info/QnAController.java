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
@RequestMapping("/info/qna")
public class QnAController {
	/*
	 * QnA 리스트 메인 페이지
	 */
	@GetMapping
	public String readQnA(Model model) {
		log.info("QNA read()");
		
		return "/qna/read";
	}
	
	/*
	 * @GetMapping("/detail") public void readQnA(long id, Model model) {
	 * log.info("read(id={})" , id); }
	 */
}
