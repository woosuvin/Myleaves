package com.itwill.myleaves.repository.store;

import java.io.Serializable;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class StoreWishId implements Serializable{
	private String userId;
	private Long itemId;
}
