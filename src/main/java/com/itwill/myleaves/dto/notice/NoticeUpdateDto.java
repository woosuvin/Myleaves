package com.itwill.myleaves.dto.notice;

import lombok.Data;

@Data
public class NoticeUpdateDto {
	
	private Long nid;
	private String title;
	private String content;
	private int fix;
	
}
