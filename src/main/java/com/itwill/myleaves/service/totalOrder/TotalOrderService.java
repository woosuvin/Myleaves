package com.itwill.myleaves.service.totalOrder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.myleaves.dto.order.TotalOrderCreateDto;
import com.itwill.myleaves.dto.order.TotalOrderReasonUpdateDto;
import com.itwill.myleaves.dto.order.TotalOrderStatusUpdateDto;
import com.itwill.myleaves.dto.order.TotalOrderUpdateDto;
import com.itwill.myleaves.dto.order.TotalOrderdSearchDto;
import com.itwill.myleaves.repository.orderDetail.OrderDetail;
import com.itwill.myleaves.repository.orderDetail.OrderDetailRepository;
import com.itwill.myleaves.repository.totalOrder.TotalOrder;
import com.itwill.myleaves.repository.totalOrder.TotalOrderRepository;

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
		return totalOrderRepository.findByOrderByOrderDateDesc();
	}
	
	/**
	 * 수빈
	 * 관리자 주문 상태 수정
	 */
	public TotalOrder update(long orderId, TotalOrderStatusUpdateDto dto) {
		log.info("update(orderId={} ,dto={})", orderId, dto);
		TotalOrder entity = totalOrderRepository.findByOrderId(orderId);
		entity.updateStatus(dto);
		return totalOrderRepository.saveAndFlush(entity);
	}
	
	/**
	 * 수빈
	 * 관리자 주문 상태로 검색
	 */
	public List<TotalOrder> searchStatus(String status) {
		log.info("searchStatus");
		return totalOrderRepository.findByStatus(status);
	}
	
	/**
	 * 수빈
	 * 마이페이지 주문 취소, 취소 사유 업데이트
	 */
	public TotalOrder updateReason(long orderId, TotalOrderReasonUpdateDto dto) {
		log.info("update(orderId={} ,dto={})", orderId, dto);
		TotalOrder entity = totalOrderRepository.findByOrderId(orderId);
		entity.updateReason(dto);
		return totalOrderRepository.saveAndFlush(entity);
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
	
	@Transactional(readOnly = true)
	public List<TotalOrder> search(TotalOrderdSearchDto dto) throws ParseException{
		return totalOrderRepository
				.search(dto.getSearchUserId(), dto.getSearchStatus()
						,dto.getSearchOrderDateStart(), dto.getSearchOrderDateEnd());
		
	}
	
}
