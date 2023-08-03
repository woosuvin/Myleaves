package com.itwill.myleaves.repository.cart;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.itwill.myleaves.dto.cart.CartListDto;



public interface CartRepository extends JpaRepository<Cart, CartId> {
	
	List<Cart> findByUserId(String userId);
	
	List<Cart> findByItemId(Long itemId);
	
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
