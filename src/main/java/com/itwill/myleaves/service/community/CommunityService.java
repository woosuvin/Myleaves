package com.itwill.myleaves.service.community;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.myleaves.repository.community.Community;
import com.itwill.myleaves.repository.community.CommunityRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommunityService {

	private final CommunityRepository communityRepository; 

	// 커뮤니티 게시글 전체 리스트
	@Transactional(readOnly = true)
	public List<Community> read() {
		log.info("read()");

		return communityRepository.findByOrderByCommunityIdDesc();
	}
	
	
}
