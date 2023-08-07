package com.itwill.myleaves.repository.planterior;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

// CRUD 작업 entity class 이름 작성. sql 작성하기.
public interface PlanteriorRepository extends JpaRepository<Planterior, Long> {
	
	// userid, plantname, 영문, 썸네일
	// planterior id = sequence
	// content는 null로 들어갈 거임.
	// 일단 이미지 제외하고 시작.
	List<Planterior> findAllByOrderByPlanteriorIdDesc();
	
	// mypage내가 쓴 글
	List<Planterior> findAllByUserIdOrderByPlanteriorIdDesc(String userId);
	
	Planterior findAllByPlanteriorId(Long PlanteriorId);
	
	Planterior findByPlanteriorId(Long PlanteriorId);
	
	
	
	
}
