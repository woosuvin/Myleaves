package com.itwill.myleaves.web.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.itwill.myleaves.repository.chat.Chat;
import com.itwill.myleaves.stomp.Greeting;

@Controller
public class MessageController {

    @MessageMapping("/sendMessage")
    @SendTo("/connect/chatting")
    public Greeting greeting(Chat message) throws Exception {
        Thread.sleep(100);
        return new Greeting(message.getUserId(), message.getMessage());
    }
}
