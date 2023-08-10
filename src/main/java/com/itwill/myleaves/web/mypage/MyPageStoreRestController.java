package com.itwill.myleaves.web.mypage;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.myleaves.dto.order.TotalOrderReasonUpdateDto;
import com.itwill.myleaves.service.totalOrder.TotalOrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mypage/store")
public class MyPageStoreRestController {

	private final TotalOrderService totalOrderService;
	
	
	@PutMapping("/orderDetail/{orderId}")
	public ResponseEntity<String> update(@PathVariable long orderId, @RequestBody TotalOrderReasonUpdateDto dto) {
		log.info("update(orderId={}, dto={})", orderId, dto);
		totalOrderService.updateReason(orderId, dto);
		return ResponseEntity.ok("Success");
	}
}
