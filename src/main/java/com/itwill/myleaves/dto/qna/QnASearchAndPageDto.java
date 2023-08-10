package com.itwill.myleaves.dto.qna;

import lombok.Data;

@Data
public class QnASearchAndPageDto {
	private String type; // 검색 키워드
    private String keyword; // 검색 유형
}
