package com.itwill.myleaves.web.community;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.myleaves.repository.community.Community;
import com.itwill.myleaves.service.community.CommunityService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/community") 
public class CommunityHomeController {
	private final CommunityService communityService;
	
	@GetMapping
	public String post(Model model) { 
		log.info("community home");
		
		// 포스트 목록 검색
	    List<Community> list = communityService.read();
	    
	    // Model 검색 결과를 세팅.
	    model.addAttribute("posts", list );
		
		return "/community/home";
	}
	
	// 커뮤니티 글 작성하기 
	@GetMapping("/create")
	public String create() {
		
		return "redirect:/community/create";
	}
	
	/**
	 * 커뮤니티 상세보기
	 * @param model
	 * @return
	 */
	@GetMapping("/detail")
	public String detail(Model model) {
		log.info("postDetail");
		
		return "/community/detail";
	}
}
