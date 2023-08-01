package com.example.demo.repository.notice;

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
    	for(int i = 0; i < 100; i++) {
    		Notice entity = Notice.builder()
					.title("test" + i)
					.content("<p>test" + i + "</p>")
					.fix(0)
					.build();
    		
    		noticeRepository.save(entity);
    	}

    }
    
    
}
