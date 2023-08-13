package com.itwill.myleaves.dto.chat;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.web.socket.WebSocketSession;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 정지언
// 채팅방(리스트)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomCreateDto {
	
	private long roomId;
	private long sellId;
	private String myId;
	private String otherId;
	
	private Set<WebSocketSession> sessions = new HashSet<>();
	
//	public static ChatRoomCreateDto create(long sellId, String myId, String otherId) {
//		ChatRoomCreateDto room = new ChatRoomCreateDto();
//		
//		room.sellId = sellId;
//		room.myId = myId;
//		room.otherId = otherId;
//		
//		return room;
//	}
	
}
