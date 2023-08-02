package com.itwill.myleaves.service.palnterior;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.myleaves.dto.planterior.PlanteriorCreateDto;
import com.itwill.myleaves.repository.Planterior.Planterior;
import com.itwill.myleaves.repository.Planterior.PlanteriorRepository;

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
	
	@Transactional(readOnly = true)
	public List<Planterior> read() {
		log.info("read()");
		
		return planeteriorRepository.findAllByOrderByPlanteriorIdDesc();
	}

}
