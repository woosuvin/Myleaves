package com.itwill.myleaves.service.mypage;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.myleaves.repository.sellbuy.BuyWish;
import com.itwill.myleaves.repository.sellbuy.BuyWishRepository;
import com.itwill.myleaves.repository.sellbuy.SellRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MypageSellBuyService {
	
	private final BuyWishRepository buyWishRepository;
	
	/**
	 * 입양 관심상품 리스트
	 * @param buyWish
	 * @return
	 */
	public List<BuyWish> read(BuyWish wishList) {
		List<BuyWish> list = buyWishRepository.findByUserId(wishList.getUserId());
		return list;
	}
	
	/**
	 * 관심상품 등록 여부 확인
	 * @param sellId
	 * @return
	 */
	public List<BuyWish> readBuyWish(Long sellId) {
		return buyWishRepository.findBySellId(sellId);
	}
	
}
