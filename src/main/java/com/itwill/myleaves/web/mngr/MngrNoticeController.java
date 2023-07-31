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
@RequestMapping("/mngr/notice")
public class MngrNoticeController {
	
	/**
	 * 사용자 공지사항 list
	 * @param model
	 * @return
	 */
	@GetMapping("/list")
	public String list(Model model) {
		log.info("mngr notice list()");
		
		return "/mngr/info/notice/list";
	}
	
	/**
	 * 사용자 공지사항 detail
	 * @param model
	 * @return
	 */
	@GetMapping("/detail")
	public String detail(Model model) {
		log.info("mngr notice detail()");
		
		return "/mngr/info/notice/detail";
	}
	
}
