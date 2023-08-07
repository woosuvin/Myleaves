package com.itwill.myleaves.dto.address;

import com.itwill.myleaves.repository.address.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressCreateDto {
	
	private String userId;
	private String name;
	private String zipcode;
	private String addr;
	private String addrdetail;
	private String tel;
	private String req;
	
	public Address toEntity() {
		return Address.builder()
				.userId(userId)
				.name(name)
				.zipcode(zipcode)
				.addr(addr)
				.addrdetail(addrdetail)
				.tel(tel)
				.req(req)
				.build();
	}
}
