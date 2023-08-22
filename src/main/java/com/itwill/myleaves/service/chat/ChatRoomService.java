package com.itwill.myleaves.service.chat;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.itwill.myleaves.dto.chat.ChatRoomCreateDto;
import com.itwill.myleaves.repository.chat.ChatRoom;
import com.itwill.myleaves.repository.chat.ChatRoomRepository;

import jakarta.annotation.PostConstruct;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatRoomService {

    private Map<Long, ChatRoom> chatRooms;
    private final ChatRoomRepository chatRoomRepository;
    
    @PostConstruct
    //의존관계 주입완료되면 실행되는 코드
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    // 채팅방 리스트 불러오기
    public List<ChatRoom> readChatRoom(Long sellId) {
    	// List<ChatRoom> result = new ArrayList<>(chatRooms.values());
        return chatRoomRepository.findBySellId(sellId);
    }
    
    // 상단바 채팅리스트 find by myId otherId
    public List<ChatRoom> readChatRoom(String myId) {
    	//log.info("myId={}, otherId={}", myId);
    	return chatRoomRepository.findByMyIdOrOtherId(myId, myId);
    }
    
    

    //채팅방 하나 불러오기
    public ChatRoom readChatRoom(String myId, String otherId, long sellId) {
    	return chatRoomRepository.findByMyIdAndOtherIdAndSellId(myId, otherId, sellId);
    }
    
    // 채팅방 하나 불러오기 roomId
    public ChatRoom readChatRoom(long roomId) {
    	return chatRoomRepository.findByRoomId(roomId);
    }

    //채팅방 생성
    public ChatRoom createRoom(ChatRoomCreateDto dto) {
    	//log.info("dto={}", dto);
        ChatRoom entity = dto.toEntity();
        //log.info("entity={}", entity);
        chatRoomRepository.saveAndFlush(entity);
        //log.info("entity={}", entity);
        return entity;
    }
    
}
