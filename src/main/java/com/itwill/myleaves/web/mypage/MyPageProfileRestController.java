package com.itwill.myleaves.web.mypage;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.myleaves.dto.member.MemberUpdateDto;
import com.itwill.myleaves.service.member.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage/profile")
public class MyPageProfileRestController {
	
	private final MemberService memberService;
	
	@PreAuthorize("hasRole('MEMBER')")
	@PutMapping("/update")
	public ResponseEntity<String> update(@RequestBody MemberUpdateDto dto) {
		log.info("modify(dto={})", dto);
		
		memberService.update(dto);
		
		return ResponseEntity.ok("Success");
	}
	
	@PreAuthorize("hasRole('MEMBER')")
	@GetMapping("/check/pwd")
	public ResponseEntity<String> checkPwd(@RequestParam String userId) {
//		log.info("checkPwd(pwd={})", pwd);
		
//		memberService.checkPwd(pwd);
		
		return null;
	}
}
