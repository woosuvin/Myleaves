package com.itwill.myleaves.service.palnterior;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.myleaves.dto.planterior.PlanteriorUpdateDto;
import com.itwill.myleaves.repository.planterior.Planterior;
import com.itwill.myleaves.repository.planterior.PlanteriorRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MypageService {
	
	private final PlanteriorRepository planteriorRepository;
	
	// 내가 쓴 글 가져오기
	@Transactional(readOnly = true)
	public List<Planterior> read(String userId) {
		log.info("read()");
		
		return planteriorRepository.findAllByUserIdOrderByPlanteriorIdDesc(userId);
	}
	
	// 수정페이지에 가져오는 것
	@Transactional(readOnly = true)
	public Planterior read(Long planteriorId ) {
		log.info("read()");
		
		return planteriorRepository.findAllByPlanteriorId(planteriorId);
	}
	
	// 수정
	public Planterior update(PlanteriorUpdateDto dto) {
		log.info("update(dto = {})", dto);
		
		Planterior entity = planteriorRepository.findById(dto.getPlanteriorId()).orElseThrow();
		entity.update(dto);
		return planteriorRepository.saveAndFlush(entity);
	}
	
	// 삭제
	public void delete(Long planteriorId) {
		log.info("delete(planteriorId = {})", planteriorId);
		
		Planterior entity = planteriorRepository.findByPlanteriorId(planteriorId);
		planteriorRepository.delete(entity);
	}
	

}
