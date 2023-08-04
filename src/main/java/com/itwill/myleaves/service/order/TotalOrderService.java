package com.itwill.myleaves.service.order;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.myleaves.dto.order.TotalOrderCreateDto;
import com.itwill.myleaves.dto.order.TotalOrderUpdateDto;
import com.itwill.myleaves.repository.order.OrderDetail;
import com.itwill.myleaves.repository.order.OrderDetailRepository;
import com.itwill.myleaves.repository.order.TotalOrder;
import com.itwill.myleaves.repository.order.TotalOrderRepository;

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
		totalOrderRepository.save(entity);
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
	 * 관리자 주문관리에서 주문상태 수정
	 * 사용자 마이페이지 주문 상세에서 반품 신청 시 수정
	 * @param dto
	 * @return
	 */
	public TotalOrder update(TotalOrderUpdateDto dto) {
		log.info("update({})", dto);
		TotalOrder entity = totalOrderRepository.findById(dto.getOrderId()).orElseThrow();
		entity.update(dto);
		return totalOrderRepository.saveAndFlush(entity);
	}
}
