package com.itwill.myleaves.service.mypage;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.myleaves.repository.sellbuy.Sell;
import com.itwill.myleaves.repository.sellbuy.SellRepository;
import com.itwill.myleaves.repository.sellbuy.WishList;
import com.itwill.myleaves.repository.sellbuy.WishListPK;
import com.itwill.myleaves.repository.sellbuy.WishListRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MypageService {
	
	private final SellRepository sellRepository;
	private final WishListRepository wishListRepository;
	
	// wishList
	public List<WishList> read(WishList wishList) {
		List<WishList> list = wishListRepository.findByUserId(wishList.getUserId());
		return list;
	}
//	// sellBuy
//	public List<Sell> {
//	}
//	
//	// store

}
