package com.itwill.myleaves.web.planterior;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.myleaves.dto.planterior.BookmarkDto;
import com.itwill.myleaves.repository.planterior.Bookmark;
import com.itwill.myleaves.repository.planterior.Planterior;
import com.itwill.myleaves.service.palnterior.BookmarkService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/planterior/home")
public class BookmarkRestController {
	
	private final BookmarkService bookmarkService;
	
	@PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
	@PostMapping("/like")
	public Bookmark createBookmark(@RequestBody BookmarkDto dto) {
		log.info("createBookmark(dto = {}), dto");
		
		Bookmark bookmark = bookmarkService.create(dto);
		log.info("bookmark={}", bookmark);
		
		return bookmark;
	}
	
	
	@PreAuthorize("hasRole('MEMBER')")
	@DeleteMapping("/delete/{planteriorId}/{userId}")
	@ResponseBody
	public ResponseEntity<String> deleteBookmark(@PathVariable long planteriorId, @PathVariable String userId) {
		log.info("deleteBookmark(planteriorId = {}, userId={})", planteriorId, userId);
		
		bookmarkService.delete(planteriorId, userId);
		
		return ResponseEntity.ok("Success");
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Integer> deleteMngr(@RequestBody List<BookmarkDto> data) {
		log.info("deleteMngr(data = {})", data);
		
		for(BookmarkDto dto: data) {
			long planteriorId = dto.getPlanteriorId();
			log.info("planteriorId={}", planteriorId );
			
			bookmarkService.delete(planteriorId, dto.getUserId());
		}
		return ResponseEntity.ok(1);
	}

}
