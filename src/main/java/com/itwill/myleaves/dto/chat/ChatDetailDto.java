package com.itwill.myleaves.dto.chat;

import java.time.LocalDateTime;

import com.itwill.myleaves.repository.chat.Chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 정지언
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatDetailDto {
	
	private long chatId;
	private String userId;
	private long roomId;
	private String message;
	private LocalDateTime createdDate;

}
