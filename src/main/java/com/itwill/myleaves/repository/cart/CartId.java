package com.itwill.myleaves.repository.cart;

import java.io.Serializable;

import com.itwill.myleaves.repository.store.Store;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class CartId implements Serializable {
	
	private Store store;
//	private User user;

}
