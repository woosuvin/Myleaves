package com.itwill.myleaves.dto.planterior;

import lombok.Data;

@Data
public class PlanteriorUpdateDto {
	
	private String userId;
	private String plantName;
	private String plantNameEnglish;
	private byte[] thumbnail;
	
}
