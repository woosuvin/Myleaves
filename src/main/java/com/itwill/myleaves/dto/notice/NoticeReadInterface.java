package com.itwill.myleaves.dto.notice;

import java.time.LocalDateTime;

public interface NoticeReadInterface {
	
	Integer getRn();
	
	Long getNid();
	
	String getTitle();
	
	String getContent();
	
	Long getViews();
	
	Integer getFix();
	
	LocalDateTime getCreated_Date();
}
