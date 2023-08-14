package com.itwill.myleaves.dto.order;


import com.itwill.myleaves.repository.totalOrder.TotalOrder;

import lombok.Data;

@Data
public class TotalOrderUpdateDto {

	private Long orderId;
	private String userId;
	private Long price;
	private Long payment;
	private String reason;
	private String reAcc;
	private String name;
	private String zipcode;
	private String addr;
	private String addrdetail;
	private String tel;
	private String req;
	private Long cnt;
	private String itemName;
	
	
	public TotalOrder toEntity() {
		return TotalOrder.builder()
				.userId(userId)
				.price(price)
				.payment(payment)
				.reAcc(reAcc)
				.name(name)
				.zipcode(zipcode)
				.addr(addr)
				.addrdetail(addrdetail)
				.tel(tel)
				.req(req)
				.cnt(cnt)
				.itemName(itemName)
				.build();
	}
}
