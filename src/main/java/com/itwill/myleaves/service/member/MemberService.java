package com.itwill.myleaves.service.member;

import java.util.Random;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.myleaves.dto.member.MemberSignUpDto;
import com.itwill.myleaves.dto.member.MemberUpdateDto;
import com.itwill.myleaves.repository.kakao.KakaoProfile;
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
	
	public String registerMember(KakaoProfile kakaoProfile) {
		log.info("registerMember(dto={})", kakaoProfile);

		UUID garbagePwd = UUID.randomUUID();
		log.info("registerMember(uuid={})", garbagePwd);
		
		Member entity = Member.builder()
				.userId(kakaoProfile.getId())
				.name(kakaoProfile.getKakao_account().getProfile().getNickname())
				.pwd(passwordEncoder.encode(garbagePwd.toString()))
				.email(kakaoProfile.getKakao_account().getEmail())
				.build();
		
		log.info("registerMember(save 전: entity={})", entity);
		
		memberRepository.save(entity);
		log.info("registerMember(save 후: entity={})", entity);
		
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

	public String read(String name, String email) {
		log.info("read(name={}, email={})", name, email);
		
		Member entity = memberRepository.findByNameAndEmail(name, email);
		
		if (entity == null) {
			return "회원정보가 존재하지 않습니다.";
		} else {
			String userId = entity.getUserId();
			
			log.info("read={}", userId);
			return userId;
		}
	}
	
	@Transactional
	public String modifyPwd(String userId) {
		log.info("modifyPwd()");
		
		StringBuffer newPwd = new StringBuffer();
        Random rnd = new Random();
 
        for (int i = 0; i < 8; i++) {
            int index = rnd.nextInt(3);
 
            switch (index) {
                case 0:
                	newPwd.append((char) ((int) (rnd.nextInt(26)) + 97));
                    break;
                case 1:
                	newPwd.append((char) ((int) (rnd.nextInt(26)) + 65));
                    break;
                case 2:
                	newPwd.append((rnd.nextInt(10)));
                    break;
            }
        }
        
        Member entity = memberRepository.findByUserId(userId);
        entity.update(passwordEncoder.encode(newPwd.toString()));
        return newPwd.toString();
	}

	@Transactional
	public void update(MemberUpdateDto dto) {
		log.info("update(dto={})", dto);
		
		Member member = memberRepository.findByUserId(dto.getUserId());
		log.info("update(member={} SAVE 전", member);
		
		member.update(dto);
		log.info("update(memeber={} SAVE 후", member);
	}

	public void checkPwd(String pwd) {
		log.info("checkPwd(pwd={})", pwd);
	
		
	}
}