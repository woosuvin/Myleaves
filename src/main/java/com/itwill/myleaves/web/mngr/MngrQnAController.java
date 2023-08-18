package com.itwill.myleaves.web.mngr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;


import com.itwill.myleaves.dto.qna.QnAMngrUpdateDto;
import com.itwill.myleaves.repository.qna.QnA;
import com.itwill.myleaves.service.qna.QnAService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/mngr/qna")
public class MngrQnAController {
	
	private final QnAService qnaService;
	
	/*
	 * QnA 리스트 불러오기
	 */
	@GetMapping("/list")
	public void mngrqna(Model model ,@PageableDefault(page=0, size=10, sort="qid", direction=Sort.Direction.DESC) Pageable pageable) {
		log.info("mngrqna list");
		
		Page<QnA> list = qnaService.read(pageable);
		
		
		
		int nowPage = list.getPageable().getPageNumber() + 1; // 현재페이지
        int startPage =  Math.max(nowPage - 4, 1); // 시작 페이지
        int endPage = Math.min(nowPage +5, list.getTotalPages()); // 끝 페이지
		
        
		model.addAttribute("qnas" , list);
		model.addAttribute("nowPage",nowPage);
	    model.addAttribute("startPage", startPage);
	    model.addAttribute("endPage", endPage);
		
	}	
	  /*
	   * QnA 수정 , 상세페이지 
	   */
	  @PreAuthorize("hasRole('ADMIN')")
	  @GetMapping({"/modify", "/detail"}) 
	  public void modifyMngrQna(Long qid, Model model) {
		  log.info("QnA Mngr modify(id={})" , qid);
		  
		  QnA qna = qnaService.read(qid);
		  
		  model.addAttribute("qna" , qna);
	  
	  } 
	  
	  /*
	   * QnA 답변 주기
	   */
	  @PreAuthorize("hasRole('ADMIN')")
	  @PostMapping("/update")
	  public String updateMngrQna(QnAMngrUpdateDto dto) {
		  log.info("QnA Update mngr(dto={})" , dto );
		  
		  qnaService.updateMngr(dto);
		  
		
		  return "redirect:/mngr/qna/list";  
	  }
	 
}
