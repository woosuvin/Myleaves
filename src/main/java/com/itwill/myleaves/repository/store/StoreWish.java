package com.itwill.myleaves.repository.store;

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

//남지현
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
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
