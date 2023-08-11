package com.itwill.myleaves.repository.totalOrder;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.myleaves.dto.order.TotalOrderdSearchDto;

public interface TotalOrderRepository extends JpaRepository<TotalOrder, Long> {

	// 마이페이지 사용자 아이디로 가지고 오기
	List<TotalOrder> findByUserIdOrderByOrderIdDesc(String UserId);
	
	// 관리자 걍 다 가져옴 수빈: 주문 날짜 정렬로 바꿈
	List<TotalOrder> findByOrderByOrderDateDesc();
	
	// 마이페이지 상세 사용자 아이디, 주문 번호로 가져오기
	TotalOrder findByOrderId(long orderId);
	
	/**
	 * 수빈
	 * 관리자 주문 상태로 검색
	 */
	@Query(value = "select * from TOTAL_ORDER where STATUS=?1 order by ORDER_DATE desc", nativeQuery = true)
	List<TotalOrder> findByStatus(String status);
	
	/**
	 * 지현
	 * 관리자 주문자 아이디, 주문 현황, 주문 날짜로 검색
	 * @param userId
	 * @return
	 */
	@Query(value = "SELECT * FROM TOTAL_ORDER WHERE "
	        + "(:searchUserId IS NULL OR LOWER(USER_ID) LIKE LOWER('%' || :searchUserId || '%')) "
	        + "AND (:searchStatus IS NULL OR STATUS = :searchStatus) "
	        + "AND (:searchOrderDateStart IS NULL OR ORDER_DATE >= :searchOrderDateStart) "
	        + "AND (:searchOrderDateEnd IS NULL OR ORDER_DATE <= :searchOrderDateEnd) "
	        + "ORDER BY ORDER_DATE DESC", nativeQuery = true)
	List<TotalOrder> search(@Param("searchUserId") String searchUserId,
	                        @Param("searchStatus") String searchStatus,
	                        @Param("searchOrderDateStart") String searchOrderDateStart,
	                        @Param("searchOrderDateEnd") String searchOrderDateEnd);

}
