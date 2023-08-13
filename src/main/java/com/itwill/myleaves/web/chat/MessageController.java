package com.itwill.myleaves.web.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.myleaves.repository.chat.Chat;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessageSendingOperations sendingOperations;

    @MessageMapping("/chat/message")
    public void enter(Chat message) {
        sendingOperations.convertAndSend("/topic/chat/room/"+message.getRoomId(),message);
    }
}