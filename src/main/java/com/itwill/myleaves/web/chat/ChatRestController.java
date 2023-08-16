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
import com.itwill.myleaves.service.chat.ChatService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatRestController {
	
	 private final ChatService chatService;
	 private final ChatRoomRepository chatRoomRepository;  // ChatRoomRepository 인터페이스를 사용하도록 가정
	 	 
	 // 채팅리스트에서 채팅방 불러오기
//	 @GetMapping("/startChat")
//	 @ResponseBody
//	 public ResponseEntity<String> startChat(@RequestParam String sellId, @RequestParam String myId, @RequestParam String otherId) {
//	     ChatRoom chatRoom = ChatRoom.create(sellId, myId, otherId);
//	     chatRoomRepository.save(chatRoom);
//	     return ResponseEntity.ok("Chat room created.");
//	 }
	 
	  @PostMapping("/startChat")
	  @ResponseBody
	    public ResponseEntity<Long> startChat(@RequestBody ChatRoomCreateDto dto) {
		  	log.info("dto = {}", dto);
	        ChatRoom checkedChatRooms = chatService.readChatRoom(dto.getMyId(), dto.getOtherId(), dto.getSellId());
	        log.info("checkedChatRooms={}", checkedChatRooms);
	        
	        if (checkedChatRooms != null) {
//	        	ChatRoom chat = c hatService.readChatRoom(dto.getMyId(), dto.getOtherId());
//	 	        log.info("charRoom={}", chat);
	            return ResponseEntity.ok(chatService.readChatRoom(dto.getMyId(), dto.getOtherId(), dto.getSellId()).getRoomId());
	        } else {
	        	ChatRoom chatRoom = chatService.createRoom(dto);
	        	long roomId = chatRoom.getRoomId();
	            return ResponseEntity.ok(roomId);
	        }
	        
	    }

}
