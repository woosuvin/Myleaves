package com.itwill.myleaves.service.chat;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.myleaves.dto.chat.ChatCreateDto;
import com.itwill.myleaves.repository.chat.Chat;
import com.itwill.myleaves.repository.chat.ChatRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {
	
	private final ChatRepository chatRepository;
	
	public Chat create(ChatCreateDto dto) {
		Chat entity = dto.toEntity();
		return chatRepository.save(entity);
	}
	
	/**
	 * 채팅 메세지 리스트
	 * @param roomId
	 * @return
	 */
	public List<Chat> read(Long roomId) {
		return chatRepository.findByRoomIdOrderByCreatedDate(roomId);
	}
}
