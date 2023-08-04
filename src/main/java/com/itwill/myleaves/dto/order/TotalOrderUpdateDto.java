package com.itwill.myleaves.dto.order;

import lombok.Data;

@Data
public class TotalOrderUpdateDto {

	private Long orderId;
	private String status;
	private String reason;
}
