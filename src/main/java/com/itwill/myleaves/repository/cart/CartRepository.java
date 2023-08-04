package com.itwill.myleaves.repository.cart;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;




public interface CartRepository extends JpaRepository<Cart, CartId> {
	
	List<Cart> findByUserId(String userId);
	
	List<Cart> findByItemId(Long itemId);
	
	@Modifying
	@Query(value = "update CART set cnt=?1 where USER_ID = ?2 and ITEM_ID = ?3", 
			nativeQuery = true)
	void update(int cnt, String userId, Long itemId);
	
	Cart findByUserIdAndItemId(String userId, Long itemId);
	
	@Transactional
	@Modifying
	@Query(value = "delete from CART where USER_ID = ?1 and ITEM_ID = ?2",
            nativeQuery = true)
    void deleteByUserIdAndItemId(String userId, long itemId);
	
	/**
	 * 장바구니 리스트
	 * @param userId
	 * @return
	 */
	/*
	 * @Query("select c.USER_ID, c.CNT, s.ITEM_NAME, s.ITEM_ID, s.PRICE, s.THUMBNAIL "
	 * + "from CART c, STORE " +
	 * "where c.ITEM_ID = s.ITEM_ID and c.USER_ID = :userId") List<CartListDto>
	 * cartList(@Param("userId") String userId);
	 */
}
