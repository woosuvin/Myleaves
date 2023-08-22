package com.itwill.myleaves.web.member;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itwill.myleaves.dto.member.MemberSignUpDto;
import com.itwill.myleaves.repository.kakao.KakaoProfile;
import com.itwill.myleaves.repository.member.Member;
import com.itwill.myleaves.repository.oauth_token.OAuthToken;
import com.itwill.myleaves.service.member.MemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

	private final MemberService memberService;
	// private final KakaoProfileService kakaoProfileService;
	private final AuthenticationManager authenticationManager;
	private final PasswordEncoder passwordEncoder;
	private final HttpServletRequest request;

	@Value("${cos.key}")
	private String cosKey;

	@GetMapping("/signup")
	public void signUp() {
//		log.info("signup()");
	}

	@PostMapping("/signup")
	public String signUp(MemberSignUpDto dto) {
//		log.info("signUp(dto={}) POST", dto);

		// 회원 가입 서비스 호출
		String id = memberService.registerMember(dto);
//		log.info("회원 가입 id={}", id);

		// 회원가입 이후에 로그인 화면으로 이동
		return "redirect:/member/login";
	}

	@GetMapping("/login")
	public void login(@RequestParam(name = "error", required = false) String error, Model model) {
//		log.info("login(error={})", error);

		if (error != null) {
			model.addAttribute("errorMsg", "아이디 또는 비밀번호가 일치하지 않습니다.");
		}
	}

	@GetMapping("/find")
	public void find() {
//		log.info("find()");
	}

	@GetMapping("/kakao/callback")
	public String kakaoCallback(String code, HttpServletRequest request) {
//		log.info("kakaoCallback(code={})", code);

		// POST 방식으로 key=value 데이터를 요청 (카카오 쪽으로)
		// Retrofit2 - 안드로이드
		// OkHttp
		// RestTemplate
		// 요청을 보내기 위한 라이브러리
		RestTemplate rt = new RestTemplate();

		// HttpHeader 오브젝트 셍성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "314c0325ca91badd1284203c73a36949");
		params.add("redirect_uri", "http://localhost:8090/member/kakao/callback");
		params.add("code", code);

		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

		// Http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음
		ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token", // 토큰 발급 요청 주소
				HttpMethod.POST, // 요청 메서드
				kakaoTokenRequest, // http 헤더와 http 바디
				String.class // 응답 받을 타입
		);

		// 요청 정보를 object에 담기
		// Gson, Json Simple, ObjectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		// 이름이 틀릴 경우 파싱할 때 에러가 나기 때문에 try-catch
		// 파싱하려면 getter/setter가 있어야 함
		try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class); // getBody()를 OAuthToken 클래스에 담음
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

//		log.info("kakaoCallback(oauthToken={})", oauthToken.getAccess_token());

		// POST 방식으로 key=value 데이터를 요청 (카카오 쪽으로)
		// Retrofit2 - 안드로이드
		// OkHttp
		// RestTemplate
		// 요청을 보내기 위한 라이브러리
		RestTemplate rt2 = new RestTemplate();

		// HttpHeader 오브젝트 셍성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpHeader를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);

		// Http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음
		ResponseEntity<String> response2 = rt2.exchange("https://kapi.kakao.com/v2/user/me", // 토큰 발급 요청 주소
				HttpMethod.POST, // 요청 메서드
				kakaoProfileRequest2, // http 헤더
				String.class // 응답 받을 타입
		);

		// 요청 정보를 object에 담기
		// Gson, Json Simple, ObjectMapper
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		// 이름이 틀릴 경우 파싱할 때 에러가 나기 때문에 try-catch
		// 파싱하려면 getter/setter가 있어야 함
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class); // getBody()를
																								// KakaoProfile.class에
																								// 담음
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		// member 오브젝트: name, password, email
//		log.info("kakaoCallback(kakao id={}, kakao email={}) kakao username={} kakao gender={} ", kakaoProfile.getId(),
//				kakaoProfile.getKakao_account().getEmail(), kakaoProfile.getKakao_account().getProfile().getNickname());

		Member kakaoMember = Member.builder().userId(kakaoProfile.getId())
				.name(kakaoProfile.getKakao_account().getProfile().getNickname()).pwd(passwordEncoder.encode(cosKey))
				.email(kakaoProfile.getKakao_account().getEmail()).build();

//		log.info("kakaoCallback(kakaoMember={})", kakaoMember);

		// 가입자 혹은 비가입자 처리
		Member originMember = memberService.findMember(kakaoMember.getUserId());
		if (originMember == null) {
//			System.out.println("기존 회원이 아니기에 자동 회원가입을 진행합니다");
			memberService.registerMember(kakaoMember);
		}

//		System.out.println("자동 로그인을 진행합니다.");

//		Authentication authentication = authenticationManager
//				.authenticate(new UsernamePasswordAuthenticationToken(kakaoMember.getUserId(), cosKey));
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		log.info("kakaoCallback(authentication={})", authentication);

//		HttpSession session = request.getSession();
//		session.setAttribute("userAuthentication", authentication);

//		log.info("kakaoCallback(session={})", session);

		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(kakaoMember.getUserId(), cosKey);

		// Authenticate the user
		Authentication authentication = authenticationManager.authenticate(authRequest);
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);

		// Create a new session and add the security context.
		HttpSession session = request.getSession(true);
		session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

		// ------------------------------------------
		//session.setAttribute("auth", authentication);
		// ------------------------------------------

		return "redirect:/";
	}
}