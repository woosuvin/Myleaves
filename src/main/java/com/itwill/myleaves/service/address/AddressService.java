package com.itwill.myleaves.service.address;

import org.springframework.stereotype.Service;

import com.itwill.myleaves.dto.address.AddressCreateDto;
import com.itwill.myleaves.repository.address.Address;
import com.itwill.myleaves.repository.address.AddressRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class AddressService {
	
	private final AddressRepository addressRepository;
	
	/**
	 * 배송지 추가
	 */
	public Address create(AddressCreateDto dto) {
		log.info("create={}", dto);
		
		Address entity = dto.toEntity();
		addressRepository.save(entity);
		return entity;
	}
}
