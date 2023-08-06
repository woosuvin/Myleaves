package com.itwill.myleaves.repository.qna;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QnARepository extends JpaRepository<QnA, Long> {
    // select * from QNA_INFO order by ID desc
    List<QnA> findByOrderByQidDesc();
    
    
    
    
    
    // 검색 기능
    
    // 시간 남으면 페이징 기능 추가
}
