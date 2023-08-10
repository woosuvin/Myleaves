package com.itwill.myleaves.web.mypage;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.myleaves.dto.member.MemberModifyPwdDto;
import com.itwill.myleaves.service.mypage.MyPageProfileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/mypage/profile")
public class MyPageProfileController {
	
	private final MyPageProfileService myPageProfileService;
	
	@GetMapping("/info")
	public void myprofile(@RequestParam String userId, Model model) {
		log.info("profile(userId={})", userId);
		
		model.addAttribute("member", myPageProfileService.read(userId));
	}
	
	@GetMapping("/modifyPwd")
	public void modifyPwd() {
		log.info("modifyPwd()");
	}
	
	@GetMapping("/matches/pwd")
	public ResponseEntity<Boolean> matchesPwd(String userId, String currentPwd) {
		log.info("matchesPwd(userId={}, currentPwd={})", userId, currentPwd);
		
		Boolean bool = myPageProfileService.matchesPwd(userId, currentPwd);
		log.info("matchesPwd(bool={})", bool);
		
		return ResponseEntity.ok(bool);
	}
	
	@PutMapping("/modify/pwd")
	public ResponseEntity<String> modifyPwd(@RequestBody MemberModifyPwdDto dto) {
		log.info("modifyPwd(dto={})", dto);
		
		myPageProfileService.modifyPwd(dto);
		
		return ResponseEntity.ok("Success");
	}
}