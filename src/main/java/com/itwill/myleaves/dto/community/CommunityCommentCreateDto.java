package com.itwill.myleaves.dto.community;

import lombok.Data;

@Data
public class CommunityCommentCreateDto {
	private long communityId; // 커뮤니티 ID 
	private String content;
	private String userId;
}
