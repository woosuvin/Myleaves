package com.itwill.myleaves.dto.community;

import lombok.Data;

@Data
public class CommunityCommentUpdateDto {
	private Long communityCommentId; // 댓글 ID
	private String title;
	private String content;
}
