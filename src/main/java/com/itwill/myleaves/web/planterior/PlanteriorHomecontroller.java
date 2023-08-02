package com.itwill.myleaves.web.planterior;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.myleaves.dto.planterior.PlanteriorCreateDto;
import com.itwill.myleaves.repository.Planterior.Planterior;
import com.itwill.myleaves.repository.Planterior.PlanteriorRepository;
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
	
	@GetMapping
	public String planterior(Model model) {
		log.info("planterior");
		
		List<Planterior> list = planteriorService.read();
		
		model.addAttribute("cardList", list);
		
		return "/planterior/home";
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
