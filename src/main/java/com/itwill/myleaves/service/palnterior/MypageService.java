package com.itwill.myleaves.service.palnterior;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.myleaves.dto.planterior.PlanteriorUpdateDto;
import com.itwill.myleaves.repository.planterior.Bookmark;
import com.itwill.myleaves.repository.planterior.BookmarkRepository;
import com.itwill.myleaves.repository.planterior.Planterior;
import com.itwill.myleaves.repository.planterior.PlanteriorRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MypageService {

	private final PlanteriorRepository planteriorRepository;
	private final BookmarkRepository bookmarkRepository;

	/**
	 * 내가 쓴 글
	 */

	// 내가 쓴 글 가져오기
	@Transactional(readOnly = true)
	public Page<Planterior> read(String userId, Pageable pageable) {
		//log.info("read()");

		return planteriorRepository.findAllByUserIdOrderByPlanteriorIdDesc(userId, pageable);
	}

	// 수정페이지에 가져오는 것
	@Transactional(readOnly = true)
	public Planterior read(Long planteriorId) {
		log.info("read()");

		return planteriorRepository.findAllByPlanteriorId(planteriorId);
	}

	// 수정
	public Planterior update(PlanteriorUpdateDto dto) throws IOException {
		log.info("update(dto = {})", dto);

		Planterior entity = planteriorRepository.findById(dto.getPlanteriorId()).orElseThrow();
		entity.update(dto);
		return planteriorRepository.saveAndFlush(entity);
	}

	// 삭제
	public void delete(Long planteriorId) {
		log.info("delete(planteriorId = {})", planteriorId);

		Planterior entity = planteriorRepository.findByPlanteriorId(planteriorId);
		planteriorRepository.delete(entity);
	}

	/**
	 * 북마크
	 */

	public Page<Bookmark> bookmarkRead(String userId, Pageable pageabel) {
		Page<Bookmark> list = bookmarkRepository.findAllByUserId(userId, pageabel);
		
		return list;
	}
	
	// planteriorhomecontroller에서 사용
	public List<Bookmark> bookmarkRead(String userId) {
		// log.info("bookmarkRead(userId ={})", userId);

		return bookmarkRepository.findAllByUserId(userId);
	}
	
	@Transactional(readOnly = true)
	public List<Planterior> read() {
		log.info("read()");

		return planteriorRepository.findAllByOrderByPlanteriorIdDesc();
	}

}
