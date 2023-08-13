package com.itwill.myleaves.repository.chat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.myleaves.repository.sellbuy.BuyWish;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, ChatRoomId> {
	
	List<ChatRoom> findByMyId(String myId);
	
	List<ChatRoom> findBySellId(long sellId);
	
	// 채팅방 여부 확인
	List<ChatRoom> findByOtherIdAndSellId(String otherId, long sellId);
	
}
