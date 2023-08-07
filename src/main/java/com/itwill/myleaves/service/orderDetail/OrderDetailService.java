package com.itwill.myleaves.service.orderDetail;

import org.springframework.stereotype.Service;

import com.itwill.myleaves.dto.order.OrderDetailCreateDto;
import com.itwill.myleaves.repository.orderDetail.OrderDetail;
import com.itwill.myleaves.repository.orderDetail.OrderDetailRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderDetailService {
	
	private final OrderDetailRepository orderDetailRepository;
	
	public OrderDetail create(OrderDetailCreateDto dto) {
		OrderDetail entity = dto.toEntity();
		orderDetailRepository.save(entity);
		return entity;
	}
	
}
