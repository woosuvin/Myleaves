package com.itwill.myleaves.web.chat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatController {

	@GetMapping("/chatList")
    public void chatList() {
		log.info("chatList() GET");
        // chatList.html에 필요한 데이터를 가져와서 model에 담아 전달
//        List<ChatMessage> chatMessages = // 데이터 가져오는 로직;
//        model.addAttribute("chatMessages", chatMessages);
        
//        return "chatList"; // chatList.html 파일의 경로와 이름을 반환
    }
	
	@GetMapping("/chatRoom")
	public void chatRoom() {
		log.info("chatRoom() GET");
		// chatList.html에 필요한 데이터를 가져와서 model에 담아 전달
//        List<ChatMessage> chatMessages = // 데이터 가져오는 로직;
//        model.addAttribute("chatMessages", chatMessages);
		
//        return "chatList"; // chatList.html 파일의 경로와 이름을 반환
	}
}
