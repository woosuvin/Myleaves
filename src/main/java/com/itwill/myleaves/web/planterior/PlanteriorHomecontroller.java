package com.itwill.myleaves.web.planterior;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.myleaves.dto.planterior.PlanteriorCreateDto;
import com.itwill.myleaves.repository.member.Member;
import com.itwill.myleaves.repository.planterior.Bookmark;
import com.itwill.myleaves.repository.planterior.Planterior;
import com.itwill.myleaves.repository.planterior.PlanteriorRepository;
import com.itwill.myleaves.service.palnterior.BookmarkService;
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
	
	@GetMapping
	public String planterior(Model model, Authentication auth ) {
		log.info("planterior");
		
		List<Planterior> list = planteriorService.read();
		List<Bookmark> bookList = bookmarkService.read();
		
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
	
	@GetMapping("/create")
	public void create() {
		log.info("planteriorCreate");
		
	}
	
	@PostMapping("/create")
	public String create(PlanteriorCreateDto dto) {
		log.info("create(dto ={}) post", dto);
		
		// form에서 가져온 data DB insert
		planteriorService.create(dto);
		
		return "redirect:/planterior";
		
	}

}
