package com.itwill.myleaves.web.info;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.myleaves.dto.qna.QnACreateDto;
import com.itwill.myleaves.dto.qna.QnAUpdateDto;
import com.itwill.myleaves.repository.qna.QnA;
import com.itwill.myleaves.service.qna.QnAService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/info/qna")
public class QnAController {
	
	private final QnAService qnaService;
	
	/*
	 * QnA 리스트 메인 페이지
	 */
	@PreAuthorize("hasRole('MEMBER')")
	@GetMapping
	public String readQnA(Model model) {
		log.info("QNA read()");
		
		 // TOOD : 포스트 목록 검색
        List<QnA> list = qnaService.read();
        
        
        // model 에 검색 결과를 세팅.
        model.addAttribute("qnas" , list);
        
		
		return "info/qna/read";
	}
	
	/*
	 * QnA 새 글 작성하기
	 */
	@PreAuthorize("hasRole('MEMBER')")
	@GetMapping("/create")
    public void create() {
        log.info("QNA create() GET");
        
	}
	@PreAuthorize("hasRole('MEMBER')")
	@PostMapping("/create")
	public String create(QnACreateDto dto) {
		log.info("QnA create(dto={}) POST" , dto);
		
		qnaService.create(dto);
		
		return "redirect:/info/qna";
	}
	
	
	/*
	 * QnA 상세 페이지 QnA 수정 페이지
	 */
	@PreAuthorize("hasRole('MEMBER')")
	@GetMapping({"/detail", "/modify"})
	public void detailQnA(Long qid, Model model) {
		log.info("QnA read(id={})" , qid);
		
		QnA qna = qnaService.read(qid);
		
		model.addAttribute("qna" , qna);
	}
	/*
	 * QnA 수정 보내기
	 */
	@PreAuthorize("hasRole('MEMBER')")
	@PostMapping("/update")
	public String update(QnAUpdateDto dto) {
        log.info("update dto={}" , dto);
        
        qnaService.update(dto);
        
        return "redirect:/info/qna/detail?qid=" + dto.getQid();
	}
	
	/*
	 * QnA 삭제 페이지
	 */
	@PreAuthorize("hasRole('MEMBER')")
	 @PostMapping("/delete")    
	    public String delete(long qid) {
	        log.info("delete(id={})" , qid);
	   
	        qnaService.delete(qid);
	        
	        return "redirect:/info/qna";
	    }

}
