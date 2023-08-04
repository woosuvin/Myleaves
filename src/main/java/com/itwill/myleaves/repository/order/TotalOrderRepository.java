package com.itwill.myleaves.repository.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TotalOrderRepository extends JpaRepository<TotalOrder, Long> {

	// 마이페이지 사용자 아이디로 가지고 오기
	List<TotalOrder> findByUserIdOrderByOrderIdDesc(String UserId);
	
	// 관리자 걍 다 가져옴
	List<TotalOrder> findByOrderByOrderIdDesc();
	
	// 마이페이지 상세 사용자 아이디, 주문 번호로 가져오기
	TotalOrder findByOrderId(long orderId);

}
