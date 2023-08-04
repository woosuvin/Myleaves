package com.itwill.myleaves.web.member;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.myleaves.repository.member.EmailService;
import com.itwill.myleaves.service.member.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberRestController {

	private final EmailService emailService;
	private final MemberService memberService;
	
	@GetMapping("/confirm")
	public ResponseEntity<String> emailConfirm(String email) throws Exception {
	    log.info("emailConfirm(email={})", email);

	    String confirm = emailService.sendSimpleMessage(email);
	    log.info("emailConfirm(confirm={})", confirm);

	    return ResponseEntity.ok(confirm);
	}
	
	@GetMapping("/find/id")
	public ResponseEntity<String> findId(String name, String email) {
		log.info("findId(name={}, email={})", name, email);
		
		String userId = memberService.read(name, email);
		log.info("findId(userId={})", userId);
		
		return ResponseEntity.ok(userId);
	}
	
	@GetMapping("/modify/pwd")
	public ResponseEntity<String> modifyPwd(@RequestParam String userId) {
		log.info("modifyPwd(userId={})", userId);
		
		String newPwd = memberService.modifyPwd(userId);
		log.info("modifyPwd(newPwd={}", newPwd);
		
		return ResponseEntity.ok(newPwd);
	}
}