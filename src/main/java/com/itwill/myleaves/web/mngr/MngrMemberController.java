package com.itwill.myleaves.web.mngr;

 import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.myleaves.dto.member.MemberSearchDto;
import com.itwill.myleaves.repository.member.Member;
import com.itwill.myleaves.repository.mngr.Criteria;
import com.itwill.myleaves.repository.mngr.PageDto;
import com.itwill.myleaves.service.member.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/mngr")
@Controller
public class MngrMemberController {

	private final MemberService memberService;

	@GetMapping("/member/manage")
	public void manage(Criteria cri, Model model) {
		log.info("manage()");

		List<Member> members = memberService.readWithPaging(cri);
		int size = memberService.read();
		log.info("manage(size={})", size);
		
		model.addAttribute("members", members);
		model.addAttribute("pageMaker", new PageDto(cri, size));
	}
	
	@GetMapping("/member/search")
	public void memberSearch(Criteria cri, Model model, MemberSearchDto dto) {
		log.info("memberSearch(cri={}, dto={})", cri, dto);
		
		List<Member> members = memberService.readWithPagingAndSearch(cri, dto);
		//TODO: 사이즈 가져와야되는데 귀찮

		model.addAttribute("members", members);
		model.addAttribute("pageMaker", new PageDto(cri, 100));
	}
}