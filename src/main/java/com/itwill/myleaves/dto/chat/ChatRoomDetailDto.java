package com.itwill.myleaves.dto.chat;

import com.itwill.myleaves.repository.chat.ChatRoom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomDetailDto {
	private long roomId;
	private long sellId;
	private String myId;
	private String otherId;
	
//	public static ChatRoomDetailDto toChatRoomDetailDto(ChatRoom chatRoom) {
//		ChatRoomDetailDto chatRoomDetailDto = new ChatRoomDetailDto();
//		
//		chatRoomDetailDto.setRoomId(chatRoom.getRoomId());
//		chatRoomDetailDto.setSellId(chatRoom.getSellId());
//		chatRoomDetailDto.setMyId(chatRoom.getMyId());
//		chatRoomDetailDto.setOtherId(chatRoom.getOtherId());
//		
//		return chatRoomDetailDto;
//		
//	}
	
}
