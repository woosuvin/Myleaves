package com.itwill.myleaves.repository.chat;

import java.io.Serializable;

import lombok.Data;

@Data
public class ChatRoomId implements Serializable {
	private long roomId;
	private long sellId;
	private String otherId;

}
