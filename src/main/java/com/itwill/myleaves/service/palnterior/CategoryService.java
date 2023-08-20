package com.itwill.myleaves.service.palnterior;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.myleaves.dto.planterior.PlanteriorCategoryCreateDto;
import com.itwill.myleaves.dto.planterior.PlanteriorCategoryUpdateDto;
import com.itwill.myleaves.dto.planterior.PlanteriorCreateDto;
import com.itwill.myleaves.dto.planterior.PlanteriorUpdateDto;
import com.itwill.myleaves.repository.planterior.Planterior;
import com.itwill.myleaves.repository.planterior.PlanteriorCategory;
import com.itwill.myleaves.repository.planterior.PlanteriorCategoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

	private final PlanteriorCategoryRepository planteriorCategoryRepository;

	@Transactional(readOnly = true)
	public PlanteriorCategory read(Long planteriorId) {
		//log.info("read()");

		return planteriorCategoryRepository.findAllByPlanteriorId(planteriorId);
	}
	
	
	public List<PlanteriorCategory> findState(String stateContent) {
		//log.info("fidnState(stateContent ={})", stateContent);

		return planteriorCategoryRepository.findAllByStateContent(stateContent);
	}

	public List<PlanteriorCategory> findStateAndCondition(String stateContent, String conditionContent) {
		//log.info("findStateAndCondition(stateContent = {}, conditionContent = {})", stateContent, conditionContent);

		return planteriorCategoryRepository.findAllByStateContentAndConditionContentContainsIgnoreCase(stateContent,
				conditionContent);
	}

	public PlanteriorCategory create(PlanteriorCategoryCreateDto dto) {
		//log.info("create(dto = {})", dto);

		// 변환
		PlanteriorCategory entity = dto.toEntity();
		//log.info("save전 entity{} = ", entity);

		planteriorCategoryRepository.save(entity);
		//log.info("save 후 entity={} = ", entity);

		return entity;

	}

	// 수정
	public void update(PlanteriorCategoryUpdateDto dto) {
		//log.info("update(dto = {})", dto);

		PlanteriorCategory entity = planteriorCategoryRepository.findById(dto.getPcid()).orElseThrow();
		entity.update(dto);
	}

}
