package com.itwill.myleaves.repository.address;

import java.io.Serializable;

import lombok.Data;

@Data
public class AddressId implements Serializable {
	private Long addrId;
	private String userId;
}
