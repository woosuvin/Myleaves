package com.itwill.myleaves.service.cart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.myleaves.dto.cart.CartCreateDto;
import com.itwill.myleaves.repository.cart.Cart;
import com.itwill.myleaves.repository.cart.CartRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@AllArgsConstructor
@Service
public class CartService {

	private final CartRepository cartRepository;
	
	public List<Cart> findByUserId(String userId) {
		List<Cart> list = cartRepository.findByUserId(userId);
		return list;
	}
	
	/**
	 * 리스트
	 * @param cart
	 * @return
	 */
	public List<Cart> read(Cart cart) {
		List<Cart> list = cartRepository.findByUserId(cart.getUserId());
		return list;
	}
	
	/**
	 * 장바구니 삭제
	 * @param userId
	 * @param itemId
	 */
	public void delete(String userId, long itemId) {
		log.info("delete(userId={}, itemId={})", userId, itemId);
		cartRepository.deleteByUserIdAndItemId(userId, itemId);
	}
	
	/**
	 * 장바구니 추가
	 * @param dto
	 * @return
	 */
	public Cart create(CartCreateDto dto) {
		log.info("create(dto={})", dto);
		Cart entity = Cart.builder()
				.userId(dto.getUserId())
				.itemId(dto.getItemId())
				.cnt(dto.getCnt())
				.build();
		
		cartRepository.saveAndFlush(entity);
		log.info("entity={}", entity);
		return entity;
	}
	
	
}
