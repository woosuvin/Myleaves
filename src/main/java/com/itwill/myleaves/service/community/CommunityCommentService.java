package com.itwill.myleaves.service.community;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.myleaves.dto.community.CommunityCommentCreateDto;
import com.itwill.myleaves.dto.community.CommunityCommentUpdateDto;
import com.itwill.myleaves.dto.community.CommunitySearchDto;
import com.itwill.myleaves.repository.community.Community;
import com.itwill.myleaves.repository.community.CommunityRepository;
import com.itwill.myleaves.repository.community_comment.CommunityComment;
import com.itwill.myleaves.repository.community_comment.CommunityCommentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommunityCommentService {
	
	private final CommunityCommentRepository communityCommentRepository;
	private final CommunityRepository communityRepository;
	
	@Transactional(readOnly = true)
	public List<CommunityComment> read(long id) {
		log.info("read(communityId={})", id);
		
		// 1. communityId로 게시글 검색 
		Community communityId = communityRepository.findById(id).orElseThrow();
		
		// 2. 찾은 게시글에 달려있는 댓글 목록을 검색 
		List<CommunityComment> list = communityCommentRepository.findByCommunityIdOrderByCommunityCommentIdDesc(communityId);
		
		return list;
	}

	
	public CommunityComment create(CommunityCommentCreateDto dto) {
		log.info("create(dto={})", dto);
		
		// 1. dto의 커뮤니티 ID로 Community 엔터티 검색
		Community communityId = communityRepository.findById(dto.getCommunityId()).orElseThrow();
		
		// 2. CommunityCommentCreateDto 객체를 CommunityComment 객체로 변환
		CommunityComment entity = CommunityComment.builder()
									.communityId(communityId)
									.content(dto.getContent())
									.userId(dto.getUserId())
									.build();
		// 	//.communityId(communityId.getCommunityId())

		
		// 3. DB COMMUNITY_COMMENT 테이블에 insert
		communityCommentRepository.save(entity);
		
				
		return entity;
	}

	
	public void deleteComment(long communityId) {
		communityCommentRepository.deleteByCommunityId(communityId);
	}
	
	public void delete(long communityCommentId) {
		log.info("delete(communityCommentId={})", communityCommentId);
		
		// DB COMMUNITY_COMMENT 테이블에서 ID로 엔터티 삭제
		communityCommentRepository.deleteById(communityCommentId);
	}

	@Transactional
	public void update(long communityCommentId, CommunityCommentUpdateDto dto) {
		log.info("update(communityCommentId={}, dto={})", communityCommentId, dto);
		
		// 1. 댓글 아이디로 DB에서 엔터티 검색
		CommunityComment entity = communityCommentRepository.findById(communityCommentId).orElseThrow();
		
		// 2. 검색한 엔터티의 프로퍼티를 수정
		entity.update(dto.getContent());
		
	}

	// 해당 게시글의 댓글 개수 
	public long countByCommunityId(Community communityId) {
		log.info("countByCommunity(communityId={})", communityId);
		
		// return communityCommentRepository.countByCommunityId(communityId.getCommunityId());
		 return communityCommentRepository.countByCommunityId(communityId);
	}
}
