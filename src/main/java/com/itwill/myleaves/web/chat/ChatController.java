package com.itwill.myleaves.web.chat;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.itwill.myleaves.repository.chat.ChatRoom;
import com.itwill.myleaves.repository.sellbuy.Sell;
import com.itwill.myleaves.service.chat.ChatService;
import com.itwill.myleaves.service.sellbuy.SellService;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 정지언
@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final SellService sellService;

    // 채팅방
    @PreAuthorize("hasRole('MEMBER')")
    @GetMapping("/chat/roomDetail")
    public void chatRoomDetail(String myId, String otherId, long sellId, Model model) {
    	 Sell sell = sellService.read(sellId);
    	 String image = Base64.getEncoder().encodeToString(sell.getThumbnail());
 		
 		 model.addAttribute("image", image);
    	 model.addAttribute("sell", sell);
    }
    
	// 채팅방 리스트
    @PreAuthorize("hasRole('MEMBER')")
    @GetMapping("/chat/room")
    public void chatRoom(String otherId, Model model) {
        
        // 해당 아이디로 채팅방 리스트를 불러옴
        List<ChatRoom> chatRoomList = chatService.readChatRoom(otherId);
        List<Sell> sellList = new ArrayList<>();
        
        // 해당 리스트에 있는 sellId로 해당 물건의 썸네일 찾음
        Map<Long, String> thumbnails = new HashMap<>();
        for(ChatRoom c : chatRoomList) {
            // 해당 sellId로 Sell 검색
            Sell sell = sellService.read(c.getSellId());
            sellList.add(sell);
            // thumbnails에 넣음.
            thumbnails.put(c.getSellId(), Base64.getEncoder().encodeToString(sell.getThumbnail()));
        }
        
        model.addAttribute("sell", sellList);
        model.addAttribute("image", thumbnails);
        model.addAttribute("chatRoom", chatRoomList);
    }
}

//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.messaging.handler.annotation.DestinationVariable;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.itwill.myleaves.dto.chat.ChatCreateDto;
//import com.itwill.myleaves.repository.chat.Chat;
//import com.itwill.myleaves.repository.chat.ChatRoom;
//import com.itwill.myleaves.repository.sellbuy.Sell;
//import com.itwill.myleaves.service.chat.ChatService;
//import com.itwill.myleaves.service.sellbuy.SellService;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Controller
//@RequiredArgsConstructor
//@RequestMapping("/chat")
//public class ChatController {
//	
//	private final ChatService chatService;
//	private final SellService sellService;
//	
	

//	@MessageMapping("/{roomId}")
//	@SendTo("/chatRoom/{roomId}")
//	public Chat test(@DestinationVariable ChatCreateDto dto) {
//		
//		// 채팅 저장
//		Chat chat = chatService.create(dto);
//		return Chat.builder()
//				.roomId(chat.getRoomId())
//				.userId(chat.getUserId())
//				.message(chat.getMessage())
//				.build();
//	}
//		
//	// 채팅 내역
//	@GetMapping("/chatList")
//    public void chatList(Long roomId, Long sellId, Model model) {
//		log.info("chatList(roomId={}", roomId);
//		List<Chat> chat = chatService.readChat(roomId);
//		log.info("{}", chat);
//		
//		Sell sell = sellService.read(sellId);
//		
//		model.addAttribute("sell", sell);
//		model.addAttribute("chat", chat);
//    }
//	

