package com.itwill.myleaves.service.mypage;

import org.springframework.stereotype.Service;

import com.itwill.myleaves.repository.member.Member;
import com.itwill.myleaves.repository.member.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MyPageProfileService {
	
	private final MemberRepository memberRepository;
	
	public Member read(String userId) {
		log.info("read(userId={})", userId);
		
		Member member = memberRepository.findByUserId(userId);
		log.info("read(member={})", member);
				
		return member;
	}
}