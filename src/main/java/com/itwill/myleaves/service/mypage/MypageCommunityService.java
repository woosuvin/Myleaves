package com.itwill.myleaves.service.mypage;

import java.util.List;

import org.springframework.stereotype.Service;

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
public class MypageCommunityService {
	private final CommunityRepository communityRepository;
	private final CommunityCommentRepository communityCommentRepository;

	// 마이페이지 내가 쓴 게시글 목록  
	public List<Community> read(String userId) {
		log.info("my_posts Service read()");
		List<Community> list = communityRepository.findByUserId(userId);

		return list;
	}
	
	
	// 마이페이지 내가 쓴 댓글 검색 (댓글 페이지 / 1)
	public List<CommunityComment> readComments(String userId) {
		log.info("my_comments Service read()");
		List<CommunityComment> list = communityCommentRepository.findByUserId(userId);
		return list;
	}
	
	// 마이페이지 내가 쓴 댓글로 게시글 검색(댓글 페이지 / 2)
		public Community readByCommunityId(Long communityId){
			Community community = communityRepository.findByCommunityId(communityId);
			return community;
		}

	
	// 마이페이지 게시글 검색 기능 	
	public List<Community> search(CommunitySearchDto dto) {
		log.info("search(dto)={}", dto);
		List<Community> list = null;
		switch (dto.getType()) {
		case "t":
			list = communityRepository.findByUserIdAndTitle(dto.getUserId(), dto.getKeyword());
			break;
		case "c":
			list = communityRepository.findByContentAndUserId(dto.getKeyword(), dto.getUserId());
			break;
		}
		
		log.info("lise size = {}", list.size());
		return list;
	}
	
//	public List<CommunityComment> readByUserId(String userId, CommunitySearchDto dto) {
//		
//		List<CommunityComment> list = null;
//		switch(dto.getType()) {
//		case "t":
//			communityRepository.findByUserIdAndTitle(userId, dto.getKeyword());
//			break;
//		case "c":	
//			communityCommentRepository.findByContentAndUserId(userId, dto.getKeyword());
//		}
//		return list;
//	}
	
}
