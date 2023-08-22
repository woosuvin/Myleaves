package com.itwill.myleaves.repository.chat;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Greeting {
	
	private String id;
    private String content;

}
