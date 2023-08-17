package com.itwill.myleaves.repository.sellbuy;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellRepository extends JpaRepository<Sell, Long>{

	//id 내림차순 메인페이지
	List<Sell> findByOrderBySoldAscSellIdDesc();
	
	Page<Sell> findByOrderBySoldAscSellIdDesc(Pageable pageable);
	
	// 마이페이지 분양 리스트 
	Page<Sell> findByUserId(String userId, Pageable pageable);

}
