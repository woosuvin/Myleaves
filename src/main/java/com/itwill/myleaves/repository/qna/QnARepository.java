package com.itwill.myleaves.repository.qna;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QnARepository extends JpaRepository<QnA, Long> {
	// Id 내림차순 정렬: 
    // select * from QNA_INFO order by ID desc
    List<QnA> findByOrderByQidDesc();
}
