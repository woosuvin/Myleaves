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
	private final SellRepository sellRepository;
	private final BuyWishRepository wishListRepository;
	
	// wishList
	public List<BuyWish> read(BuyWish wishList) {
		List<BuyWish> list = wishListRepository.findByUserId(wishList.getUserId());
		return list;
	}
//	// sellBuy
//	public List<Sell> {
//	}
//	
//	// store
}
