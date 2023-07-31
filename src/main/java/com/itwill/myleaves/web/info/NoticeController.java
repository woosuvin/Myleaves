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
@RequestMapping("/info/notice")
public class NoticeController {
	
	/**
	 * 사용자 공지사항 list
	 * @param model
	 * @return
	 */
	@GetMapping({"", "/"})
	public String list(Model model) {
		log.info("notice list()");
		
		return "/notice/list";
	}
	
	/**
	 * 사용자 공지사항 detail
	 * @param model
	 * @return
	 */
	@GetMapping("/detail")
	public String detail(Model model) {
		log.info("notice detail()");
		
		return "/notice/detail";
	}
	
}
