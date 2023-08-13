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

import java.util.Base64;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final SellService sellService;

//    @GetMapping("/chat/roomDetail")
//    public void chatRoomDetail(String myId, String otherId, Long sellId) {
//    	Sell sell = sellService.
//    }
    
	// 채팅방 리스트
    @GetMapping("/chat/room")
    public void chatRoom(@RequestParam String myId, @RequestParam(required = false) Long sellId, Model model) {
        log.info("chatRoom(myId={}, sellId={})", myId, sellId);
        List<ChatRoom> chatRoomList = chatService.readChatRoom(myId);
        Sell sell = null;
        if (sellId != null) {
            sell = sellService.read(sellId);
        }
        
        model.addAttribute("chatRoomList", chatRoomList);
        model.addAttribute("sell", sell); 
    }
	
//	// 채팅방 리스트
//	@GetMapping("/chatList")
//	public void chatRoom(String myId, Long sellId, Model model) {
//	    log.info("chatRoom(myId={}, sellId={})", myId, sellId);
//	    List<ChatRoom> chatRoomList = chatService.readChatRoom(myId);
//	    Sell sell = sellService.read(sellId);
//	    
//	    model.addAttribute("chatRoom", chatRoomList);
//	    model.addAttribute("sell", sell);
//	}
    
//    // 채팅 리스트 화면
//    @GetMapping("/room")
//    public String rooms(String myId, Long sellId, Long roomId, Model model) {
//    	log.info("rooms(sellId={}, roomId={}, myId={})", sellId, roomId, myId);
//    	
////    	List<ChatRoom> chatRoom = chatService.findAllRoom()
//        return "/chat/room";
//    }
//
//    // 채팅방 생성
//    @PostMapping("/room")
//    @ResponseBody
//    public ChatRoom createRoom(@RequestParam Long sellId, String myId, String otherId) {
//        return chatService.createRoom(sellId, myId, otherId);
//    }
//    
//    // 채팅방 입장 화면
//    @GetMapping("/room/enter/{roomId}")
//    public String roomDetail(Model model, @PathVariable Long roomId) {
//        model.addAttribute("roomId", roomId);
//        return "/chat/roomdetail";
//    }
//    
//    // 특정 채팅방 조회
//    @GetMapping("/room/{roomId}")
//    @ResponseBody
//    public ChatRoom roomInfo(@PathVariable Long roomId) {
//        return chatService.findById(roomId);
//    }
    
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

