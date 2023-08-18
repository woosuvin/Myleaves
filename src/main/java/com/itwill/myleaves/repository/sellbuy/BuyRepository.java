package com.itwill.myleaves.repository.sellbuy;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BuyRepository extends JpaRepository<Buy, Long> {

	List<Buy> findByOrderByBuyIdDesc();

	/**
	 * 마이페이지 입양, 분양 리스트
	 * @param BuyerId
	 * @return
	 */
	Page<Buy> findByBuyerId(String BuyerId, Pageable pageable);
	
	
	
}
