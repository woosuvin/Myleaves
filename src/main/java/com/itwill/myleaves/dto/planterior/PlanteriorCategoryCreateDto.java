package com.itwill.myleaves.dto.planterior;

import java.util.List;

import com.itwill.myleaves.repository.planterior.PlanteriorCategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlanteriorCategoryCreateDto {
	
	private Long pcid;
	private Long planteriorId;
	private String stateContent;
	private String conditionContent;
	
	public PlanteriorCategory toEntity() {
		return PlanteriorCategory.builder()
				.stateContent(stateContent)
				.conditionContent(conditionContent)
				.planteriorId(planteriorId)
				.pcid(pcid)
				.build();
	}

}
