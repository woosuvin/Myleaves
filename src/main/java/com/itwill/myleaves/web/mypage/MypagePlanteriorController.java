package com.itwill.myleaves.web.mypage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.myleaves.dto.planterior.PlanteriorUpdateDto;
import com.itwill.myleaves.dto.planterior.TotalUpdateDto;
import com.itwill.myleaves.repository.planterior.Bookmark;
import com.itwill.myleaves.repository.planterior.BookmarkRepository;
import com.itwill.myleaves.repository.planterior.Planterior;
import com.itwill.myleaves.repository.planterior.PlanteriorCategory;
import com.itwill.myleaves.repository.planterior.PlanteriorRepository;
import com.itwill.myleaves.service.palnterior.BookmarkService;
import com.itwill.myleaves.service.palnterior.CategoryService;
import com.itwill.myleaves.service.palnterior.MypageService;
import com.itwill.myleaves.service.palnterior.PlanteriorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/planterior")
public class MypagePlanteriorController {
	
	private final MypageService mypageService;
	private final BookmarkService bookmarkService;
	private final CategoryService categoryService;
	private final PlanteriorService planteriorSerivce;
	
	// 내가 쓴 글 읽기
	@PreAuthorize("hasRole('MEMBER')")
	@GetMapping("/my_posts")
	public String planteriorMine(Model model, String userId, @PageableDefault(page = 0, size = 8) Pageable pageable ) {
		//log.info("planteriorMine(userId = {})", userId);
		
		Page<Planterior> list = mypageService.read(userId, pageable);
		model.addAttribute("cardList", list);
		
		Map<Long, String> thumbnails = new HashMap<>();
		for(Planterior p : list) {
			log.info("{}", p.getThumbnail());
			thumbnails.put(p.getPlanteriorId(), Base64.getEncoder().encodeToString(p.getThumbnail()));
		}
		model.addAttribute("images", thumbnails);

		// 페이징 설정
		int totalPage = list.getTotalPages()-1;
		int nowPage = list.getPageable().getPageNumber()+1; //지금 페이지 0 + 1 => 1 페이지부터 시작
		int startPage = Math.max(nowPage-4, 1);
		int endPage = Math.min(nowPage+5, list.getTotalPages());
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "mypage/planterior/planteriorMyposts";
	}
	
	// 내가 쓴 글 수정
	@PreAuthorize("hasRole('MEMBER')")
	@GetMapping("/updateDelete")
	public void planteriorUpdate(Model model, Long planteriorId) {
		log.info("planteriorUpdate(planteriorId={})", planteriorId);
		
		Planterior list = mypageService.read(planteriorId);
		PlanteriorCategory plist = categoryService.read(planteriorId);
		String image = Base64.getEncoder().encodeToString(list.getThumbnail());
		 
		Map<String, String> conditionContentMap = new HashMap<>();
		String[] result = plist.getConditionContent().split(",");
        for(int i = 0; i < result.length; i++) {
        	conditionContentMap.put(result[i], result[i]);
        }
        
        model.addAttribute("conditionContentMap", conditionContentMap);
		model.addAttribute("category", plist);
		model.addAttribute("image", image);
		model.addAttribute("cardList", list);
	}
	
	@PreAuthorize("hasRole('MEMBER')")
	@PostMapping("/update")
	public String update(TotalUpdateDto dto) throws IOException {
		log.info("update(dto ={})", dto);
		
		categoryService.update(dto.planteriorCategoryUpdateDto());
		mypageService.update(dto.planteriorUpdateDto());
		
		return "redirect:/mypage/planterior/my_posts?userId=" + dto.getUserId();
	}
	
	@PreAuthorize("hasRole('MEMBER')")
	@PostMapping("delete")
	public String delete(long planteriorId, String userId) {
		log.info("delete(planteriorId = {})", planteriorId);
		
		mypageService.delete(planteriorId);
		bookmarkService.delete(planteriorId);
		return "redirect:/mypage/planterior/my_posts?userId=" + userId;
	}
	
	// 북마크
	@PreAuthorize("hasRole('MEMBER')")
	@GetMapping("bookmark")
	public void bookmarkRead(Model model, String userId, @PageableDefault(page = 0, size = 8) Pageable pageable) {
		log.info("bookmarkRead");
		
		// 북마크 가져오기
		Page<Bookmark> list = mypageService.bookmarkRead(userId, pageable);
		List<Planterior> result = new ArrayList<>();
		for(Bookmark b: list) {
			Planterior planterior = planteriorSerivce.read(b.getPlanteriorId());
			log.info("확인:{}", planterior.getPlanteriorId());
			result.add(planterior);
		}
		
		Map<Long, String> thumbnails = new HashMap<>();
		for(Planterior planterior : result) {
			if (planterior != null) {
		        thumbnails.put(planterior.getPlanteriorId(), Base64.getEncoder().encodeToString(planterior.getThumbnail()));
		    } else {
		        log.warn("Encountered a null Planterior object.");
		    }
		}
		
		model.addAttribute("images", thumbnails);
		model.addAttribute("cardList", result);
		
		int totalPage = list.getTotalPages()-1;
		int nowPage = list.getPageable().getPageNumber()+1; //지금 페이지 0 + 1 => 1 페이지부터 시작
		int startPage = Math.max(nowPage-4, 1);
		int endPage = Math.min(nowPage+5, list.getTotalPages());
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
	}
	
	
}
