package com.itwill.myleaves.web.mngr;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mngr")
public class MngrStoreController {

	@GetMapping("/store/list")
	public void mngrHome(Model model) {
		log.info("mngr store");
		
	}
	
	
}
