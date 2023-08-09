package com.itwill.myleaves.dto.notice;

import com.itwill.myleaves.repository.notice.Notice;

import lombok.Data;

@Data
public class NoticeCreateDto {
    
    private String title;
    private String content;
    private int fix;
    
    // DTO를 엔터티 객체로 변환해서 리턴하는 메서드:
    public Notice toEntity() {
        return Notice.builder()
                .title(title)
                .content(content)
                .fix(fix)
                .views(0)
                .build();
    }

}
