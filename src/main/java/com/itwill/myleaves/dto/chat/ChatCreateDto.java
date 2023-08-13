package com.itwill.myleaves.dto.chat;

import com.itwill.myleaves.repository.chat.Chat;
import com.itwill.myleaves.repository.chat.ChatRoom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 정지언

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatCreateDto {
	private long chatId; // 채팅 고유 아이디
	private String userId;
	private long roomId;
	private String message;
	
	public Chat toEntity() {
		return Chat.builder()
				.userId(userId)
				.roomId(roomId)
				.message(message)
				.build();
	}

}
