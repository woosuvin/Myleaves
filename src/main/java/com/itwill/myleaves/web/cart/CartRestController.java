package com.itwill.myleaves.web.cart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.myleaves.dto.cart.CartCreateDto;
import com.itwill.myleaves.dto.cart.CartUpdateDto;
import com.itwill.myleaves.repository.cart.Cart;
import com.itwill.myleaves.repository.store.Store;
import com.itwill.myleaves.service.cart.CartService;
import com.itwill.myleaves.service.store.StoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 수빈
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cart")
public class CartRestController {
	
	private final CartService cartService;
	private final StoreService storeService;
	
	/**
	 * 장바구니 추가
	 * @param dto
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Cart> create(@RequestBody CartCreateDto dto) {
		log.info("create(dto={})", dto);
		Cart cart = cartService.create(dto);
		log.info(cart.toString());
		return ResponseEntity.ok(cart);
	}
	
	/**
	 * 장바구니 삭제
	 * @param userId
	 * @param itemId
	 * @return
	 */
	@DeleteMapping("/{userId}/{itemId}")
	public ResponseEntity<String> delete(@PathVariable String userId, @PathVariable long itemId) {
		cartService.delete(userId, itemId);
		return ResponseEntity.ok("Success");
	}
	
	/**
	 * 장바구니 수량 변경 +
	 */
	@PutMapping("/update/{userId}/{itemId}")
	public ResponseEntity<String> update(
			@PathVariable String userId, @PathVariable long itemId, @RequestBody CartUpdateDto dto) {
		log.info("update(userId={}, itemId={}, dto={})",userId, itemId, dto);
		
		cartService.update(userId, itemId, dto);
		return ResponseEntity.ok("Success");
	}
}
