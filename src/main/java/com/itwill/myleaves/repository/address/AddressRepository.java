package com.itwill.myleaves.repository.address;

import org.springframework.data.jpa.repository.JpaRepository;

// 수빈
public interface AddressRepository extends JpaRepository<Address, AddressId> {
	
	// 유저 아이디로
	Address findByUserIdAndDefAddr(String userId, int defAddr);
	
	// addrId로
	Address findByAddrId(long addrId);
	
}
