package com.itwill.myleaves.repository.store;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

// 수빈
public interface StoreRepository extends JpaRepository<Store, Long> {
	
	// Mngr 페이지에 날짜로 정렬?
	List<Store> findByOrderByItemIdDesc();
	
	/**
	 * 품절 오름차순, 등록일 내림차순 순으로 정렬
	 * @param itemId
	 * @return
	 */
	List<Store> findByOrderBySoldAscCreatedDateDesc();
	
	Store findByItemId(long itemId);
	
}
