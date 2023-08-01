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
@RequestMapping("/mypage/qna")
public class MypageQnAController {
	
	/*
	 * 내가 쓴 QnA 리스트 페이지
	 */
	@GetMapping("/qna_list")
	public void qnaList(Model model) {
	}
}
