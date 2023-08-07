package com.itwill.myleaves.web.oauth_member;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.myleaves.repository.oauth_member.AuthTokens;
import com.itwill.myleaves.repository.oauth_member.KakaoLoginParams;
import com.itwill.myleaves.service.oauth_member.OAuthLoginService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthRestController {
    private final OAuthLoginService oAuthLoginService;

    @PostMapping("/kakao")
    public ResponseEntity<AuthTokens> loginKakao(@RequestBody KakaoLoginParams params) {
    	log.info("loginKaka(params={})", params);
    	
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }
}