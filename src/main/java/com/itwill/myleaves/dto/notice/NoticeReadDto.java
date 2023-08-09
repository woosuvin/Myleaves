package com.itwill.myleaves.dto.notice;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class NoticeReadDto {
	
	private int rn;
	private Long nid;
	private String title;
    private String content;
    private int views;
    private int fix;
    private LocalDateTime createdDate;    
    
}
