package com.itwill.myleaves.dto.member;

import lombok.Data;

@Data
public class MemberUpdateDto {
	
	private String userId;
	
	private String gender;
	
	private String phone;
	
	private String birth;
}