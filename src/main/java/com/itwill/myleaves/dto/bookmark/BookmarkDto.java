package com.itwill.myleaves.dto.bookmark;

import com.itwill.myleaves.repository.bookmark.Bookmark;

import lombok.Data;

@Data
public class BookmarkDto {
	
	private Long PlanteriorId;
	private String userId;
}
