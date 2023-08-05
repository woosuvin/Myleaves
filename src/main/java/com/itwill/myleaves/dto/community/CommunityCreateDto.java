package com.itwill.myleaves.dto.community;

import com.itwill.myleaves.repository.community.Community;

import lombok.Data;

@Data
public class CommunityCreateDto {
	private String hrsHd;
	private String title;
	private String content;
	private String userId;
	
	// Dto를 엔터티 객체로 변환해서 리턴하는 메서드:
	public Community toEntity() {
	    return Community.builder()
	    		.hrsHd(hrsHd)
	    		.title(title)
	    		.content(content)
	    		.userId(userId)
	    		.build();
	            
	}

}
