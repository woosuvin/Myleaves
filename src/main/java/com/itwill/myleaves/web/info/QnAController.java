package com.itwill.myleaves.web.info;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
		
		return "/info/qna/read";
	}
	
	/*
	 * QnA 새 글 작성하기
	 */
	@GetMapping("/create")
    public void create() {
        log.info("QNA create() GET");
        
	}
	/*
	 * QnA 상세 페이지
	 */
	@GetMapping("/detail")
	public void detailQnA(Model model) { // (QID) DB 만들면 합쳐야징 !! .. id 값도 줘야함
		log.info("QNA detail() GET");
	}
	/*
	 * QnA 수정 페이지
	 */
	
	
	
	
	
	/*
	 * QnA 삭제 페이지
	 */
	

}
