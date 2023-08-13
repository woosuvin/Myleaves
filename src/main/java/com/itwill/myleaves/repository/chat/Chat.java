package com.itwill.myleaves.repository.chat;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.itwill.myleaves.dto.chat.ChatCreateDto;
import com.itwill.myleaves.repository.BaseTimeEntity;
import com.itwill.myleaves.repository.member.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Entity
@Data
@Table(name = "CHAT")
@SequenceGenerator(name = "CHAT_SEQ_GEN", sequenceName = "CHAT_SEQ", allocationSize = 1)
public class Chat extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHAT_SEQ_GEN")
	private long chatId;
	
	@Column(nullable = false)
	private String userId;
	
	@Column(nullable = false)
	private long roomId;
	
	@Column(nullable = false)
	private String message;
	
	// MessageType 열거형 정의
    public enum MessageType {
        TALK
    }
	
//	/**
//	 * 채팅 내역
//	 * @param roomId
//	 * @param userId
//	 * @param message
//	 * @return
//	 */
//	public static Chat create(long roomId, String userId, String message) {
//		return Chat.builder()
//				.roomId(roomId)
//				.userId(userId)
//				.message(message)
//				.build();
//	}
	
}
