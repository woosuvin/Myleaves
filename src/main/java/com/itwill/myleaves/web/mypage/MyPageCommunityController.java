package com.itwill.myleaves.web.mypage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public void read(Model model, @RequestParam("userId") String userId) {
		log.info("my_posts Controller read()");

		List<Community> list = mypageCommunityService.read(userId);

		model.addAttribute("posts", list);

	}

	@GetMapping("/my_comments")
	public void readPostsAndComments(Model model, @RequestParam("userId") String userId) {
		log.info("my_comments Controller read()");

//	    List<Community> postList = mypageCommunityService.read(userId);

		Map<Long, Community> communityList = new HashMap<>();
		// userId로 사용자의 댓글 list 가져옴
		List<CommunityComment> commentList = mypageCommunityService.readComments(userId);
		// list 반복 -> 댓글 id(key), 댓글의 게시글(Type: Community -> 게시글 id)를 가져와 Map에 저장
		// 댓글 키로 해당 게시글(value)을 찾음.
		for (CommunityComment comment : commentList) {
			communityList.put(comment.getCommunityCommentId(),
					mypageCommunityService.readByCommunityId(comment.getCommunityId().getCommunityId()));
		}

		// model.addAttribute("postList", postList);
		model.addAttribute("commentList", commentList);
		model.addAttribute("communityList", communityList);

	}

	// 마이페이지 내가 쓴 게시글 검색
	@GetMapping("/search")
	public String search(CommunitySearchDto dto, Model model) {
		log.info("search(dto={})", dto);

		List<Community> list = mypageCommunityService.search(dto);

		model.addAttribute("posts", list);

		return "mypage/community/my_posts";
	}

	
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
