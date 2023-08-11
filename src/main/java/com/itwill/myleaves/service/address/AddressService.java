package com.itwill.myleaves.service.address;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.myleaves.dto.address.AddressCreateDto;
import com.itwill.myleaves.dto.address.AddressUpdateDto;
import com.itwill.myleaves.dto.store.StoreUpdateDto;
import com.itwill.myleaves.repository.address.Address;
import com.itwill.myleaves.repository.address.AddressRepository;
import com.itwill.myleaves.repository.store.Store;

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
	
	/**
	 * 수빈
	 * 배송지 찾기(기본 배송지 저장 되어있는지 보려고)
	 */
	public Address read(String userId, int defAddr) {
		Address address = addressRepository.findByUserIdAndDefAddr(userId, defAddr);
		return address;
	}
	
	
	
	/**
	 * 수빈
	 * 기본 배송지 update
	 */
	@Transactional
	public void update(String userId, AddressUpdateDto dto) {
		log.info("update(dto={})", dto);
		
		Address entity = addressRepository.findByUserIdAndDefAddr(userId, 1);
		entity.update(dto);
	}
	
}
