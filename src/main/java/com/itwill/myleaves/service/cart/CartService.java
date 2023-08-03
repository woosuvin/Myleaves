package com.itwill.myleaves.service.cart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.myleaves.dto.cart.CartListDto;
import com.itwill.myleaves.repository.cart.Cart;
import com.itwill.myleaves.repository.cart.CartRepository;
import com.itwill.myleaves.repository.store.Store;
import com.itwill.myleaves.repository.store.StoreRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@RequiredArgsConstructor
@Service
public class CartService {

	private final CartRepository cartRepository;
	
	/**
	 * 리스트
	 * @param cart
	 * @return
	 */
	public List<Cart> read(Cart cart) {
		List<Cart> list = cartRepository.findByUserId(cart.getUserId());
		return list;
	}
	
	
}
