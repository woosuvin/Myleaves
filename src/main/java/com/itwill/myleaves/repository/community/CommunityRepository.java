package com.itwill.myleaves.repository.community;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.myleaves.repository.community_comment.CommunityComment;

public interface CommunityRepository extends JpaRepository<Community, Long>{

	List<Community> findByOrderByCommunityIdDesc();

	List<Community> findByUserId(String userId);
	
	Community findByCommunityId(Long communityId);

	List<Community> findByTitleContainsIgnoreCaseOrderByCommunityIdDesc(String title);

	List<Community> findByContentContainsIgnoreCaseOrderByCommunityIdDesc(String content);


	List<Community> findByUserIdContainsIgnoreCaseOrderByCommunityIdDesc(String userId);

	
	@Query(
	           "select c from Community c" + 
               " where lower(c.title) like lower('%' || :keyword || '%') " +
               " or lower(c.content) like lower('%' || :keyword || '%') "  +
               " order by c.communityId desc"
			)
	List<Community> searchByKeyword(@Param("keyword") String keyword);

	
	
	
    List<Community> findByUserIdAndTitle(String userId, String title);

	List<Community> findByContentAndUserId(String userId, String content);

	 
}
