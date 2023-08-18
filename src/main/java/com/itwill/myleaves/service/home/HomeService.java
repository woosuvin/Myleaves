package com.itwill.myleaves.service.home;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.myleaves.repository.community.Community;
import com.itwill.myleaves.repository.community.CommunityRepository;
import com.itwill.myleaves.repository.sellbuy.Sell;
import com.itwill.myleaves.repository.sellbuy.SellRepository;
import com.itwill.myleaves.repository.store.Store;
import com.itwill.myleaves.repository.store.StoreRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 정지언
@Slf4j
@RequiredArgsConstructor
@Service
public class HomeService {
	
	private final SellRepository sellRepository;
	private final StoreRepository storeRepository;
	private final CommunityRepository communityRepository;
	
	/**
	 * 입양 메뉴 리스트
	 * @return
	 */
	public List<Sell> readSellList() {
		return sellRepository.findByOrderBySoldAscSellIdDesc();
	}
	
	/**
	 * 스토어 리스트
	 * @return
	 */
	public List<Store> readStoreList() {
		return storeRepository.findByOrderBySoldAscCreatedDateDesc();
	}
	
	/**
	 * 커뮤니티 리스트
	 * @return
	 */
	public List<Community> readCommunityList() {
		return communityRepository.findByOrderByCreatedDateDesc();
	}

}
