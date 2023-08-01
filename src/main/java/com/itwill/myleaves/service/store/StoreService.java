package com.itwill.myleaves.service.store;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.myleaves.dto.store.StoreCreateDto;
import com.itwill.myleaves.repository.store.Store;
import com.itwill.myleaves.repository.store.StoreRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 수빈
@Slf4j
@RequiredArgsConstructor
@Service
public class StoreService {

	private final StoreRepository storeRepository;
	
	/**
	 * 관리자 페이지에서 스토어 상품 create
	 */
	public Store create(StoreCreateDto dto) {
		log.info("create(dto={})", dto);
		
		Store entity = dto.toEntity();
		log.info("entity={}", entity);
		
		storeRepository.save(entity);
		log.info("entity={}", entity);
		
		return entity;
	}
	
	/**
	 * 관리자 페이지 스토어 상품 리스트
	 */
	public List<Store> read() {
		log.info("mngrRead()");
		return storeRepository.findByOrderByItemIdDesc();
	}
	
	/**
	 * detail
	 */
	public Store read(long itemId) {
		log.info("read(itemId={})", itemId);
		//return storeRepository.findById(itemId).orElseThrow();
		return storeRepository.findByItemId(itemId);
	}
	
}
