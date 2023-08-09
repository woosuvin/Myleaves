package com.itwill.myleaves.dto.qna;

import com.itwill.myleaves.repository.qna.QnA;

import lombok.Data;

@Data
public class QnACreateDto {
	
	private String title;
	private String content;
	private String userId;
	private long secret; // check 0,1
	/*
	 * private String an_title; private String an_content;
	 */
				
	public QnA toEntity() {
		return QnA.builder()
				.title(title)
				.content(content)
				.userId(userId)
				.secret(secret)
				.build();
	}
}
