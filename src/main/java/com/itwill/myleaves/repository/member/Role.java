package com.itwill.myleaves.repository.member;

public enum Role {
    USER("ROLE_MEMBER", "M"),
    ADMIN("ROLE_ADMIN", "A");

    private final String key;
    private final String name;

    Role(String key, String name) {
        this.key = key;
        this.name = name;
    }
    
    public String getKey() {
        return this.key;
    }
}