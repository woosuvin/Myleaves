package com.itwill.myleaves.repository.community;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CommunityRepository extends JpaRepository<Community, Long>{
	
	List<Community> findByOrderByCreatedDateDesc();

//	Page<Community> findByOrderByCommunityIdDesc(Pageable pageable);
	Page<Community> findAll(Pageable pageable);
	
	Page<Community> findByUserId(String userId, Pageable pageable);
	
	Community findByCommunityId(Long communityId);

	Page<Community> findByTitleContainsIgnoreCaseOrderByCommunityIdDesc(String title, Pageable pageable);

	Page<Community> findByContentContainsIgnoreCaseOrderByCommunityIdDesc(String content, Pageable pageable);


	Page<Community> findByUserIdContainsIgnoreCaseOrderByCommunityIdDesc(String userId, Pageable pageable);

	
	@Query(
	           "select c from Community c" + 
               " where lower(c.title) like lower('%' || :keyword || '%') " +
               " or lower(c.content) like lower('%' || :keyword || '%') "  +
               " order by c.communityId desc"
			)
	Page<Community> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

	
	
	
	Page<Community> findByUserIdAndTitle(String userId, String title, Pageable pageable);

	Page<Community> findByContentAndUserId(String userId, String content, Pageable pageable);

	 
}
