package com.itwill.myleaves.service.orderDetail;

import org.springframework.stereotype.Service;

import com.itwill.myleaves.repository.orderDetail.OrderDetail;
import com.itwill.myleaves.repository.orderDetail.OrderDetailRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderDetailService {
	
	private final OrderDetailRepository orderDetailRepository;
	
	public OrderDetail create(Long orderId, Long itemId, Long price, Long cnt) {
		OrderDetail entity = OrderDetail.builder()
				.orderId(orderId)
				.itemId(itemId)
				.price(price)
				.cnt(cnt)
				.build();
		orderDetailRepository.save(entity);
		return entity;
	}
	
}
