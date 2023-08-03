package com.itwill.myleaves.dto.faq;

import com.itwill.myleaves.repository.faq.FaQ;

import lombok.Data;

@Data
public class FaQCreateDto {
	
	
	private String question;
	private String answer;
	
	
	public FaQ toEntity() {
		return FaQ.builder()
				  .question(question)
				  .answer(answer)
				  .build();
	}
}
