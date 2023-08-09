package com.itwill.myleaves.web.mypage;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.myleaves.dto.qna.QnAUpdateDto;
import com.itwill.myleaves.repository.qna.QnA;
import com.itwill.myleaves.service.mypage.MypageQnAService;
import com.itwill.myleaves.service.qna.QnAService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/qna")
public class MypageQnAController {
	
	private final MypageQnAService mypageqnaService;
	
		
	/*
	 * 내가 쓴 QnA 리스트 페이지
	 */
	@GetMapping("/qna_list")
	public void qnaList(Model model, String userId) {
		log.info("QnA My list()");
		
		List<QnA> list = mypageqnaService.allread(userId);
		
		model.addAttribute("myQnAlist" , list);
		
	}
	
	/*
	 * 내가 쓴 QnA 상세보기
	 */
	@GetMapping({"/detail","/modify"})
	public void detailMyQnA(Long qid, String userId, Model model) {
		log.info("QnA My Detai;(id={}, userId={})" , qid, userId);
		
		QnA myqna = mypageqnaService.readMyDetail(userId, qid);
		
		model.addAttribute("myqnas" , myqna);
	}
	
	/*
	 * 내가 쓴 QnA 수정하기
	 */
	@PostMapping("/update")
	public String update(QnAUpdateDto dto) {
        log.info("update dto={}" , dto);
        
        mypageqnaService.updateMyQna(dto);
        
        return "redirect:/mypage/qna/detail?userId=" + dto.getUserId() + "&qid=" + dto.getQid();

	}
	/*
	 * 내가 쓴 QnA 삭제하기
	 */
	@PostMapping("/delete")    
    public String delete(long qid , String userId) {
        log.info("delete(id={})" , qid);
   
        mypageqnaService.deleteMyQna(qid);
        
        return "redirect:/mypage/qna/qna_list?userId=" + userId;
    }
	
	/*
	 * 내가 쓴 QnA 여러개 삭제
	 */
	@PostMapping("/alldelete")
	public String deleteAllMyQnA(@RequestParam("selectedQnaIds") List<Long> selectedQnaIds, @RequestParam("userId") String userId) {
		
		mypageqnaService.deleteMyQnaAll(selectedQnaIds);
        
		return "redirect:/mypage/qna/qna_list?userId=" + userId;
	}
	
	
	
}
