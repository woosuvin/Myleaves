package com.itwill.myleaves.repository.sellbuy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyWishRepository extends JpaRepository<BuyWish, BuyWishId> {
	
	List<BuyWish> findByUserId(String userId);
	
}
