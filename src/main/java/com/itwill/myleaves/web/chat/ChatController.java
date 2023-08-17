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
import com.itwill.myleaves.service.chat.ChatRoomService;
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
    private final ChatRoomService chatService;
    private final SellService sellService;

    // 채팅방
    @PreAuthorize("hasRole('MEMBER')")
    @GetMapping("/chat/roomDetail")
    public void chatRoomDetail(long roomId, Model model) {
    	log.info("roomId={}", roomId);
    	ChatRoom chatRoom = chatService.readChatRoom(roomId);
   	 	log.info("chatRoom={}", chatRoom);
    	 Sell sell = sellService.read(chatRoom.getSellId());
    	 log.info("sell={}", sell);
    	 
    	 String image = Base64.getEncoder().encodeToString(sell.getThumbnail());
 		 
    	 model.addAttribute("chatRoom", chatRoom);
 		 model.addAttribute("image", image);
    	 model.addAttribute("sell", sell);
    }
    
	// sellbuyDetail 대화내역 채팅방 리스트
    @PreAuthorize("hasRole('MEMBER')")
    @GetMapping("/chat/room")
    public void chatRoom(Long sellId, Model model) {
        
        // 해당 아이디로 채팅방 리스트를 불러옴
        List<ChatRoom> chatRoomList = chatService.readChatRoom(sellId);
        //List<Sell> sellList = new ArrayList<>();
        Map<Long, Sell> sellMap = new HashMap<>();
        
        // 해당 리스트에 있는 sellId로 해당 물건의 썸네일 찾음
        Map<Long, String> thumbnails = new HashMap<>();
        for(ChatRoom c : chatRoomList) {
            // 해당 sellId로 Sell 검색
            Sell sell = sellService.read(c.getSellId());
            //sellList.add(sell);
            sellMap.put(c.getRoomId(), sell);
            // thumbnails에 넣음.
            thumbnails.put(c.getSellId(), Base64.getEncoder().encodeToString(sell.getThumbnail()));
        }
        model.addAttribute("map", sellMap);
        //model.addAttribute("sell", sellList);
        model.addAttribute("image", thumbnails);
        model.addAttribute("chatRoom", chatRoomList);
    }
    
    // 상단 바 채팅 리스트(myId or otherId)
    @PreAuthorize("hasRole('MEMBER')")
    @GetMapping("/chat/list")
    public void chat(String myId, Model model) {
        
        // 해당 아이디로 채팅방 리스트를 불러옴
        List<ChatRoom> chatRoomList = chatService.readChatRoom(myId);
        log.info("chatRoomList={}",chatRoomList);
        List<Sell> sellList = new ArrayList<>();
        Map<Long, Sell> sellMap = new HashMap<>();
        
        // 해당 리스트에 있는 sellId로 해당 물건의 썸네일 찾음
        Map<Long, String> thumbnails = new HashMap<>();
        for(ChatRoom c : chatRoomList) {
            // 해당 sellId로 Sell 검색
            Sell sell = sellService.read(c.getSellId());
            log.info("sell={}",sell);
            //sellList.add(sell);
            sellMap.put(c.getRoomId(), sell);
            // thumbnails에 넣음.
            thumbnails.put(c.getSellId(), Base64.getEncoder().encodeToString(sell.getThumbnail()));
        }
        
        //model.addAttribute("sell", sellList);
        model.addAttribute("map", sellMap);
        model.addAttribute("image", thumbnails);
        model.addAttribute("chatRoom", chatRoomList);
    }
    
}
