package com.itwill.myleaves.dto.faq;

import lombok.Data;

@Data
public class FaQUpdateDto {
	private Long fqid;
	
	private String question;
	
	private String answer;
}
