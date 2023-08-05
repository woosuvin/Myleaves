package com.itwill.myleaves.web.community;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.myleaves.dto.community.CommunityCreateDto;
import com.itwill.myleaves.dto.community.CommunityUpdateDto;
import com.itwill.myleaves.repository.community.Community;
import com.itwill.myleaves.service.community.CommunityCommentService;
import com.itwill.myleaves.service.community.CommunityService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/community") 
public class CommunityHomeController {
	private final CommunityService communityService;
	private final CommunityCommentService communityCommentService;
	
	@GetMapping
	public String post(Model model) { 
		log.info("community home");
		
		// 포스트 목록 검색
	    List<Community> list = communityService.read();
	    
	    // Model 검색 결과를 세팅.
	    model.addAttribute("posts", list );
		
		return "community/home";
	}
	
	// 커뮤니티 게시글 작성하기 
//	@PreAuthorize("hasRole('USER')")
	@GetMapping("/create")
	public void create() {
		log.info("create() Get");
		
	}
	
//	@PreAuthorize("hasRole('USER')")
	@PostMapping("/create")
	public String create(CommunityCreateDto dto) {
		log.info("create(dto={}) POST", dto);
		
		// form에서 submit된 내용을 DB 테이블에 insert
		communityService.create(dto);
		
		return "redirect:/community";
	}
	
	/**
	 * 커뮤니티 게시글 상세보기 
	 * @param model @id 
	 * @return String 
	 */
	@GetMapping("/detail") 
	public String read(long id, Model model) {
		log.info("read(communityId={})", id);
		
		// COMMUNITY 테이블에서 id에 해당하는 게시글을 검색
		Community post = communityService.read(id);
		
		// 결과를 model에 저장 -> 뷰로 전달 
		model.addAttribute("post", post);
		
	    long count = communityCommentService.countByCommunityId(post);
	    model.addAttribute("communityCommentCount", count);
		
		return "/community/detail";
		
	}
	
	/**
	 * 커뮤니티 게시글 수정하기 
	 * @param model @id 
	 * @return String 
	 */
	@GetMapping("/modify") 
	public String modify(long communityId, Model model) {
		log.info("read(communityId={})", communityId);
		
		// COMMUNITY 테이블에서 id에 해당하는 게시글을 검색
		Community post = communityService.read(communityId);
		
		// 결과를 model에 저장 -> 뷰로 전달 
		model.addAttribute("post", post);
		
		 long count = communityCommentService.countByCommunityId(post);
		  model.addAttribute("communityCommentCount", count);
		
		return "community/modify";
		
	}
	
	
	// 커뮤니티 게시글 삭제하기
//	@PreAuthorize("hasRole('USER')")
	@PostMapping("/delete")
	public String delete(Long communityId) {
		log.info("delete(communityId={})", communityId);
		
	communityService.delete(communityId);

	return "redirct:/community";
	}
	
	
//	@PreAuthorize("hasRole('USER')")
	@PostMapping("/update")
	public String update(CommunityUpdateDto dto) {
		log.info("update(dto={})", dto);
		
		communityService.update(dto);
		
		return "redirect:/community/detail?id="+dto.getCommunityId();
	}
	
	
	
	
	
	
	
	
}
