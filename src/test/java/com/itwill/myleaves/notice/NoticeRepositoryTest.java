package com.itwill.myleaves.notice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.myleaves.MyleavesApplication;
import com.itwill.myleaves.repository.notice.Notice;
import com.itwill.myleaves.repository.notice.NoticeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(classes = MyleavesApplication.class)
public class NoticeRepositoryTest {

    @Autowired
    private NoticeRepository noticeRepository;
    
    @Test
    public void testSave() {
    	for(int i = 1; i <= 100; i++) {
    		Notice entity = Notice.builder()
					.title("[종료] 내풀잎스 " + i + "일차 기념 감사 인사")
					.content("<div><p style=\"text-align: center; \"><b><span style=\"font-size: 28px;\">내풀잎스 공지</span></b></p><img src=\"https://blog.kakaocdn.net/dn/bfNoMG/btqKvPU4UaM/MeYQp3uOfXV9fka9FHXAO0/img.jpg\" srcset=\"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&amp;fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbfNoMG%2FbtqKvPU4UaM%2FMeYQp3uOfXV9fka9FHXAO0%2Fimg.jpg\" data-filename=\"1 상황별 감사인사말 모음 감사합니다 이미지 모음집 (1).jpg\" data-origin-width=\"1200\" data-origin-height=\"1200\" data-ke-mobilestyle=\"widthContent\" onerror=\"this.onerror=null; this.src='//t1.daumcdn.net/tistory_admin/static/images/no-image-v1.png'; this.srcset='//t1.daumcdn.net/tistory_admin/static/images/no-image-v1.png';\"></div>")
					.fix(0)
					.build();
    		
    		noticeRepository.save(entity);
    	}

    }
    
    
}
