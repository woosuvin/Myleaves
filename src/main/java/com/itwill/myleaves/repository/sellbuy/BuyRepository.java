package com.itwill.myleaves.repository.sellbuy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyRepository extends JpaRepository<Buy, Long> {
	
	List<Buy> findByOrderByBuyIdDesc();
	
}
