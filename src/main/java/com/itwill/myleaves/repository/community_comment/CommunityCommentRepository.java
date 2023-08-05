package com.itwill.myleaves.repository.community_comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.myleaves.repository.community.Community;

public interface CommunityCommentRepository extends JpaRepository<CommunityComment, Long>{
	
	// 찾은 Community에 달려 있는 댓글 목록을 검색. 
	List<CommunityComment> findByCommunityId(Community communityId);

	// 게시물의 댓글 개수
//	Long countByCommunity(Community post);
	Long countByCommunityId(Long communityId);
}
