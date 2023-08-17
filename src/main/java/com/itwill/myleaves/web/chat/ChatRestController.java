package com.itwill.myleaves.web.chat;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.myleaves.dto.chat.ChatRoomCreateDto;
import com.itwill.myleaves.repository.chat.ChatRoom;
import com.itwill.myleaves.repository.chat.ChatRoomRepository;
import com.itwill.myleaves.service.chat.ChatRoomService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatRestController {
	
	 private final ChatRoomService chatService;
	 private final ChatRoomRepository chatRoomRepository;  // ChatRoomRepository 인터페이스를 사용하도록 가정
	 	 
	 
	  	@PostMapping("/startChat")
	    public ResponseEntity<Long> startChat(@RequestBody ChatRoomCreateDto dto) {
		  	log.info("dto = {}", dto);
	        ChatRoom checkedChatRooms = chatService.readChatRoom(dto.getMyId(), dto.getOtherId(), dto.getSellId());
	        log.info("checkedChatRooms={}", checkedChatRooms);
	        
	        if (checkedChatRooms != null) { // DB에 있으면
	            return ResponseEntity.ok(chatService.readChatRoom(dto.getMyId(), dto.getOtherId(), dto.getSellId()).getRoomId());
	        } else { // DB에 없으면
	        	ChatRoom chatRoom = chatService.createRoom(dto);
	        	
	        	ChatRoom newChatRoom = chatService.readChatRoom(dto.getMyId(), dto.getOtherId(), dto.getSellId());
	        	
	            return ResponseEntity.ok(newChatRoom.getRoomId());
	        }
	        
	    }

}
