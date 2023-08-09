package com.itwill.myleaves.dto.planterior;

import com.itwill.myleaves.repository.planterior.PlanteriorCategory;

import lombok.Data;

@Data
public class PlanteriorCategoryCreateDto {
	
	private String stateContent;
	private String conditionContent;
	private Long planteriorId;
	
	public PlanteriorCategory toEntity() {
		return PlanteriorCategory.builder()
				.stateContent(stateContent)
				.conditionContent(conditionContent)
				.planteriorId(planteriorId)
				.build();
	}

}
