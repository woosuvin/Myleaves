package com.itwill.myleaves.dto.wish;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishStoreCreateDto {

		private String userId;
		private long itemId;
	
}
