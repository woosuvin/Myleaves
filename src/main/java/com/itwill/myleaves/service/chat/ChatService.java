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
public class ChatService {

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
    	log.info("myId={}, otherId={}", myId);
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
        ChatRoom entity = dto.toEntity();
        chatRoomRepository.save(entity);
        return entity;
    }
    
    /**
     * 채팅방 여부 확인
     * @param otherId
     * @param sellId
     * @return
     */
//    public List<ChatRoom> checkedRoom(String otherId, Long sellId) {
//        return chatRoomRepository.findByOtherIdAndSellId(otherId, sellId);
//    }
    
}

//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//
//import com.itwill.myleaves.dto.chat.ChatCreateDto;
//import com.itwill.myleaves.repository.chat.Chat;
//import com.itwill.myleaves.repository.chat.ChatRepository;
//import com.itwill.myleaves.repository.chat.ChatRoom;
//import com.itwill.myleaves.repository.chat.ChatRoomRepository;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//// 정지언
//@Slf4j
//@RequiredArgsConstructor
//@Service
//public class ChatService {
//	
//	private final ChatRepository chatRepository;
//	private final ChatRoomRepository chatRoomRepository;
//	
	
	
//	public Chat create(ChatCreateDto dto) {
//		log.info("create(dto={})", dto);
//		
//		Chat entity = dto.toEntity();
//		log.info("entity = {}", entity);
//		
//		chatRepository.save(entity);
//		log.info("entity = {}", entity );
//		
//		return entity;
//	}
	
	
//    public void handleChatMessage(String userId, long roomId, String message) {
//        Chat chat = new Chat();
//        chat.setUserId(userId);
//        chat.setRoomId(roomId);
//        chat.setMessage(message);
//        // 다른 필요한 필드 값 설정
//        
//        chatRepository.save(chat);
//        
//        // 연결된 모든 클라이언트에 메시지 발송
//        TextMessage textMessage = new TextMessage(userId + ": " + message);
//        for (WebSocketSession session : sessions) {
//            session.sendMessage(textMessage);
//        }
//    }
	
	
//	/**
//	 * 채팅방 리스트
//	 * @param myId
//	 * @return
//	 */
//	public List<ChatRoom> readChatRoom(String myId, Long sellId) {
//		log.info("readChatRoom()");
//		return chatRoomRepository.findByMyIdAndSellId(myId, sellId);
//	}
//	
//	/**
//	 * 채팅내역
//	 * @param roomId
//	 * @return
//	 */
//	public List<Chat> readChat(Long roomId) {
//		log.info("readChat()");
//		return chatRepository.findByRoomId(roomId);
//	}

