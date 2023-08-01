package com.itwill.myleaves.repository.store;

import com.itwill.myleaves.repository.CreatedTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

@Entity
@Table(name = "STORE")
@SequenceGenerator(name = "STORE_SEQ_GEN", sequenceName = "STORE_SEQ1", allocationSize = 1)
public class Store extends CreatedTimeEntity {
	
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
	private String sold;
	
	@Column(nullable = true)
	private long inven;
	
}
