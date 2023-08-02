package com.itwill.myleaves.repository.store;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.itwill.myleaves.dto.store.StoreUpdateDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)

@Entity
@Table(name = "STORE")
@SequenceGenerator(name = "STORE_SEQ_GEN", sequenceName = "STORE_SEQ", allocationSize = 1)
public class Store {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STORE_SEQ_GEN")
	private long itemId;
	
	@Column(nullable = true)
	private String itemName;
	
	@Column(nullable = true)
	private byte[] thumbnail;
	
	@Column(nullable = true)
	private String content;
	
	@Column(nullable = true)
	private long price;
	
	@Column(nullable = true)
	private int sold;
	
	@Column(nullable = true)
	private long inven;
	
	@CreatedDate
	private LocalDateTime createdDate;
	
	public Store update(StoreUpdateDto dto) {
		this.itemName = dto.getItemName();
		//this.thumbnail = dto.getThumbnail();
		this.content = dto.getContent();
		this.price = dto.getPrice();
		this.sold = dto.getSold();
		this.inven = dto.getInven();
		return this;
	}
}
