package com.itwill.myleaves.repository.cart;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


// 수빈
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
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
	
	public Cart update(int cnt) {
		this.cnt = cnt;
		return this;
	}
}