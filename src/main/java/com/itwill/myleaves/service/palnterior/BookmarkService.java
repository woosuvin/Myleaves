package com.itwill.myleaves.service.palnterior;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.myleaves.dto.planterior.BookmarkDto;
import com.itwill.myleaves.repository.planterior.Bookmark;
import com.itwill.myleaves.repository.planterior.BookmarkRepository;
import com.itwill.myleaves.repository.planterior.Planterior;
import com.itwill.myleaves.repository.planterior.PlanteriorRepository;

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
	public Bookmark create(BookmarkDto dto) {
		log.info("create(dto ={})", dto);
		
		// 엔터티 객체로 변환 
		Bookmark entity = dto.toEntity();
		
		// insert
		bookmarkRepository.save(entity);
		
		// 문제 발생할 경우 무조건 retrun entity바꿀것.
		return entity;
	
	}
	
	// 삭제
	public void delete(Long PlanteriorId, String userId) {
		log.info("delete(planterior = {})", PlanteriorId, userId);
		
		bookmarkRepository.deleteByPlanteriorAndUserId(PlanteriorId, userId);
	}
	
	// 읽기
	@Transactional(readOnly = true)
	public List<Bookmark> read() {
		log.info("read()");
		
		return bookmarkRepository.findAll();
	}
	
}
