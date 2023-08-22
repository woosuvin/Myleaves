package com.itwill.myleaves.web;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.myleaves.repository.member.Member;
import com.itwill.myleaves.repository.mngr.Criteria;
import com.itwill.myleaves.repository.mngr.PageDto;
import com.itwill.myleaves.service.community.CommunityCommentService;
import com.itwill.myleaves.service.community.CommunityService;
import com.itwill.myleaves.service.home.HomeService;
import com.itwill.myleaves.service.member.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mngr")
public class MngrHomeController {
	
	private final MemberService memberService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/member/manage")
	public String manage(Criteria cri, Model model) {
//		log.info("manage()");

		List<Member> members = memberService.readWithPaging(cri);
//		log.info("manage(members={})", members);
		int size = memberService.read();
		log.info("manage(size={})", size);
		
		String json1 = memberService.readGender();
		
		String json2 = memberService.readMonth();
		
		model.addAttribute("json1", json1);
		model.addAttribute("json2", json2);
		model.addAttribute("members", members);
		model.addAttribute("pageMaker", new PageDto(cri, size));
		
		return "/mngr/member/manage";
	}

}
