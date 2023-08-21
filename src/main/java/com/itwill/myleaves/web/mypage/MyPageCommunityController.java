package com.itwill.myleaves.web.mypage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.myleaves.dto.community.CommunitySearchDto;
import com.itwill.myleaves.repository.community.Community;
import com.itwill.myleaves.repository.community_comment.CommunityComment;
import com.itwill.myleaves.service.mypage.MypageCommunityService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/community")
public class MyPageCommunityController {
	private final MypageCommunityService mypageCommunityService;

	@GetMapping("/my_posts")
	public void read(Model model, @RequestParam("userId") String userId, @PageableDefault(page=0, size=10, sort="communityId", direction=Sort.Direction.DESC) Pageable pageable) {
//		log.info("my_posts Controller read()");

		Page<Community> list = mypageCommunityService.read(userId, pageable);
		 int nowPage = list.getPageable().getPageNumber() + 1; // 현재페이지
	     int startPage =  Math.max(nowPage - 4, 1); // 시작 페이지
	     int endPage = Math.min(nowPage +5, list.getTotalPages()); // 끝 페이지
	       
		model.addAttribute("posts", list);
	    model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
	}

	@GetMapping("/my_comments")
	public void readPostsAndComments(Model model, @RequestParam("userId") String userId,  @PageableDefault(page=0, size=10, sort="communityId", direction=Sort.Direction.DESC) Pageable pageable) {
//		log.info("my_comments Controller read()");

//	    List<Community> postList = mypageCommunityService.read(userId);

		Map<Long, Community> communityList = new HashMap<>();
		// userId로 사용자의 댓글 list 가져옴
		Page<CommunityComment> commentList = mypageCommunityService.readComments(userId, pageable);
		// list 반복 -> 댓글 id(key), 댓글의 게시글(Type: Community -> 게시글 id)를 가져와 Map에 저장
		// 댓글 키로 해당 게시글(value)을 찾음.
		for (CommunityComment comment : commentList) {
			communityList.put(comment.getCommunityCommentId(),
					mypageCommunityService.readByCommunityId(comment.getCommunityId().getCommunityId()));
		}
		int nowPage = commentList.getPageable().getPageNumber() + 1; // 현재페이지
	    int startPage =  Math.max(nowPage - 4, 1); // 시작 페이지
	    int endPage = Math.min(nowPage +5, commentList.getTotalPages()); // 끝 페이지
		
		// model.addAttribute("postList", postList);
		model.addAttribute("commentList", commentList);
		model.addAttribute("communityList", communityList);
		model.addAttribute("posts", commentList);
		
	    model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

	}

//	// 마이페이지 내가 쓴 게시글 검색
//	@GetMapping("/search")
//	public String search(CommunitySearchDto dto, Model model, @PageableDefault(page=0, size=10, sort="communityId", direction=Sort.Direction.DESC) Pageable pageable) {
//		log.info("search(dto={})", dto);
//
//		Page<Community> list = mypageCommunityService.search(dto, pageable);
//		 int nowPage = list.getPageable().getPageNumber() + 1; // 현재페이지
//	     int startPage =  Math.max(nowPage - 4, 1); // 시작 페이지
//	     int endPage = Math.min(nowPage +5, list.getTotalPages()); // 끝 페이지
//		
//		model.addAttribute("posts", list);
//	    model.addAttribute("nowPage",nowPage);
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//
//		return "mypage/community/my_posts";
//	}

	
//	// 마이페이지 내가 쓴 댓글 검색
//	@GetMapping("/searchcomment")
//	public String searchcomment(CommunitySearchDto dto, Model model) {
//		log.info("searchcomment(dto={})", dto);
//
//		Map<Long, Community> communityList = new HashMap<>();
//		// userId로 사용자의 댓글 list 가져옴
//		
//		List<CommunityComment> commentList = mypageCommunityService.readByUserId(dto.getUserId(), dto);
//		// list 반복 -> 댓글 id(key), 댓글의 게시글(Type: Community -> 게시글 id)를 가져와 Map에 저장
//		// 댓글 키로 해당 게시글(value)을 찾음.
//		for (CommunityComment comment : commentList) {
//			communityList.put(comment.getCommunityCommentId(),
//					mypageCommunityService.readByCommunityId(comment.getCommunityId().getCommunityId()));
//		}
//
//		model.addAttribute("commentList", commentList);
//		model.addAttribute("communityList", communityList);
//
//		return "mypage/community/my_comments";
//	}

}
