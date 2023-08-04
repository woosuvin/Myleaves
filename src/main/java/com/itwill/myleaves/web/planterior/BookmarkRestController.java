package com.itwill.myleaves.web.planterior;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.myleaves.dto.planterior.BookmarkDto;
import com.itwill.myleaves.repository.Planterior.Bookmark;
import com.itwill.myleaves.repository.Planterior.Planterior;
import com.itwill.myleaves.service.palnterior.BookmarkService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/planterior/home")
public class BookmarkRestController {
	
	private final BookmarkService bookmarkService;
	
	@PostMapping("/like")
	public Bookmark createBookmark(@RequestBody BookmarkDto dto) {
		log.info("createBookmark(dto = {}), dto");
		
		Bookmark bookmark = bookmarkService.create(dto);
		log.info("bookmark={}", bookmark);
		
		return bookmark;
	}
	
	
	@DeleteMapping("/delete/{planteriorId}/{userId}")
	@ResponseBody
	public ResponseEntity<String> deleteBookmark(@PathVariable long planteriorId, @PathVariable String userId) {
		log.info("deleteBookmark(planteriorId = {}, userId={})", planteriorId, userId);
		
		bookmarkService.delete(planteriorId, userId);
		
		return ResponseEntity.ok("Success");
	}
	

}
