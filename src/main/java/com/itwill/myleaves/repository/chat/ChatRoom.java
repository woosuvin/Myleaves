package com.itwill.myleaves.repository.chat;

import java.util.UUID;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

//import com.itwill.myleaves.dto.chat.ChatCreateDto;
//import com.itwill.myleaves.dto.chat.ChatRoomCreateDto;

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

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Entity
@Data
@IdClass(ChatRoomId.class)
@Table(name = "CHAT_ROOM")
@SequenceGenerator(name = "CHAT_ROOM_SEQ_GEN", sequenceName = "CHAT_ROOM_SEQ", allocationSize = 1)
public class ChatRoom {
// 정지언
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHAT_ROOM_SEQ_GEN")
	private long roomId;
	
	@Id
    @Column(name = "sell_Id")
    private long sellId;
    
    @Column(nullable = false)
    private String myId;
	
    @Id
    @Column(nullable = false)
    private String otherId;

    /**
     * 채팅방 생성
     */
    public static ChatRoom create(long sellId, String myId, String otherId) {
	    return ChatRoom.builder()
	    		.sellId(sellId)
	    		.myId(myId)
	    		.otherId(otherId)
	    		.build();
    }
}
