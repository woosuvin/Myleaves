package com.itwill.myleaves.service.mypage;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.myleaves.dto.member.MemberModifyPwdDto;
import com.itwill.myleaves.repository.member.Member;
import com.itwill.myleaves.repository.member.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MyPageProfileService {
	
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	
	public Member read(String userId) {
		log.info("read(userId={})", userId);
		
		Member member = memberRepository.findByUserId(userId);
		log.info("read(member={})", member);
				
		return member;
	}

	public Boolean matchesPwd(String userId, String currentPwd) {
		log.info("matchesPwd(userId={}, currentPwd={}", userId, currentPwd);
		
		Member member = memberRepository.findByUserId(userId);
		String pwd = member.getPassword();
		log.info("findPwd(pwd={})", pwd);
		
		
		boolean matches = passwordEncoder.matches(currentPwd, pwd);
		if (matches) {
			return true;
		} else {
			return false;
		}
	}
	
	@Transactional
	public void modifyPwd(MemberModifyPwdDto dto) {
		log.info("modifyPwd(dto={})", dto);
		
		Member member = memberRepository.findByUserId(dto.getUserId());
		
		log.info("modifiyPwd(pwd={} SAVE 전)", member.getPwd());
		
		member.update(passwordEncoder.encode(dto.getReNewPwd()));
		
		log.info("modifyPwd(pwd={} SAVE 후)", member.getPwd());
	}
}