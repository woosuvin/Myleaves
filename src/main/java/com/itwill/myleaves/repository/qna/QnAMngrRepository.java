package com.itwill.myleaves.repository.qna;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QnAMngrRepository extends JpaRepository<QnAMngr, Long>{
	// 혹시 모르니 답변 리스트 가져옴....
	List<QnAMngr> findByOrderByQidDesc();
}
