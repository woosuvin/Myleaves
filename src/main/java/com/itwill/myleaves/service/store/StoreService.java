package com.itwill.myleaves.service.store;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.myleaves.dto.store.StoreCreateDto;
import com.itwill.myleaves.dto.store.StoreUpdateDto;
import com.itwill.myleaves.dto.wish.WishStoreCreateDto;
import com.itwill.myleaves.repository.sellbuy.BuyWish;
import com.itwill.myleaves.repository.store.Store;
import com.itwill.myleaves.repository.store.StoreRepository;
import com.itwill.myleaves.repository.store.StoreWish;
import com.itwill.myleaves.repository.store.StoreWishRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 수빈
@Slf4j
@RequiredArgsConstructor
@Service
public class StoreService {

	private final StoreRepository storeRepository;
	private final StoreWishRepository storeWishRepository;
	
	/**
	 * 스토어 리스트 (메인페이지에서 사용)
	 * @return
	 */
	public List<Store> read() {
		return storeRepository.findByOrderBySoldAscCreatedDateDesc();
	}
	
	/**
	 * 관리자 페이지에서 스토어 상품 create
	 */
	public Store create(StoreCreateDto dto) {
//		log.info("create(dto={})", dto);
		
		Store entity = dto.toEntity();
//		log.info("entity={}", entity);
		
		storeRepository.save(entity);
//		log.info("entity={}", entity);
		
		return entity;
	}
	
	/**
	 * 관리자 페이지 스토어 상품 리스트
	 */
	public Page<Store> read(Pageable pageable) {
//		log.info("mngrRead()");
		return storeRepository.findByOrderByItemIdDesc(pageable);
	}
	
	/**
	 * 회원 페이지 스토어 상품 리스트
	 * @return
	 */
	public Page<Store> readUserPage(Pageable pageable) {
		return storeRepository.findByOrderBySoldAscCreatedDateDesc(pageable);
	}
	
	/**
	 * detail
	 */
	@Transactional(readOnly = true)
	public Store read(long itemId) {
//		log.info("read(itemId={})", itemId);
		//return storeRepository.findById(itemId).orElseThrow();
		return storeRepository.findByItemId(itemId);
	}
	
	/**
	 * update
	 * @throws IOException 
	 */
	@Transactional
	public void update(StoreUpdateDto dto) throws IOException {
//		log.info("update(dto={})", dto);
		Store entity = storeRepository.findByItemId(dto.getItemId());
		entity.update(dto);
	}
	
	/**
	 * delete
	 */
	public void delete(long itemId) {
		Store entity = storeRepository.findByItemId(itemId);
		storeRepository.delete(entity);
	}
	
	
	/**
	 * 스토어 관심상품 삭제
	 * @param userId
	 * @param itemId
	 */
	public void deleteStoreWish(String userId, long itemId) {
//		log.info("delete(userId={}, sellId={})", userId, itemId);
		storeWishRepository.deleteByUserIdAndItemId(userId, itemId);
	}
	
	/**
	 * 스토어 관심삼품 추가
	 * @param dto
	 * @return
	 */
	public StoreWish createStoreWish(WishStoreCreateDto dto) {
//		log.info("create(dto={})", dto);
		StoreWish entity = StoreWish.builder()
					.userId(dto.getUserId())
					.itemId(dto.getItemId())
					.build();
		
		storeWishRepository.saveAndFlush(entity);
//		log.info("entity={}", entity);
		return entity;
	}
	
	/**
	 * 관심상품 등록 여부 확인
	 * @param itemId
	 * @return
	 */
	public List<StoreWish> readStoreWish(long itemId) {
		return storeWishRepository.findByItemId(itemId);
	}
	
}
