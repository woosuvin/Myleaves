package com.itwill.myleaves.dto.order;

import lombok.Data;

@Data
public class TotalOrderdSearchDto {
	private String searchUserId;
	private String searchStatus;
	private String searchOrderDateStart;
	private String searchOrderDateEnd;
}
