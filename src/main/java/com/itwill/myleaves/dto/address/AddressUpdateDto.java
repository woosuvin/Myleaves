package com.itwill.myleaves.dto.address;

import com.itwill.myleaves.repository.address.Address;

import lombok.Data;

// 수빈
@Data
public class AddressUpdateDto {
	
	// if defAddr 1(기본배송지)일 때 주소 수정할거니까 defAddr는 필요 없 나
	private long addrId;
	private String name;
	private String zipcode;
	private String addr;
	private String addrdetail;
	private String tel;
	private String req;
	private int defAddr;
	
	public Address toEntity() {
		return Address.builder()
				.name(name)
				.zipcode(zipcode)
				.addr(addr)
				.addrdetail(addrdetail)
				.tel(tel)
				.req(req)
				.build();
	}
}
