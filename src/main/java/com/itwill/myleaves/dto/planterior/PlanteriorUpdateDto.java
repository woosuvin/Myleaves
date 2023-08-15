package com.itwill.myleaves.dto.planterior;

import org.springframework.web.multipart.MultipartFile;

import com.itwill.myleaves.repository.planterior.Planterior;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlanteriorUpdateDto {
	
	private Long planteriorId;
	private String userId;
	private String plantName;
	private String plantNameEnglish;
	private byte[] thumbnail;
	
	private MultipartFile file;
	
	public Planterior toEntity() {
		return Planterior.builder()
				.userId(userId)
				.plantName(plantName)
				.plantNameEnglish(plantNameEnglish)
				.thumbnail(thumbnail)
				.build();
	}
	
}
