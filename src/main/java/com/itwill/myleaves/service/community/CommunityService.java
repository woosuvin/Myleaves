package com.itwill.myleaves.service.community;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.myleaves.dto.community.CommunityCreateDto;
import com.itwill.myleaves.dto.community.CommunitySearchDto;
import com.itwill.myleaves.dto.community.CommunityUpdateDto;
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
	public Page<Community> read(Pageable pageable) {
		log.info("read()");

		return communityRepository.findAll(pageable);
	}
	

	// DB COMMUNITY 테이블에 엔터티 insert 
	public Community create(CommunityCreateDto dto) {
		log.info("create(dto={})", dto);

		
		// DTO를 Entity로 변환:
		Community entity = dto.toEntity();
		log.info("entity={}", entity);
		
		// DB 테이블에 저장 
		communityRepository.save(entity);
		log.info("entity={}", entity);
		
		return entity;
	}

    @Transactional(readOnly = true)
	public Community read(Long communityId) {
		log.info("read(communityId={})", communityId);
		
		
		return communityRepository.findById(communityId).orElseThrow();
	}


	public void delete(Long communityId) {
		log.info("delete(communityId={})", communityId);
		
		communityRepository.deleteById(communityId);
		
	}


	@Transactional
	public void update(CommunityUpdateDto dto) {
		log.info("update(dto={})", dto.getCommunityId());
		Community entity = communityRepository.findById(dto.getCommunityId()).orElseThrow();
		
		entity.update(dto);
	}


	@Transactional(readOnly = true)
	public Page<Community> search(CommunitySearchDto dto, Pageable pageable) {
		log.info("search(dto)={}", dto);
		
		Page<Community> list = null;
		switch (dto.getType()) {
		case "t":
			list = communityRepository.findByTitleContainsIgnoreCaseOrderByCommunityIdDesc(dto.getKeyword(), pageable);
			break;
		case "c":
			list = communityRepository.findByContentContainsIgnoreCaseOrderByCommunityIdDesc(dto.getKeyword(), pageable);
			break;
		case "tc":
			list = communityRepository.searchByKeyword(dto.getKeyword(), pageable);
			break;
		case "u":
			list = communityRepository.findByUserIdContainsIgnoreCaseOrderByCommunityIdDesc(dto.getKeyword(), pageable);
			break;
		}
		return list;
	}
	
	
	
}
