package com.itwill.myleaves.dto.planterior;

import java.util.List;

import com.itwill.myleaves.repository.planterior.Planterior;

import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class TotalCreateDto {
	private Long planteriorId;
	private String userId;
	private String plantName;
	private String content; // 무조건 빈 문자열 넣기.
	private String plantNameEnglish;
	// clob string, blob byte[]
	private byte[] thumbnail;
	
	private Long pcid;
	private String stateContent;
	private String conditionContent;
	
	public PlanteriorCreateDto planteriorCreateDto() {
		return new PlanteriorCreateDto(planteriorId, userId, plantName, content, plantNameEnglish, thumbnail);
	}
	
	public PlanteriorCategoryCreateDto planteriorCategoryCreateDto(Long planteriorId) {
		return new PlanteriorCategoryCreateDto(pcid, planteriorId, stateContent, conditionContent);
	}
	


}
