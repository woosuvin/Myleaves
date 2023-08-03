package com.itwill.myleaves.repository.faq;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FaQRepository extends JpaRepository<FaQ, Long> {
	
	// select * from FAQ_INFO order by ID desc
	List<FaQ> findByOrderByFqidDesc();
}
