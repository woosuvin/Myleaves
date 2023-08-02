package com.itwill.myleaves.repository.sellbuy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SellRepository extends JpaRepository<Sell, Long>{

	//id 내림차순
	List<Sell> findByOrderBySellIdDesc();
	
}
