package com.itwill.myleaves.repository.store;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface StoreWishRepository extends JpaRepository<StoreWish, StoreWishId> {

	List<StoreWish> findByUserId(String userId);
	
	List<Store> findByItemId(Long itemId);
}
