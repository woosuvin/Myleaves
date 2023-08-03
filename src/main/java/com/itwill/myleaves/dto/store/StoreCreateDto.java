package com.itwill.myleaves.dto.store;

import com.itwill.myleaves.repository.store.Store;

import lombok.Data;

// 수빈
@Data
public class StoreCreateDto {
	
	private String itemName;
	//private byte[] thumbnail;
	private String content;
	private long price;
	private long inven;
	private int sold;
	
	// dto를 entity 객체로 변환해서 리턴하는 메서드
	public Store toEntity() {
		return Store.builder()
				.itemName(itemName)
				//.thumbnail(thumbnail)
				.content(content)
				.price(price)
				.inven(inven)
				.sold(sold)
				.build();
	}
}
