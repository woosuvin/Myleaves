package com.itwill.myleaves.dto.store;

import com.itwill.myleaves.repository.store.Store;

import com.itwill.myleaves.repository.store.Store;

import lombok.Data;

// 수빈
@Data
public class StoreUpdateDto {
	
	private long itemId;
	private String itemName;
	//private byte[] thumbnail;
	private String content;
	private long price;
	private long inven;
	private int sold;
	
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
