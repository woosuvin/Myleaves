package com.itwill.myleaves.repository.orderDetail;

import org.springframework.stereotype.Service;

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
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name="ORDER_DETAIL")
@SequenceGenerator(name = "ORDER_DETAIL_SEQ_GEN", sequenceName = "ORDER_DETAIL_SEQ", allocationSize = 1)
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_DETAIL_SEQ_GEN")
	private Long orderdetId;
	
	@Column(nullable = true)
	private Long orderId;
	
	@Column(nullable = true)
	private Long itemId;
	
	@Column(nullable = true)
	private Long price;
	
	@Column(nullable = true)
	private Long cnt;
}
