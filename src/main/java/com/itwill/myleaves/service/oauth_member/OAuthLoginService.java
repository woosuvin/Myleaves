package com.itwill.myleaves.service.oauth_member;

import org.springframework.stereotype.Service;

import com.itwill.myleaves.repository.oauth_member.AuthTokens;
import com.itwill.myleaves.repository.oauth_member.AuthTokensGenerator;
import com.itwill.myleaves.repository.oauth_member.OAuthInfoResponse;
import com.itwill.myleaves.repository.oauth_member.OAuthLoginParams;
import com.itwill.myleaves.repository.oauth_member.OAuthMember;
import com.itwill.myleaves.repository.oauth_member.OAuthMemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OAuthLoginService {
    private final OAuthMemberRepository oAuthmemberRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;

    public AuthTokens login(OAuthLoginParams params) {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        Long memberId = findOrCreateMember(oAuthInfoResponse);
        return authTokensGenerator.generate(memberId);
    }

    private Long findOrCreateMember(OAuthInfoResponse oAuthInfoResponse) {
        return oAuthmemberRepository.findByEmail(oAuthInfoResponse.getEmail())
                .map(OAuthMember::getId)
                .orElseGet(() -> newMember(oAuthInfoResponse));
    }

    private Long newMember(OAuthInfoResponse oAuthInfoResponse) {
    	OAuthMember oAuthMeber = OAuthMember.builder()
                .email(oAuthInfoResponse.getEmail())
                .nickname(oAuthInfoResponse.getNickname())
                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .build();

        return oAuthmemberRepository.save(oAuthMeber).getId();
    }
}