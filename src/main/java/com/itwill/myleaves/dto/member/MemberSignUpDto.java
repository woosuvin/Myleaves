package com.itwill.myleaves.dto.member;

import lombok.Data;

@Data
public class MemberSignUpDto {

	private String userId;

	private String name;

	private String pwd;

	private String gender;

	private String birth;

	private String phone;

	private String email;
}
