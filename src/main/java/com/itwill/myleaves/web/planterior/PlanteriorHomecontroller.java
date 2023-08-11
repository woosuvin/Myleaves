package com.itwill.myleaves.web.planterior;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.myleaves.dto.planterior.TotalCreateDto;
import com.itwill.myleaves.dto.planterior.PlanteriorCategoryCreateDto;
import com.itwill.myleaves.dto.planterior.PlanteriorCreateDto;
import com.itwill.myleaves.repository.member.Member;
import com.itwill.myleaves.repository.planterior.Bookmark;
import com.itwill.myleaves.repository.planterior.Planterior;
import com.itwill.myleaves.repository.planterior.PlanteriorCategory;
import com.itwill.myleaves.repository.planterior.PlanteriorRepository;
import com.itwill.myleaves.service.palnterior.BookmarkService;
import com.itwill.myleaves.service.palnterior.CategoryService;
import com.itwill.myleaves.service.palnterior.PlanteriorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/planterior")
public class PlanteriorHomecontroller {
	
	// 의존성 주입!!!!
	private final PlanteriorService planteriorService;
	private final BookmarkService bookmarkService;
	private final CategoryService categoryService;
	
	@GetMapping
	// catertgorydto-> param
	public String planterior(Model model, Authentication auth ) {
		log.info("planterior");
		
		Slice<Planterior> list = planteriorService.read(0,20);
		List<Bookmark> bookList = bookmarkService.read();
		// count가져오기
		
		// 최종
		Map<Long, Long> bookmarkMap = new HashMap<>();
		
		if (auth != null && auth.isAuthenticated()) {
			
			// 아이디 비교
			// 현재 로그인한 사용자 정보 가져오기
			Member loggedInUser = (Member) auth.getPrincipal();
			for(Bookmark p : bookList) {
				if(p.getUserId().equals(loggedInUser.getUserId())) {
					bookmarkMap.put(p.getPlanteriorId(), p.getPlanteriorId());
				}
			}
		  }
		
		
		model.addAttribute("cardList", list);
		model.addAttribute("bookmark", bookmarkMap);
		
		return "planterior/home";
	}
	
	// 검색
	@GetMapping("search")
	public String filterRead(Model model, PlanteriorCategoryCreateDto dto, Authentication auth) {
		log.info("filterRead(dto={})",dto);
		
		if(dto.getConditionContent().isEmpty()) {
			List<PlanteriorCategory> stateList = categoryService.findState(dto.getStateContent());
			List<Planterior> list = planteriorService.read();
			List<Bookmark> bookList = bookmarkService.read();
			
			// (1) 검색에 해당하는 플래테리어 카드 가져오기.
			List<Planterior> preResult = new ArrayList<>();
			
			for(int i = 0; i < list.size(); i++) {
				for(int j = 0; j < stateList.size(); j++) {
					if(list.get(i).getPlanteriorId() == stateList.get(j).getPlanteriorId()) {
						preResult.add(list.get(i));
					}
				}
			}
			log.info("검색 플랜테리어 사이즈= {}", preResult.size());
			
			// (2) 북마크 가져오기
			Map<Long, Long> bookmarkMap = new HashMap<>();
			
			if (auth != null && auth.isAuthenticated()) {
				
				// 아이디 비교
				// 현재 로그인한 사용자 정보 가져오기
				Member loggedInUser = (Member) auth.getPrincipal();
				for(Bookmark p : bookList) {
					if(p.getUserId().equals(loggedInUser.getUserId())) {
						bookmarkMap.put(p.getPlanteriorId(), p.getPlanteriorId());
					}
				}
			  }
			
			model.addAttribute("cardList", preResult);
			model.addAttribute("bookmark", bookmarkMap);
			model.addAttribute("count", preResult.size());
			return "planterior/home";
			
		} else {
			List<PlanteriorCategory> stateList = categoryService.findStateAndCondition(dto.getStateContent(), dto.getConditionContent());
			List<Planterior> list = planteriorService.read();
			List<Bookmark> bookList = bookmarkService.read();
			
			// (1) 검색에 해당하는 플래테리어 카드 가져오기.
			List<Planterior> preResult = new ArrayList<>();
			
			for(int i = 0; i < list.size(); i++) {
				for(int j = 0; j < stateList.size(); j++) {
					if(list.get(i).getPlanteriorId() == stateList.get(j).getPlanteriorId()) {
						preResult.add(list.get(i));
					}
				}
			}
			log.info("검색 플랜테리어 사이즈= {}", preResult.size());
			model.addAttribute("cardList", preResult);
			
			// (2) 북마크 가져오기
			Map<Long, Long> bookmarkMap = new HashMap<>();
			
			if (auth != null && auth.isAuthenticated()) {
				
				// 아이디 비교
				// 현재 로그인한 사용자 정보 가져오기
				Member loggedInUser = (Member) auth.getPrincipal();
				for(Bookmark p : bookList) {
					if(p.getUserId().equals(loggedInUser.getUserId())) {
						bookmarkMap.put(p.getPlanteriorId(), p.getPlanteriorId());
					}
				}
			  }
			model.addAttribute("bookmark", bookmarkMap);
			model.addAttribute("count", preResult.size());
			return "planterior/home";
		}
		
	}
	
	
	
	
	@GetMapping("/create")
	public void create() {
		log.info("planteriorCreate");
		
	}
	
	@PostMapping("/create")
	public String create(TotalCreateDto dto) {
		log.info("create(dto ={}) post", dto);
		
		// form에서 가져온 data DB insert
		Planterior entity = planteriorService.create(dto.planteriorCreateDto());
		log.info("확인 = {}", entity.getPlanteriorId());
		
		categoryService.create(dto.planteriorCategoryCreateDto(entity.getPlanteriorId()));
		
		return "redirect:/planterior";
		
	}

}
