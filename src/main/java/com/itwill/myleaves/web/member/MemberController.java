package com.itwill.myleaves.web.member;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itwill.myleaves.dto.member.MemberSignUpDto;
import com.itwill.myleaves.repository.kakao.KakaoProfile;
import com.itwill.myleaves.repository.oauth_token.OAuthToken;
import com.itwill.myleaves.service.kakao.KakaoProfileService;
import com.itwill.myleaves.service.member.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

	private final MemberService memberService;
	private final KakaoProfileService kakaoProfileService;
	
	@GetMapping("/signup")
	public void signUp() {
		log.info("signup()");
	}

	@PostMapping("/signup")
	public String signUp(MemberSignUpDto dto) {
		log.info("signUp(dto={}) POST", dto);

		// 회원 가입 서비스 호출
		String id = memberService.registerMember(dto);
		log.info("회원 가입 id={}", id);

		// 회원가입 이후에 로그인 화면으로 이동
		return "redirect:/member/login";
	}

	@GetMapping("/login")
	public void login(@RequestParam(name = "error", required = false) String error, Model model) {
		log.info("login(error={})", error);

		if (error != null) {
			model.addAttribute("errorMsg", "아이디 또는 비밀번호가 일치하지 않습니다.");
		}
	}

	@GetMapping("/find")
	public void find() {
		log.info("find()");
	}

	@GetMapping("/kakao/callback")
	public String kakaoCallback(String code) {
		log.info("kakaoCallback(code={})", code);
		
		kakaoProfileService.kakaoCallback(code);
		
		return "/member/login";
	}
}