package com.itwill.myleaves.service.bookmark;

import org.springframework.stereotype.Service;

import com.itwill.myleaves.dto.bookmark.BookmarkDto;
import com.itwill.myleaves.repository.Planterior.Planterior;
import com.itwill.myleaves.repository.Planterior.PlanteriorRepository;
import com.itwill.myleaves.repository.bookmark.Bookmark;
import com.itwill.myleaves.repository.bookmark.BookmarkRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class BookmarkService {
	
	// 생성자 주입
	private final BookmarkRepository bookmarkRepository;
	private final PlanteriorRepository planteriorRepository;
	
	// 생성
	// boolean으로 값을 넘겨주면, 생성이 됬어 true가 리턴이 될 거고
	// 이 리턴 값을 모델로 보내줘서 th:if="model 값." 이거면 되잖아.
	// 그러면 일단 생성일때와 삭제일 때 다 restcontroller에서 넘겨주는 거니까
	// 되겠다.
	public Boolean create(BookmarkDto dto, String userId) {
		log.info("create(dto ={}, userId = {})", dto, userId);
		
		// 일단 찾아
		Planterior planterior = planteriorRepository.findById(dto.getPlanteriorId()).orElseThrow();
		
		// 엔터티 객체로 변환 
		Bookmark entity = Bookmark.builder()
				.platerior(planterior)
				.userId(userId)
				.build();
		
		// insert
		bookmarkRepository.save(entity);
		
		Boolean createResult = true;
		
		// 문제 발생할 경우 무조건 retrun entity바꿀것.
		return createResult;
	
	}
	
	// 삭제
	public void delete(String userId) {
		log.info("delete(userId = {})", userId);
		
		bookmarkRepository.deleteById(userId);
	}
	
}
