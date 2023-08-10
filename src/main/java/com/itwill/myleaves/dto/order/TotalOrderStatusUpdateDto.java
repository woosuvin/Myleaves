package com.itwill.myleaves.dto.order;

import com.itwill.myleaves.repository.totalOrder.TotalOrder;

import lombok.Data;

@Data
public class TotalOrderStatusUpdateDto {
	
	private String status;
	
	public TotalOrder toEntity() {
		return TotalOrder.builder()
				.status(status)
				.build();
	}
		
}
