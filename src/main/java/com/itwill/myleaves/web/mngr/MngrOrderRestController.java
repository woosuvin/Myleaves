package com.itwill.myleaves.web.mngr;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.myleaves.dto.order.TotalOrderStatusUpdateDto;
import com.itwill.myleaves.service.totalOrder.TotalOrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mngr")
public class MngrOrderRestController {
	
	private final TotalOrderService totalOrderService;
	
	/**
	 * 수빈
	 * 주문 상태 변경
	 */
	@PutMapping("/order/{orderId}")
	public ResponseEntity<String> update(@PathVariable long orderId, @RequestBody TotalOrderStatusUpdateDto dto) {
		//log.info("update(orderId={}, dto={})", orderId, dto);
		totalOrderService.update(orderId, dto);
		return ResponseEntity.ok("Success");
	}

}
