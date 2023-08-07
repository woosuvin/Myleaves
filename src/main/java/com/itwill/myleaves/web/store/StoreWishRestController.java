package com.itwill.myleaves.web.store;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.myleaves.dto.wish.WishStoreCreateDto;
import com.itwill.myleaves.repository.store.StoreWish;
import com.itwill.myleaves.service.store.StoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 정지언
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/storeWish")
public class StoreWishRestController {
		
	private final StoreService storeService;
	
	/**
	 * 스토어 관심상품 추가
	 * @param dto
	 * @return
	 */
	@PostMapping
	public ResponseEntity<StoreWish> createStoreWish(@RequestBody WishStoreCreateDto dto) {
		log.info("create(dto={})", dto);
		StoreWish wish = storeService.createStoreWish(dto);
		log.info(wish.toString());
		return ResponseEntity.ok(wish);
	}
	
	/**
	 * 스토어 관심상품 삭제
	 * @param userId
	 * @param itemId
	 * @return
	 */
	@DeleteMapping("/{userId}/{itemId}")
	public ResponseEntity<String> deleteStoreWish(@PathVariable String userId, @PathVariable long itemId) {
		storeService.deleteStoreWish(userId, itemId);
		return ResponseEntity.ok("Success");
	}
	
}
