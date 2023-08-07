package com.itwill.myleaves.dto.planterior;

import com.itwill.myleaves.repository.planterior.Planterior;

import lombok.Data;

@Data
public class PlanteriorUpdateDto {
	
	private Long planteriorId;
	private String userId;
	private String plantName;
	private String plantNameEnglish;
	private byte[] thumbnail;
	
	public Planterior toEntity() {
		return Planterior.builder()
				.userId(userId)
				.plantName(plantName)
				.plantNameEnglish(plantNameEnglish)
				.thumbnail(thumbnail)
				.build();
	}
	
}
