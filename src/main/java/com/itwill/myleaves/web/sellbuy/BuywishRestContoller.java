package com.itwill.myleaves.web.sellbuy;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.myleaves.dto.wish.WishSellCreateDto;
import com.itwill.myleaves.dto.wish.WishStoreCreateDto;
import com.itwill.myleaves.repository.sellbuy.BuyWish;
import com.itwill.myleaves.repository.store.StoreWish;
import com.itwill.myleaves.service.sellbuy.SellService;
import com.itwill.myleaves.service.store.StoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 정지언
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/buyWish")
public class BuywishRestContoller {
	
	private final SellService sellService;
	
	/**
	 * 입양 관심상품 추가
	 * @param dto
	 * @return
	 */
	@PostMapping
	public ResponseEntity<BuyWish> createBuyWish(@RequestBody WishSellCreateDto dto) {
//		log.info("create(dto={})", dto);
		BuyWish wish = sellService.create(dto);
//		log.info(wish.toString());
		return ResponseEntity.ok(wish);
	}
	
	/**
	 * 입양 관심상품 삭제
	 * @param userId
	 * @param sellId
	 * @return
	 */
	@DeleteMapping("/{userId}/{sellId}")
	public ResponseEntity<String> deleteBuyWish(@PathVariable String userId, @PathVariable long sellId) {
		sellService.delete(userId, sellId);
		return ResponseEntity.ok("Success");
	}
	
}
