package com.itwill.myleaves.repository.totalOrder;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.itwill.myleaves.dto.order.TotalOrderUpdateDto;

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
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "TOTAL_ORDER")
@SequenceGenerator(name = "TOTAL_ORDER_SEQ_GEN", sequenceName = "TOTAL_ORDER_SEQ", allocationSize = 1)
public class TotalOrder {
// 지현
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TOTAL_ORDER_SEQ_GEN")
	private Long orderId;
	
	@Column(nullable = false)
	private String userId;
	
	@Column(nullable = true)
	private Long price;
	
	@CreatedDate
	private LocalDateTime orderDate;
	
	@Column(nullable = true)
	private Long payment;
	
	@Column(nullable = true)
	private String card;
	
	@Column(nullable = true)
	private String status;

	@Column(nullable = true)
	private String reason;

	@Column(nullable = true)
	private String reAcc;

	@Column(nullable = true)
	private String name;

	@Column(nullable = true)
	private String zipcode;

	@Column(nullable = true)
	private String addr;

	@Column(nullable = true)
	private String addrdetail;

	@Column(nullable = true)
	private String tel;

	@Column(nullable = true)
	private String req;

	@Column(nullable = true)
	private Long cnt;

	@Column(nullable = true)
	private String itemName;

	@Column(nullable = true)
	private byte[] itemImg;
	
//	public TotalOrder update(TotalOrderUpdateDto dto) {
//		this.status = dto.getStatus();
//		this.reason = dto.getReason();
//		return this;
//	}
	public TotalOrder update(TotalOrderUpdateDto dto) {
		this.price = dto.getPrice();
		this.payment = dto.getPayment();
		this.card = dto.getCard();
		this.status = "주문 완료";
		this.reAcc = dto.getReAcc();
		this.name = dto.getName();
		this.zipcode = dto.getZipcode();
		this.addr = dto.getAddr();
		this.addrdetail = dto.getAddrdetail();
		this.tel = dto.getTel();
		this.req = dto.getReq();
		this.cnt = dto.getCnt();
		this.itemName = dto.getItemName();
		this.itemImg = dto.getItemImg();
		return this;
	}
	
}
