package com.itwill.myleaves.web.community;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.myleaves.dto.community.CommunityCommentCreateDto;
import com.itwill.myleaves.dto.community.CommunityCommentDeleteDto;
import com.itwill.myleaves.dto.community.CommunityCommentUpdateDto;
import com.itwill.myleaves.repository.community_comment.CommunityComment;
import com.itwill.myleaves.service.community.CommunityCommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/communitycomment")
public class CommunityCommentRestController {

	private final CommunityCommentService communityCommentService;

	// 댓글 목록 불러오기
    @PreAuthorize("hasRole('MEMBER')") 
	@GetMapping("/all/{id}")
	public ResponseEntity<List<CommunityComment>> all(@PathVariable long id) {
		log.info("all(communityId)={}", id);

		List<CommunityComment> list = communityCommentService.read(id);
		return ResponseEntity.ok(list);
	}

	// 댓글 작성
	// request body안의 내용을 분석해서 객체로 만들어달라. cf) get 방식: 쿼리스트링에 있음.
	// ajax에서 post, put, delete: @RequestBody를 써줘야 동작함.
	// response의 의미: 하나의 데이터. 이 데이터 안에 data 속성이 있음.
	@PreAuthorize("hasRole('MEMBER')")
	@PostMapping
	public ResponseEntity<CommunityComment> create(@RequestBody CommunityCommentCreateDto dto) {
		log.info("Create(dto={})", dto);

		CommunityComment communityComment = communityCommentService.create(dto);
		log.info("communityComment={}", communityComment);

		return ResponseEntity.ok(communityComment);
	}

	// 댓글 삭제
	@PreAuthorize("hasRole('MEMBER')")
	@DeleteMapping("/{communityCommentId}")
	public ResponseEntity<String> delete(@PathVariable long communityCommentId) {
		log.info("delete(communityCommentId)={}", communityCommentId);

		communityCommentService.delete(communityCommentId);

		return ResponseEntity.ok("Success");
	}

	@DeleteMapping("/deletecomments")
	public ResponseEntity<Integer> deleteByCheckbox(
			@RequestBody List<CommunityCommentDeleteDto> selectedCommunityCommentIds) {
		log.info("deleteByCheckbox(selectedCommunityCommentIds={})", selectedCommunityCommentIds);

		for (CommunityCommentDeleteDto communityCommentDeleteDto : selectedCommunityCommentIds) {
			long communityCommentId = communityCommentDeleteDto.getCommunityCommentId();
			log.info("communityCommentId={}", communityCommentId);

			communityCommentService.delete(communityCommentId);
		}
		return ResponseEntity.ok(1);
	}


	// 댓글 업데이트
    @PreAuthorize("hasRole('MEMBER')")
	@PutMapping("/{communityCommentId}")
	public ResponseEntity<String> update(@PathVariable long communityCommentId,
			@RequestBody CommunityCommentUpdateDto dto) {
		log.info("update(dto={})", dto);

		communityCommentService.update(communityCommentId, dto);

		return ResponseEntity.ok("Success");
	}
}
