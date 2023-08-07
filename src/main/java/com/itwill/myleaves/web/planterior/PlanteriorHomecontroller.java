package com.itwill.myleaves.web.planterior;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.myleaves.dto.planterior.PlanteriorCreateDto;
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
	public String planterior(Model model) {
		log.info("planterior");
		
		List<Planterior> list = planteriorService.read();
		List<Bookmark> bookList = bookmarkService.read();
		
		// 해당 플랜테리어아이디를 북마크한 유저들의 집합
		List<Bookmark> bookmarkList = new ArrayList<>();
		
		for(int i =0; i < list.size(); i++) {
			for(int j = 0; j< bookList.size(); j++) {
				if(list.get(i).getPlanteriorId() == bookList.get(j).getPlanteriorId()) {
					bookmarkList.add(bookList.get(j));
				}
			}
		}
		
		log.info(bookmarkList.toString());
		
		model.addAttribute("cardList", list);
		model.addAttribute("bookmark", bookmarkList);
		
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
