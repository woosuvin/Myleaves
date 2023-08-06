package com.itwill.myleaves.repository.sellbuy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;

public interface BuyRepository extends JpaRepository<Buy, Long> {

	List<Buy> findByOrderByBuyIdDesc();

	/**
	 * 마이페이지 입양, 분양 리스트
	 * @param BuyerId
	 * @return
	 */
	List<Buy> findByBuyerId(String BuyerId);
	
}
