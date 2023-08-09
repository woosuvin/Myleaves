package com.itwill.myleaves.web.planterior;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.myleaves.repository.planterior.Planterior;
import com.itwill.myleaves.service.palnterior.BookmarkService;
import com.itwill.myleaves.service.palnterior.PlanteriorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/planteriors")
public class pagingRestController {
	
	private final PlanteriorService planteriorService;
	
	@GetMapping
	public Slice<Planterior> result( 
			@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
		
		log.info("더보기 페이징");
		
		Pageable pageable = PageRequest.of(page, size);
        return planteriorService.read(page,size);
	}

}
