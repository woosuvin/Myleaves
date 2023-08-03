package com.itwill.myleaves.repository.member;

public interface EmailService {
	String sendSimpleMessage(String to) throws Exception;
}