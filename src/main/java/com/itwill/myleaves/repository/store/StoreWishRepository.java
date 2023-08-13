package com.itwill.myleaves.repository.store;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.myleaves.repository.sellbuy.BuyWish;


public interface StoreWishRepository extends JpaRepository<StoreWish, StoreWishId> {

	Page<StoreWish> findByUserId(String userId, Pageable pageable);
	
	List<StoreWish> findByItemId(long itemId);
	
	// 관심목록 삭제
	@Transactional
	@Modifying
	@Query(value = "delete from WISH_STORE where USER_ID = ?1 and ITEM_ID = ?2", 
			nativeQuery = true)
	void deleteByUserIdAndItemId(String userId, long itemId);

}
