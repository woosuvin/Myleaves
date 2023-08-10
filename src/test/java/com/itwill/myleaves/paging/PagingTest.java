package com.itwill.myleaves.paging;


import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.myleaves.MyleavesApplication;
import com.itwill.myleaves.repository.member.Member;
import com.itwill.myleaves.repository.member.MemberRepository;
import com.itwill.myleaves.repository.mngr.Criteria;
import com.itwill.myleaves.repository.mngr.PageDto;
import com.itwill.myleaves.service.member.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(classes = MyleavesApplication.class)
public class PagingTest {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberRepository memberRepository;
	
//	@Test
	public void testPagingAndSearch() {
		int size = memberService.read();
		
		Criteria cri = new Criteria();
		PageDto pageDto = new PageDto(cri, size);
		
		
	}
	
	@Test
    public void testPageDto() {
        Criteria cri = new Criteria();

        PageDto pageDto = new PageDto(cri, 250);

        log.info("testPageDto(pageDto={})", pageDto);
        
//        List<Member> members = memberRepository.readByEmail(cri.getPageNum(), cri.getAmount(), "test@test.com");
//        log.info("testPageDto(members={})", members);
        
//        List<Member> members = memberRepository.readByPhone(cri.getPageNum(), cri.getAmount(), "01012345678");
//        log.info("testPageDto(members={})", members);
        
//        List<Member> members = memberRepository.readByUserId(cri.getPageNum(), cri.getAmount(), "test1");
//        log.info("testPageDto(members={})", members);
        
//        List<Member> members = memberRepository.readWithPagingByBirth(cri.getPageNum(), cri.getAmount(), "20000101");
//        log.info("testPageDto(members={})", members);
        
//        List<Member> members = memberRepository.readWithPagingByGender(cri.getPageNum(), cri.getAmount(), "F");
//        log.info("testPageDto(members={})", members);
        
        List<Member> members = memberRepository.readWithPagingByName(cri.getPageNum(), cri.getAmount(), "test");
        log.info("testPageDto(members={})", members);
    }
}