package com.itwill.myleaves.dto.chat;

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
	
//	public static ChatDetailDto toChatDetailDto(Chat chat) {
//		ChatDetailDto chatDetailDto = new ChatDetailDto();
//		
//		chatDetailDto.setChatId(chat.getChatId());
//		
//		chatDetailDto.setRoomId(chat.getRoomId());
//		chatDetailDto.setUserId(chat.getUserId());
//		chatDetailDto.setMessage(chat.getMessage());
//		
//		return chatDetailDto;
//		
//	}

}
