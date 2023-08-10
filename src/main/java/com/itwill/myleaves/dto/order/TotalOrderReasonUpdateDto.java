package com.itwill.myleaves.dto.order;

import com.itwill.myleaves.repository.totalOrder.TotalOrder;

import lombok.Data;

@Data
public class TotalOrderReasonUpdateDto {
	
	private String reason;
	private String status;
	
	public TotalOrder toEntity() {
		return TotalOrder.builder()
				.reason(reason)
				.status(status)
				.build();
	}
}
