package com.itwill.myleaves.service.sellbuy;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.myleaves.dto.sellbuy.BuyCreateDto;
import com.itwill.myleaves.repository.sellbuy.Buy;
import com.itwill.myleaves.repository.sellbuy.BuyRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 수빈
@Slf4j
@RequiredArgsConstructor
@Service
public class BuyService {
	
	private final BuyRepository buyRepository;
	
	/**
	 * 분양 내역 추가
	 */
	public Buy create(BuyCreateDto dto) {
		Buy entity = Buy.builder()
				.sellId(dto.getSellId())
				.sellerId(dto.getSellerId())
				.buyerId(dto.getBuyerId())
				.build();
		
		buyRepository.saveAndFlush(entity);
		return entity;
	}
	
}
