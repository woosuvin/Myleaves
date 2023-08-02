package com.itwill.myleaves.repository.sellbuy;

import java.io.Serializable;

import lombok.Data;

// 정지언
@Data
public class WishListPK implements Serializable {
	private String userId;
	private Long sellId;

}
