//package com.itwill.myleaves.service.cart;
//
//import org.springframework.stereotype.Service;
//
//import com.itwill.myleaves.repository.cart.CartId;
//import com.itwill.myleaves.repository.cart.CartRepository;
//
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.java.Log;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@RequiredArgsConstructor
//@Service
//public class CartService {
//
//	private final CartRepository cartRepository;
//	
//	@Transactional
//	public void addCart(User user, Item item, int amount) {
//		
//		// 해당 유저의 장바구니 찾기
//		Cart cart = cartRepository.findByUserId(user.getId());
//		
//		// 새 상품 추가
//		if (cartItem == null) {
//			cartItem = CartItem.createCartItem()
//		}
//	
//else ()
//	}
//	
//	
//}
