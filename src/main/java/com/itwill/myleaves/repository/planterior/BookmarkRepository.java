package com.itwill.myleaves.repository.planterior;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BookmarkRepository extends JpaRepository<Bookmark, String> {

	@Transactional
	@Modifying
	@Query(value = "delete from BOOKMARK where PLANTERIOR_ID = ?1 and USER_ID = ?2", nativeQuery = true)
	void deleteByPlanteriorAndUserId(Long PlanteriorId, String userId);

	@Query(value = "SELECT * FROM BOOKMARK", nativeQuery = true)
	List<Bookmark> findAll();
}
