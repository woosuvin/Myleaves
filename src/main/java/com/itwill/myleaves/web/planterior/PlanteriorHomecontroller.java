package com.itwill.myleaves.web.planterior;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/planterior")
public class PlanteriorHomecontroller {
	
	@GetMapping
	public String planterior(Model model) {
		log.info("planterior");
		
		return "/planterior/home";
	}
	
	@GetMapping("/create")
	public void create() {
		log.info("planteriorCreate");
		
	}

}
