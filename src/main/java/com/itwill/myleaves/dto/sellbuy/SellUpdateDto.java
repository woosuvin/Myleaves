package com.itwill.myleaves.dto.sellbuy;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class SellUpdateDto {

	private Long sellId;
	private String title;
	// clob string, blob byte[]
	private byte[] thumbnail;
	private String content;
	private Long price;
	private String sido;
	private String gungu;
	private String loc;
	
	private MultipartFile file;

}
