package com.itwill.myleaves.repository.planterior;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanteriorCategoryRepository extends JpaRepository<PlanteriorCategory, Long> {
	
	// 상태 카테고리 선택시 플랜테리어 아이디 검색됨
	List<PlanteriorCategory> findAllByStateContent(String stateContent);

	
	// 상태 + 조건 카테고리 선택시 플랜테리어 아이디 검색됨.
	List<PlanteriorCategory> findAllByStateContentAndConditionContentContainsIgnoreCase(String stateContent, String conditionContent);

	// 검색 결과 수
	//Long countcountByPlanteriorId(Long planteriorId);
}
