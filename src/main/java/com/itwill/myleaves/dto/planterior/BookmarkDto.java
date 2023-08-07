package com.itwill.myleaves.dto.planterior;

import com.itwill.myleaves.repository.planterior.Bookmark;

import lombok.Data;

@Data
public class BookmarkDto {
	
	private Long PlanteriorId;
	private String userId;
	private Long BId;
	
	public Bookmark toEntity() {
		return Bookmark.builder()
				.planteriorId(PlanteriorId)
				.userId(userId)
				.BId(BId)
				.build();
	}
}
