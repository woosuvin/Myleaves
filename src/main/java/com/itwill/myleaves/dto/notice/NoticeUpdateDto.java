package com.itwill.myleaves.dto.notice;

import lombok.Data;

@Data
public class NoticeUpdateDto {
	
	private Long id;
	private String title;
	private String content;
	private int fix;
	
}
