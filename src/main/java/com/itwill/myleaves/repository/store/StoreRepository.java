package com.itwill.myleaves.repository.store;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

// 수빈
public interface StoreRepository extends JpaRepository<Store, Long> {
	
	// Mngr 페이지에 날짜로 정렬?
	List<Store> findByOrderByItemIdDesc();
	
	Store findByItemId(long itemId);
	
}
