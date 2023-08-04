package com.itwill.myleaves.dto.qna;

import com.itwill.myleaves.repository.qna.QnAMngr;

import lombok.Data;

@Data
public class QnAMngrUpdateDto {
	private Long qid;
	private String an_title;
	private String an_content;
	// 시간?...
	
	
	public QnAMngr toEntity() {
		return QnAMngr.builder()
					  .qid(qid)
					  .an_title(an_title)
					  .an_content(an_content)
					  .build();
	}
}	
