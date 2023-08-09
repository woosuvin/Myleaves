package com.itwill.myleaves.dto.community;


import lombok.Data;

@Data
public class CommunityUpdateDto {
	private Long communityId;
	private String hrsHd;
	private String title;
	private String content;
	
}
