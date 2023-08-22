package com.itwill.myleaves.web.chat;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.myleaves.dto.chat.ChatCreateDto;
import com.itwill.myleaves.repository.chat.Chat;
import com.itwill.myleaves.service.chat.ChatService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MessageRestController {

    private final SimpMessageSendingOperations sendingOperations;
    private final ChatService chatService;

    @MessageMapping("/chat/message")
    public void enter(Chat message) {
        sendingOperations.convertAndSend("/topic/chat/room/"+message.getRoomId(),message);
    }
    
    @PostMapping("/chat/create")
    public ResponseEntity<String> create(@RequestBody ChatCreateDto dto) {
    	//log.info("dto={}", dto);
    	chatService.create(dto);
    	return ResponseEntity.ok("success");
    }
    
    @GetMapping("/chat/{roomId}")
    public ResponseEntity<List<Chat>> read(@PathVariable Long roomId) {
    	List<Chat> list = chatService.read(roomId);
    	return ResponseEntity.ok(list);
    }
    
    
    
}