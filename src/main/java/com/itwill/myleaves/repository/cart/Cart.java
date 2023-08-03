package com.itwill.myleaves.repository.cart;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;


// 수빈
@Entity
@Data
@IdClass(CartId.class)
@Table(name = "CART")
public class Cart {

	@Id
	@Column(name = "USER_ID")
	private String userId;
	
	@Id
	@Column(name = "ITEM_ID")
	private Long itemId;
	
	@Column(nullable = true)
	private int cnt;
	
	@CreatedDate
	private LocalDateTime addDate;
}