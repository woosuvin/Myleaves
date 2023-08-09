package com.itwill.myleaves.service.palnterior;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.itwill.myleaves.dto.planterior.PlanteriorCreateDto;
import com.itwill.myleaves.repository.planterior.Planterior;
import com.itwill.myleaves.repository.planterior.PlanteriorRepository;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlanteriorService {

	//static 의존성 주입.
	private final PlanteriorRepository planeteriorRepository;
	
	public Planterior create(PlanteriorCreateDto dto) {
		log.info("create(dto={})",dto);
		
		// DTO를 Entity로 변환
		Planterior entity = dto.toEntity();
		log.info("save전 entity{} = ", entity);
		
		// DB에 저장
		planeteriorRepository.save(entity);
		log.info("save 후 entity={} = ", entity );
		
		return entity;
		
	}
	
	// 페이징 처리 포함된 전체 읽기
	@Transactional(readOnly = true)
	public Slice<Planterior> read(int pageNumber, int pageSize) {
		log.info("read(pageNumber = {}, pageSize ={})", pageNumber, pageSize);
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "planteriorId"));
		
		return planeteriorRepository.findAllByOrderByPlanteriorIdDesc(pageable);
	}
	
	// 페이징 처리 미포함된 전체 읽기
	@Transactional(readOnly = true)
	public List<Planterior> read() {
		log.info("read()");
		
		return planeteriorRepository.findAllByOrderByPlanteriorIdDesc();
	}

}
