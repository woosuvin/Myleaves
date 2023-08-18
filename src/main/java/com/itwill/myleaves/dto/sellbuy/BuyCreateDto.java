package com.itwill.myleaves.dto.sellbuy;

import com.itwill.myleaves.repository.sellbuy.Buy;

import lombok.Data;

// 수빈
@Data
public class BuyCreateDto {
	
	private Long buyId;
	private Long sellId;
	private String sellerId;
	private String buyerId;
	private Long sold;
	
	public Buy toEntity() {
		return Buy.builder()
				.sellId(sellId)
				.sellerId(sellerId)
				.buyerId(buyerId)
				.build();
	}
}
