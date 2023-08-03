package com.itwill.myleaves.dto.planterior;

import com.itwill.myleaves.repository.Planterior.Planterior;

import lombok.Data;

@Data
public class PlanteriorCreateDto {

	private Long planteriorId;
	private String userId;
	private String plantName;
	private String content; // 무조건 빈 문자열 넣기.
	private String plantNameEnglish;
	// clob string, blob byte[]
	private byte[] thumbnail;
	
	public Planterior toEntity() {
		return Planterior.builder()
				.planteriorId(planteriorId)
				.userId(userId)
				.plantName(plantName)
				.content(content)
				.plantNameEnglish(plantNameEnglish)
				.thumbnail(thumbnail)
				.build();
	}

}
