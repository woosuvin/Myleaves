package com.itwill.myleaves.dto.chat;

import com.itwill.myleaves.repository.chat.ChatRoom;

import lombok.Data;

@Data
public class ChatRoomCreateDto {
	private Long sellId;
	private String myId;
	private String otherId;
	
	public ChatRoom toEntity() {
		return ChatRoom.builder()
				.sellId(sellId)
				.myId(myId)
				.otherId(otherId)
				.build();
	}
}
