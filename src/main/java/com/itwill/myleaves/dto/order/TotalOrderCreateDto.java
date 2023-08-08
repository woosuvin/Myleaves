package com.itwill.myleaves.dto.order;


import com.itwill.myleaves.repository.totalOrder.TotalOrder;

import lombok.Data;

//지현 빈 totalOrder 만드는거
@Data
public class TotalOrderCreateDto {
	
	private String userId;
	
	
	public TotalOrder toEntity() {
		return TotalOrder.builder()
				.userId(userId)
				.build();
	}
}
