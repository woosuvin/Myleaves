package com.itwill.myleaves.repository.store;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

//남지현

@Entity
@Data
@IdClass(StoreWishId.class)
@Table(name="WISH_STORE")
public class StoreWish {
	
	@Id
	@Column(name="user_id")
	private String userId;
	
	@Id
	@Column(name="item_id")
	private Long itemId;
}
