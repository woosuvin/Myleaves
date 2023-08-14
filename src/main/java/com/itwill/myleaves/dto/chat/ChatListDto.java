package com.itwill.myleaves.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatListDto {
	private long sellId;
	private String myId;
	private String otherId;
}
