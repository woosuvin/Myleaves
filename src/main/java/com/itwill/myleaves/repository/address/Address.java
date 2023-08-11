package com.itwill.myleaves.repository.address;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.itwill.myleaves.dto.address.AddressUpdateDto;
import com.itwill.myleaves.repository.cart.Cart;
import com.itwill.myleaves.repository.cart.CartId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
@Entity
@Data
@IdClass(AddressId.class)
@Table(name = "ADDRESS")
@SequenceGenerator(name = "ADDRESS_SEQ_GEN", sequenceName = "ADDRESS_SEQ", allocationSize = 1)
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADDRESS_SEQ_GEN")
	@Column(name = "ADDR_ID")
	private Long addrId;
	
	@Id
	@Column(name = "USER_ID")
	private String userId;
	
	private String name;
	
	private String zipcode;
	
	private String addr;
	
	private String addrdetail;
	
	private String tel;
	
	private String req;
	
	private int defAddr;
	
	public Address update(AddressUpdateDto dto) {
		this.name = dto.getName();
		this.zipcode = dto.getZipcode();
		this.addr = dto.getAddr();
		this.addrdetail = dto.getAddrdetail();
		this.tel = dto.getTel();
		this.req = dto.getReq();
		return this;
	}
	
}
