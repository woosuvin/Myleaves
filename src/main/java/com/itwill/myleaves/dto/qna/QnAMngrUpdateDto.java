package com.itwill.myleaves.dto.qna;

import com.itwill.myleaves.repository.qna.QnA;

import lombok.Data;

@Data
public class QnAMngrUpdateDto {
	private String an_title;
	private String an_content;
	// 시간?...
	
	
	public QnA toEntity() {
		return QnA.builder()
					  .an_title(an_title)
					  .an_content(an_content)
					  .build();
	}
}	
