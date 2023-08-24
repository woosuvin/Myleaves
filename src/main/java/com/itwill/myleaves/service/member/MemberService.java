package com.itwill.myleaves.service.member;

import java.time.Month;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.itwill.myleaves.dto.member.MemberGenderDto;
import com.itwill.myleaves.dto.member.MemberJoinDateDto;
import com.itwill.myleaves.dto.member.MemberSearchDto;
import com.itwill.myleaves.dto.member.MemberSignUpDto;
import com.itwill.myleaves.dto.member.MemberUpdateDto;
import com.itwill.myleaves.repository.kakao.KakaoProfile;
import com.itwill.myleaves.repository.member.Member;
import com.itwill.myleaves.repository.member.MemberRepository;
import com.itwill.myleaves.repository.mngr.Criteria;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	@Value("${cos.key}")
	private String cosKey;

	// 회원가입
	public String registerMember(MemberSignUpDto dto) {
//		log.info("registerMember(dto={})", dto);

		Member entity = Member.builder().userId(dto.getUserId()).name(dto.getName())
				.pwd(passwordEncoder.encode(dto.getPwd())).gender(dto.getGender()).birth(dto.getBirth())
				.phone(dto.getPhone()).email(dto.getEmail()).build();

//		log.info("save 전: entity={}", entity);

//		memberRepository.save(entity);
//		log.info("save 후: entity={}", entity);

		return entity.getUserId();
	}

	@Transactional
	public void registerMember(Member kakaoMember) {
//		log.info("registerMember(dto={})", kakaoMember);

//		UUID garbagePwd = UUID.randomUUID();
//		log.info("registerMember(cosKey={})", cosKey);
//
//		Member entity = Member.builder().userId(kakaoProfile.getId())
//				.name(kakaoProfile.getKakao_account().getProfile().getNickname())
//				.pwd(cosKey).email(kakaoProfile.getKakao_account().getEmail())
//				.build();

		memberRepository.save(kakaoMember);
	}

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
//		log.info("loadUserByUsername(userId={})", userId);

		UserDetails user = memberRepository.findByUserId(userId);
//		log.info("loadUserByUsername(user={})", user);
		
		if (user != null) {
			return user;
		}

		throw new UsernameNotFoundException(userId + "not found");
	}

	/**
	 * 전체 회원수 반환
	 * 
	 * @return 전체 회원수
	 */
	public int read() {
//		log.info("read()");

		List<Member> members = memberRepository.findAll();
		int size = members.size();

		return size;
	}

	/**
	 * 이름, email 일치하는 사용자의 userId 리턴
	 * 
	 * @param name
	 * @param email
	 * @return userId
	 */
	public String read(String name, String email) {
//		log.info("read(name={}, email={})", name, email);

		Member entity = memberRepository.findByNameAndEmail(name, email);

		if (entity == null) {
			return "회원정보가 존재하지 않습니다.";
		} else {
			String userId = entity.getUserId();

//			log.info("read={}", userId);
			return userId;
		}
	}

	@Transactional
	public String modifyPwd(String userId) {
//		log.info("modifyPwd()");

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
//		log.info("update(dto={})", dto);

		Member member = memberRepository.findByUserId(dto.getUserId());
//		log.info("update(member={} SAVE 전", member);

		member.update(dto);
//		log.info("update(memeber={} SAVE 후", member);
	}

	public List<Member> readWithPaging(Criteria cri) {
//		log.info("readWithPaging()");

		List<Member> members = memberRepository.readWithPaging(cri.getPageNum(), cri.getAmount());
//	    log.info("read(members={})", members);

		return members;
	}

	public List<Member> readWithPagingAndSearch(Criteria cri, MemberSearchDto dto) {
//		log.info("readWithPagingAndSearch(cri={}, dto={})", cri, dto);

		List<Member> members = null;

		switch (dto.getType()) {

		case "name":
			members = memberRepository.readWithPagingByName(cri.getPageNum(), cri.getAmount(), dto.getKeyword());
			break;
		case "userId":
			members = memberRepository.readByUserId(cri.getPageNum(), cri.getAmount(), dto.getKeyword());
			break;
		case "phone":
			members = memberRepository.readByPhone(cri.getPageNum(), cri.getAmount(), dto.getKeyword());
			break;
		case "eamil":
			members = memberRepository.readByEmail(cri.getPageNum(), cri.getAmount(), dto.getKeyword());
			break;
		case "birth":
			members = memberRepository.readWithPagingByBirth(cri.getPageNum(), cri.getAmount(), dto.getKeyword());
			break;
		case "gender":
			members = memberRepository.readWithPagingByGender(cri.getPageNum(), cri.getAmount(), dto.getKeyword());
			break;
		}

		return members;
	}

	/**
	 * 키워드별 사이즈 가져오기
	 * 
	 * @param dto
	 * @return
	 */
	public int read(MemberSearchDto dto) {
//		log.info("read(dto={})", dto);

		int size = 0;

		switch (dto.getType()) {
		case "name":
			size = memberRepository.countByName(dto.getKeyword());
			break;
		case "userId":
			size = memberRepository.countByUserId(dto.getKeyword());
			break;
		case "phone":
			size = memberRepository.countByPhone(dto.getKeyword());
			break;
		case "email":
			size = memberRepository.countByEmail(dto.getKeyword());
			break;
		case "birth":
			size = memberRepository.countByBirth(dto.getKeyword());
			break;
		case "gender":
			size = memberRepository.countByGender(dto.getKeyword());
			break;
		}

		return size;
	}

	public String read(String inputId) {
//		log.info("read(inputId={})", inputId);

		try {
			return memberRepository.findByUserId(inputId).getUserId();
		} catch (Exception e) {
			return "fail";
		}
	}

	/**
	 * 시각화
	 * 
	 * @return
	 */
	public String readGender() {

		List<Member> members = memberRepository.findAll();

		List<MemberGenderDto> genders = new ArrayList<>();
		MemberGenderDto genderDto = new MemberGenderDto();

		int maleCount = 0;
		int femaleCount = 0;

		for (Member member : members) {
			String gender = member.getGender();
			if ("M".equals(gender)) {
				maleCount++;
			} else if ("F".equals(gender)) {
				femaleCount++;
			}
		}

		genderDto.setGender("M");
		genderDto.setCnt(maleCount);
		genders.add(genderDto);

		genderDto = new MemberGenderDto();
		genderDto.setGender("F");
		genderDto.setCnt(femaleCount);
		genders.add(genderDto);

		Gson gson = new Gson();
		JsonArray jArray = new JsonArray();

		Iterator<MemberGenderDto> it = genders.iterator();
		while (it.hasNext()) {
			MemberGenderDto curVO = it.next();
			JsonObject object = new JsonObject();
			String gender = curVO.getGender();
			int cnt = curVO.getCnt();

			object.addProperty("gender", gender);
			object.addProperty("cnt", cnt);
			jArray.add(object);
		}

		String json = gson.toJson(jArray);

		return json;
	}

	/**
	 * 시각화
	 * 
	 * @return
	 */
	public String readMonth() {
		List<Member> members = memberRepository.findAll();

		List<MemberJoinDateDto> months = new ArrayList<>();
		for (int i = 1; i <= 12; i++) {
			MemberJoinDateDto monthDto = new MemberJoinDateDto();
			monthDto.setMonth(i);
			monthDto.setCnt(0); // Initialize count to 0

			months.add(monthDto);
		}

		for (Member member : members) {
			// Assuming joinDate is of type LocalDateTime

			try {
				Month joinMonth = member.getJoinDate().getMonth();

				for (MemberJoinDateDto monthDto : months) {
					if (monthDto.getMonth() == joinMonth.getValue()) {
						monthDto.setCnt(monthDto.getCnt() + 1);
						break;
					}
				}
			} catch (Exception e) {
//        		log.info(null);
			}
		}

		Gson gson = new Gson();
		JsonArray jArray = new JsonArray();

		for (MemberJoinDateDto monthDto : months) {
			JsonObject object = new JsonObject();
			object.addProperty("month", monthDto.getMonth());
			object.addProperty("cnt", monthDto.getCnt());
			jArray.add(object);
		}

		String json = gson.toJson(jArray);

		return json;
	}
	@Transactional(readOnly = true)
	public Member findMember(String userId) {
//		log.info("findMember(userId={})", userId);

		return memberRepository.findByUserId(userId);
	}
}