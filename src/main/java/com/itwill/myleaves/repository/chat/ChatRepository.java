package com.itwill.myleaves.repository.chat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

// 정지언
public interface ChatRepository extends JpaRepository<Chat, Long> {
	
		// 채팅 내역 리스트
		List<Chat> findByRoomIdOrderByCreatedDate(Long roomId);
		
}
