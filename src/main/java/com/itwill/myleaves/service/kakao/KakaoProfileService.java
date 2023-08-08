package com.itwill.myleaves.service.kakao;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itwill.myleaves.repository.kakao.KakaoProfile;
import com.itwill.myleaves.repository.oauth_token.OAuthToken;
import com.itwill.myleaves.service.member.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class KakaoProfileService {

	private final MemberService memberService;

	public void kakaoCallback(String code) {
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

		log.info("kakaoCallback(oauthToken={})", oauthToken.getAccess_token());

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
		log.info("kakaoCallback(kakao id={}, kakao email={}) kakao username={} kakao gender={} ", kakaoProfile.getId(),
				kakaoProfile.getKakao_account().getEmail(), kakaoProfile.getKakao_account().getProfile().getNickname());

		memberService.registerMember(kakaoProfile);
	}

}
