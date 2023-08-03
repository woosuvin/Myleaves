package com.itwill.myleaves.service.sellbuy;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.myleaves.dto.sellbuy.SellCreateDto;
import com.itwill.myleaves.dto.sellbuy.SellUpdateDto;
import com.itwill.myleaves.repository.sellbuy.Sell;
import com.itwill.myleaves.repository.sellbuy.SellRepository;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class SellService {

	private final SellRepository sellRepository;
	
	public List<Sell> read(){
		log.info("read()");
		return sellRepository.findByOrderBySellIdDesc();
	}
	
	public Sell read(long sellId) {
		return sellRepository.findById(sellId).orElseThrow();
	}
	
	public Sell create(SellCreateDto dto) {
		log.info("create(dto={})", dto);
		
		Sell entity = dto.toEntity();
		log.info("entity = {}", entity);
		
		sellRepository.save(entity);
		log.info("entity = {}", entity);
		
		return entity;
	}
	
	public Sell update(SellUpdateDto dto) {
		log.info("update({})", dto);
		
		Sell entity = sellRepository.findById(dto.getSellId()).orElseThrow();
		entity.update(dto);
		return sellRepository.saveAndFlush(entity);
	}
	
	public void delete(long sellId) {
		log.info("delete(sellId={})", sellId);
		sellRepository.deleteById(sellId);
		
	}
}
