package com.itwill.myleaves.repository.sellbuy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;

public interface BuyWishRepository extends JpaRepository<BuyWish, BuyWishId> {
	
	List<BuyWish> findByUserId(String userId);
	
	// 관심목록 삭제
	@Transactional
	@Modifying
	@Query(value = "delete from WISH_SELL where USER_ID = ?1 and SELL_ID = ?2", 
			nativeQuery = true)
	void deleteByUserIdAndSellId(String userId, long sellId);
	
	List<BuyWish> findBySellId(long sellId);
	
}
