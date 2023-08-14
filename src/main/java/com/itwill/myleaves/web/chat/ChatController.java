package com.itwill.myleaves.web.chat;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final SellService sellService;

    // 채팅방
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
    public void chatRoom(ChatRoom chatRoom, String otherId, Long sellId, Model model) {
    	 
    	List<ChatRoom> chatRoomList = chatService.readChatRoom(otherId);
    	List<Sell> sellList = new ArrayList<>();
    	for(ChatRoom c : chatRoomList) {
    		Sell sell = sellService.read(c.getSellId());
    		sellList.add(sell);
    	}
    	
    	Map<Long, String> thumbnails = new HashMap<>();
    	for(Sell sell: sellList){
        	thumbnails.put(sell.getSellId(), Base64.getEncoder().encodeToString(sell.getThumbnail()));
        }
        model.addAttribute("images", thumbnails);
		model.addAttribute("sell", sellList);
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

