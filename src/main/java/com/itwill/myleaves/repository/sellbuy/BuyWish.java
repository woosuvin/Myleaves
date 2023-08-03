package com.itwill.myleaves.repository.sellbuy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

// 정지언
@Entity
@Data
@IdClass(BuyWishId.class)
@Table(name = "WISH_SELL")
public class BuyWish {
	
	@Id
	@Column(name = "user_id")
	private String userId;
	
	@Id
	@Column(name = "sell_id")
	private Long sellId;

}
