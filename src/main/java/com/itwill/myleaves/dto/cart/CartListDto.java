package com.itwill.myleaves.dto.cart;

import lombok.Data;

// 수빈
@Data
public class CartListDto {
	
	private String userId;
	private long itemId;
	private String itemName;
	private byte[] thumbnail;
	private long price;
	private int cnt;
	
}
