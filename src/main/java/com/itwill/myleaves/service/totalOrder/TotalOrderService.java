package com.itwill.myleaves.service.totalOrder;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.myleaves.dto.order.TotalOrderCreateDto;
import com.itwill.myleaves.dto.order.TotalOrderUpdateDto;
import com.itwill.myleaves.repository.orderDetail.OrderDetail;
import com.itwill.myleaves.repository.orderDetail.OrderDetailRepository;
import com.itwill.myleaves.repository.totalOrder.TotalOrder;
import com.itwill.myleaves.repository.totalOrder.TotalOrderRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//지현

@Slf4j
@RequiredArgsConstructor
@Service
public class TotalOrderService {
	
	private final TotalOrderRepository totalOrderRepository;
	private final OrderDetailRepository orderDetailRepository;
	// delete 없음ㅁ
	
	// create는 결제가 완료되면 되는건데 form 제출을 어디서 하는지?
	public TotalOrder create(TotalOrderCreateDto dto) {
		TotalOrder entity = dto.toEntity();
		totalOrderRepository.saveAndFlush(entity);
		return entity;
	}
	
	/**
	 * 관리자 주문 관리용
	 * @return
	 */
	public List<TotalOrder> read(){
		log.info("mngr read()");
		return totalOrderRepository.findByOrderByOrderIdDesc();
	}
	
	/**
	 * 사용자 마이페이지
	 * @param UserId
	 * @return
	 */
	public List<TotalOrder> read(String UserId){
		log.info("mypage read()");
		return totalOrderRepository.findByUserIdOrderByOrderIdDesc(UserId);
	}
	
	/**
	 * 주문 상세 페이지
	 * @param orderId
	 * @return
	 */
	public TotalOrder read(long orderId) {
		return totalOrderRepository.findByOrderId(orderId);
	}
	
	public List<OrderDetail> readOrderDetails(long orderId) {
		return orderDetailRepository.findByOrderId(orderId);
	}
	
	/**
	 * 결제 완료 시 total_order update
	 * @param dto
	 * @return
	 */
	@Transactional
	public TotalOrder update(long orderId, TotalOrderUpdateDto dto) {
		log.info("update(orderId={} ,dto={})", orderId, dto);
		TotalOrder entity = totalOrderRepository.findByOrderId(orderId);
		log.info("before entity={}",entity);
		entity.update(dto);
		
		log.info("after entity={}", entity);
		return totalOrderRepository.saveAndFlush(entity);
	}
	
	
}
