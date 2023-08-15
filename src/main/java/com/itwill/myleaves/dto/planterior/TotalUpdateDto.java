package com.itwill.myleaves.dto.planterior;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class TotalUpdateDto {
	private Long planteriorId;
	private String userId;
	private String plantName;
	private String content; // 무조건 빈 문자열 넣기.
	private String plantNameEnglish;
	private byte[] thumbnail;
	
	private Long pcid;
	private String stateContent;
	private String conditionContent;
	
	private MultipartFile file;
	
	public PlanteriorUpdateDto planteriorUpdateDto() {
		return new PlanteriorUpdateDto(planteriorId, userId, plantName, plantNameEnglish, thumbnail, file); 
	}
	
	public PlanteriorCategoryUpdateDto planteriorCategoryUpdateDto() {
		return new PlanteriorCategoryUpdateDto(pcid, stateContent, conditionContent, planteriorId);
	}
}
