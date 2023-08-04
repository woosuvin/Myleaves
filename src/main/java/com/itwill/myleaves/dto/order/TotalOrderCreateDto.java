package com.itwill.myleaves.dto.order;

import org.springframework.web.multipart.MultipartFile;

import com.itwill.myleaves.repository.order.TotalOrder;

import lombok.Data;

//지현
@Data
public class TotalOrderCreateDto {
	private Long orderId;
	private String userId;
	private Long price;
	private Long payment;
	private String card;
	private String status;
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
	private byte[] itemImg;
	
	private MultipartFile file;
	
	public TotalOrder toEntity() {
		return TotalOrder.builder()
				.userId(userId)
				.price(price)
				.payment(payment)
				.card(card)
				.status("주문 확인중")
				.reAcc(reAcc)
				.name(name)
				.zipcode(zipcode)
				.addr(addr)
				.addrdetail(addrdetail)
				.tel(tel)
				.req(req)
				.cnt(cnt)
				.itemName(itemName)
				.itemImg(itemImg)
				.build();
	}
}
