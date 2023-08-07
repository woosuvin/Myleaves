package com.itwill.myleaves.repository.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TotalOrderRepository extends JpaRepository<TotalOrder, Long> {

	// 마이페이지 사용자 아이디로 가지고 오기
	List<TotalOrder> findByUserIdOrderByOrderIdDesc(String UserId);
	
	// 관리자 걍 다 가져옴
	List<TotalOrder> findByOrderByOrderIdDesc();
	
	// 마이페이지 상세 사용자 아이디, 주문 번호로 가져오기
	TotalOrder findByOrderId(long orderId);
	
	/**
	 * 수빈
	 * 빈 total_order 생성하여 주문상세 받을 준비
	 */
//	@Transactional
//	@Modifying
//	@Query(value = "insert into TOTAL_ORDER "
//			+ "(USER_ID, PRICE, NAME, ZIPCODE, ADDR, ADDRDETAIL, TEL, CNT, ITEM_NAME, ITEM_IMG) "
//			+ "values "
//			+ "(?1, -1, "
//			+ "(select NAME from USER_INFO where USER_ID = ?1), "
//			+ "'', '', '', '', -1, '', '')", nativeQuery = true)
//	void insertTotalOrder(String user_id);
	
	@Transactional
	@Modifying
	@Query(value ="insert into TOTAL_ORDER "
			+ "(USER_ID, PRICE, NAME, CNT) "
			+ "VALUES "
			+ "(:userId, -1, (select NAME from USER_INFO where USER_ID = :userId), -1)", nativeQuery = true)
	void insertTotalOrder(@Param("userId") String userId);
}
