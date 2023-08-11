package com.itwill.myleaves.repository.community_comment;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.myleaves.repository.community.Community;

public interface CommunityCommentRepository extends JpaRepository<CommunityComment, Long>{
	
	// 찾은 Community에 달려 있는 댓글 목록을 검색. 
	List<CommunityComment> findByCommunityIdOrderByCommunityCommentIdDesc(Community communityId);

	// 게시물의 댓글 개수
	Long countByCommunityId(Community communityId);
//	Long countByCommunityId(Long communityId);
	
	Page<CommunityComment> findByUserId(String userId, Pageable pageable);
	
	@Transactional
	@Modifying
	@Query(value = "delete from community_comment where community_id = ?1", 
			nativeQuery = true)
	void deleteByCommunityId(Long communityId);


	void findByContentAndUserId(String userId, String keyword);

}
