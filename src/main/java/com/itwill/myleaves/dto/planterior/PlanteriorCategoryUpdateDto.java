package com.itwill.myleaves.dto.planterior;

import com.itwill.myleaves.repository.planterior.PlanteriorCategory;

import lombok.Data;

@Data
public class PlanteriorCategoryUpdateDto {
	
	private String stateContent;
	private String conditionContent;
	private Long planteriorId;
	
	public PlanteriorCategory toEntity() {
		return PlanteriorCategory.builder()
				.planteriorId(planteriorId)
				.conditionContent(conditionContent)
				.stateContent(stateContent)
				.build();
	}
}
