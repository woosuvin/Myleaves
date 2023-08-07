package com.itwill.myleaves.dto.sellbuy;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import com.itwill.myleaves.repository.sellbuy.Sell;

import lombok.Data;
// 지현  
@Data
public class SellCreateDto {
	private Long sellId;
	private String title;
	private String userId;
	// clob string, blob byte[]
	private byte[] thumbnail;
	private String content;
	private Long price;
	private String sido;
	private String gungu;
	private String loc;
	private Long sold;
	
	private MultipartFile file;
	
	public Sell toEntity() {
		return Sell.builder()
				.title(title)
				.userId(userId)
				//.thumbnail(thumbnail)
				.content(content)
				.price(price)
				.sido(sido)
				.gungu(gungu)
				.loc(loc)
				.sold((long) 1)
				.build();
	}
}
