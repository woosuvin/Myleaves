package com.itwill.myleaves.dto.planterior;

import com.itwill.myleaves.repository.planterior.PlanteriorCategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlanteriorCategoryUpdateDto {
	
	private Long pcid;
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
