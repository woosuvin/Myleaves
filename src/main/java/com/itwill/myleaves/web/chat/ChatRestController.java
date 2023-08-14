package com.itwill.myleaves.web.chat;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.myleaves.dto.chat.ChatListDto;
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
	 	 
//	    @PostMapping("/startChat")
//	    public ResponseEntity<String> startChat(@RequestParam long sellId, @RequestParam String myId, @RequestParam String otherId) {
//	        ChatRoom chatRoom = ChatRoom.create(sellId, myId, otherId);
//	        chatRoomRepository.save(chatRoom);
//	        return ResponseEntity.ok("Chat room created");
//	    }
	 
	 // 관심목록 하던것마냥 해보기... 근데 실패인듯
//	 @PostMapping
//	 public ResponseEntity<ChatRoom> createChatRoom(@RequestParam Long sellId, @RequestParam String myId, @RequestParam String otherId) {
//		 ChatRoom chat = chatService.createRoom(sellId, myId, otherId);
//		 
//		 return ResponseEntity.ok(chat);
//	 }
	    
//	 @PostMapping("/startChat")
//	 public ResponseEntity<String> startChat(@RequestParam Long sellId, @RequestParam String myId, @RequestParam String otherId) {
//	     List<ChatRoom> checkedChatRooms = chatService.checkedRoom(otherId, sellId);
//	     
//	     if (checkedChatRooms != null && !checkedChatRooms.isEmpty()) {
//	         // 이미 존재하는 채팅방인 경우
//	         String htmlResponse = checkedChatRoom(checkedChatRooms.get(0));
//	         return ResponseEntity.ok(htmlResponse);
//	     } else {
//	         // 채팅방 생성
//	         ChatRoom chatRoom = ChatRoom.create(sellId, myId, otherId);
//	         chatRoomRepository.save(chatRoom);
//	         return ResponseEntity.ok("New chat room created.");
//	     }
//	 }
//	 
//	 private String checkedChatRoom(ChatRoom chatRoom) {
//		    return "Chat room already exists.";
//		}
	 
	  @PostMapping("/startChat")
	  @ResponseBody
	    public ResponseEntity<String> startChat(@RequestBody ChatListDto dto) {
		  	log.info("dto = {}", dto);
	        List<ChatRoom> checkedChatRooms = chatService.checkedRoom(dto.getOtherId(), dto.getSellId());
	        
	        if (checkedChatRooms != null && !checkedChatRooms.isEmpty()) {
	            return ResponseEntity.ok("Chat room already exists.");
	        } else {
	            ChatRoom chatRoom = ChatRoom.create(dto.getSellId(), dto.getMyId(), dto.getOtherId());
	            chatRoomRepository.save(chatRoom);
	            return ResponseEntity.ok("New chat room created.");
	        }
	        
	    }

}
