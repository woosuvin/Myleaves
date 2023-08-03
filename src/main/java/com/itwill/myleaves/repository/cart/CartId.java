package com.itwill.myleaves.repository.cart;

import java.io.Serializable;

import lombok.Data;

// 수빈
@Data
public class CartId implements Serializable {
	
	private String userId;
	private Long itemId;
	
}
