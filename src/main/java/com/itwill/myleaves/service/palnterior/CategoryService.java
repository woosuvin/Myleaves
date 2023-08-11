package com.itwill.myleaves.service.palnterior;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.myleaves.dto.planterior.PlanteriorCategoryCreateDto;
import com.itwill.myleaves.dto.planterior.PlanteriorCreateDto;
import com.itwill.myleaves.repository.planterior.PlanteriorCategory;
import com.itwill.myleaves.repository.planterior.PlanteriorCategoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

	private final PlanteriorCategoryRepository planteriorCategoryRepository;

	public List<PlanteriorCategory> findState(String stateContent) {
		log.info("fidnState(stateContent ={})", stateContent);

		return planteriorCategoryRepository.findAllByStateContent(stateContent);
	}

	public List<PlanteriorCategory> findStateAndCondition(String stateContent, String conditionContent) {
		log.info("findStateAndCondition(stateContent = {}, conditionContent = {})", stateContent, conditionContent);

		return planteriorCategoryRepository.findAllByStateContentAndConditionContent(stateContent, conditionContent);
	}

	public PlanteriorCategory create(PlanteriorCategoryCreateDto dto) {
		log.info("create(dto = {})", dto);

		// 변환
		PlanteriorCategory entity = dto.toEntity();
		log.info("save전 entity{} = ", entity);

		planteriorCategoryRepository.save(entity);
		log.info("save 후 entity={} = ", entity);

		return entity;

	}

}
