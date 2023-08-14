package com.itwill.myleaves.stomp;

public class HelloMessage {
	
	private String message;
    private String name;

    public HelloMessage() {
    }

    public HelloMessage(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getMessage() {
    	return message;
    }
    
    public void setMessage(String message) {
    	this.message = message;
    }
}
