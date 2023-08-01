package com.itwill.myleaves.service.member;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.itwill.myleaves.dto.member.MemberSignUpDto;
import com.itwill.myleaves.repository.member.Member;
import com.itwill.myleaves.repository.member.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	// 회원가입
	public String registerMember(MemberSignUpDto dto) {
		
		log.info("registerMember(dto={})", dto);
		
		Member entity = Member.builder()
				.userId(dto.getUserId())
                .name(dto.getName())
                .pwd(passwordEncoder.encode(dto.getPwd()))
                .gender(dto.getGender())
                .birth(dto.getBirth())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .build();
		
		log.info("save 전: entity={}", entity);
		
		memberRepository.save(entity);
		log.info("save 후: entity={}", entity);
		
		return entity.getUserId();
	}

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		log.info("loadUserByUsername(userId={})", userId);

		UserDetails user = memberRepository.findByUserId(userId);

		if (user != null) {
			return user;
		}

		throw new UsernameNotFoundException(userId + "not found");
	}
}