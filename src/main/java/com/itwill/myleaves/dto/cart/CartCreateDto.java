package com.itwill.myleaves.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartCreateDto {
	
	private String userId;
	private long itemId;
	private int cnt;
}
