package com.itwill.myleaves.dto.qna;

import com.itwill.myleaves.repository.qna.QnA;

import lombok.Data;

@Data
public class QnACreateDto {
	
	private String title;
	private String content;
	private String user_id;
	private long secret; // check 0,1
	
				
	public QnA toEntity() {
		return QnA.builder()
				.title(title)
				.content(content)
				.user_id(user_id)
				.secret(secret)
				.build();
	}
}
