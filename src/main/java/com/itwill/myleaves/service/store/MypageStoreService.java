package com.itwill.myleaves.service.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.itwill.myleaves.repository.store.Store;
import com.itwill.myleaves.repository.store.StoreRepository;
import com.itwill.myleaves.repository.store.StoreWish;
import com.itwill.myleaves.repository.store.StoreWishRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MypageStoreService {
	private final StoreWishRepository storeWishRepository;
	private final StoreRepository storeRepository;
	
	
	/**
	 * UserId로 StoreWishList 테이블의 해당 itemId 리스트 만듦
	 * itemId로 Store 테이블 데이터 가져옴
	 * @param storeWishList
	 * @return Store 상품 정보 list
	 */
	public Page<StoreWish> read(StoreWish storeWish, Pageable pageable){
		Page<StoreWish> list = storeWishRepository.findByUserId(storeWish.getUserId(), pageable);
		return list;
	}
	
//	public List<StoreWish> readWish(Long itemId){
//		return storeWishRepository.findByItemId(itemId);
//	}
	
}
