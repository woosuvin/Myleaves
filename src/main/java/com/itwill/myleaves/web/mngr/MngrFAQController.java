package com.itwill.myleaves.web.mngr;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.myleaves.dto.faq.FaQCreateDto;
import com.itwill.myleaves.dto.faq.FaQUpdateDto;
import com.itwill.myleaves.repository.faq.FaQ;
import com.itwill.myleaves.service.faq.FaQService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/mngr/faq")
public class MngrFAQController {
	
	private final FaQService faqService;
	
	/*
	 * FAQ 리스트 보기
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/list")
	public void mngrListFaq(Model model) {
		
		log.info("mngrListfaq()");
		
		List<FaQ> list = faqService.read();
		
		model.addAttribute("faqs" , list);
		
	}
	
	/*
	 * FAQ 글 작성하기
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/create")
    public void create() {
        log.info("create() GET");
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	 @PostMapping("/create") 
	 public String create(FaQCreateDto dto) {
		 log.info("FaQ create(dto={}) POST" , dto); 
	 	faqService.create(dto);
	  
	 	return "redirect:/mngr/faq/list"; 
}
	
	/*
	 * FAQ 상세보기 페이지
	 */
	@GetMapping("/detail")
	public void detailFaq(Long fqid , Model model) {
        log.info("detailFaq(id={})" , fqid);
        
        FaQ faq = faqService.read(fqid);
        model.addAttribute("faq" , faq);
	}  
	
	/*
	 * FaQ 수정 페이지
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/update")
	public String updateFaq(FaQUpdateDto dto) {
		log.info("updatefaq dto={}" , dto);
		
		faqService.update(dto);
		
		return "redirect:/mngr/faq/list";
	}
	
	/*
	 * FaQ 삭제 페이지
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/delete")
	public String deleteFaq(long fqid) {
		log.info("deleteFaQ(fqid={})" , fqid);
		
		faqService.delete(fqid);
		
		return "redirect:/mngr/faq/list";
	}
	
}
