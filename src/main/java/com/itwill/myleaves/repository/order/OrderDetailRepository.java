package com.itwill.myleaves.repository.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

	/**
	 * 주문 상세 페이지 지현
	 * @param orderId
	 * @return
	 */
	List<OrderDetail> findByOrderId(Long orderId);
	
}
