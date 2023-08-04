package com.itwill.myleaves.dto.order;

import com.itwill.myleaves.repository.order.OrderDetail;

import lombok.Data;
//지현

@Data
public class OrderDetailCreateDto {
	private Long orderdetId;
	private Long orderId;
	private Long itemId;
	private Long price;
	private Long cnt;
	
	public OrderDetail toEntity() {
		return OrderDetail.builder()
				.orderId(orderId)
				.itemId(itemId)
				.price(price)
				.cnt(cnt)
				.build();
	}
}
