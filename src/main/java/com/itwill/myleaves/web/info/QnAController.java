package com.itwill.myleaves.web.info;

import java.util.List;

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

import com.itwill.myleaves.dto.qna.QnACreateDto;
import com.itwill.myleaves.dto.qna.QnASearchAndPageDto;
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
	/*
	 * @PreAuthorize("hasRole('MEMBER')")
	 * 
	 * @GetMapping public String readQnA(Model model , @PageableDefault(page=0,
	 * size=5, sort="qid", direction=Sort.Direction.DESC) Pageable pageable) {
	 * log.info("QNA read()");
	 * 
	 * Page<QnA> list = qnaService.read(pageable);
	 * 
	 * int nowPage = list.getPageable().getPageNumber() + 1; // 현재페이지 int startPage
	 * = Math.max(nowPage - 4, 1); // 시작 페이지 int endPage = Math.min(nowPage + 5,
	 * list.getTotalPages()); // 끝 페이지
	 * 
	 * model.addAttribute("qnas" , list); model.addAttribute("nowPage",nowPage);
	 * model.addAttribute("startPage", startPage); model.addAttribute("endPage",
	 * endPage);
	 * 
	 * return "info/qna/read"; }
	 */
	@GetMapping
	public String qnaList(Model model,
			@PageableDefault(page = 0, size = 3, sort = "qid", direction = Sort.Direction.DESC) Pageable pageable) {
		log.info("QnA My list()");

		Page<QnA> list = qnaService.read(pageable);

	    int nowPage = list.getPageable().getPageNumber() + 1; // 현재페이지
	    int maxPage = list.getTotalPages();

	    int visiblePageCount = 5; // 한 번에 표시될 페이지 번호의 갯수
	    int halfVisiblePageCount = visiblePageCount / 2;

	    int startPage, endPage;

	    if (nowPage <= halfVisiblePageCount) {
	        startPage = 1;
	        endPage = Math.min(visiblePageCount, maxPage);
	    } else if (nowPage >= maxPage - halfVisiblePageCount) {
	        startPage = Math.max(maxPage - visiblePageCount + 1, 1);
	        endPage = maxPage;
	    } else {
	        startPage = nowPage - halfVisiblePageCount;
	        endPage = nowPage + halfVisiblePageCount;
	    }

	    model.addAttribute("qnas", list);
	    model.addAttribute("nowPage", nowPage);
	    model.addAttribute("startPage", startPage);
	    model.addAttribute("endPage", endPage);

	    return "info/qna/read";
	}

	/*
	 * QnA 검색 기능
	 * 
	 * @PreAuthorize("hasRole('MEMBER')")
	 * 
	 * @GetMapping("/search") public String searchQnA(QnASearchAndPageDto dto, Model
	 * model, @PageableDefault(page=0, size=10, sort="qid",
	 * direction=Sort.Direction.DESC) Pageable pageable) { log.info("search(dto={})"
	 * , dto);
	 * 
	 * Page<QnA> list = qnaService.searchQnA(dto , pageable);
	 * 
	 * int nowPage = list.getPageable().getPageNumber() + 1; // 현재페이지 int maxPage =
	 * list.getTotalPages();
	 * 
	 * int visiblePageCount = 5; // 한 번에 표시될 페이지 번호의 갯수 int halfVisiblePageCount =
	 * visiblePageCount / 2;
	 * 
	 * int startPage = Math.max(nowPage - halfVisiblePageCount, 1); int endPage =
	 * Math.min(startPage + visiblePageCount - 1, maxPage);
	 * 
	 * model.addAttribute("qnas", list); model.addAttribute("nowPage", nowPage);
	 * model.addAttribute("startPage", startPage); model.addAttribute("endPage",
	 * endPage);
	 * 
	 * return "info/qna/read"; }
	 */

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
		log.info("QnA create(dto={}) POST", dto);

		qnaService.create(dto);

		return "redirect:/info/qna";
	}

	/*
	 * QnA 상세 페이지 QnA 수정 페이지
	 */
	@PreAuthorize("hasRole('MEMBER')")
	@GetMapping({ "/detail", "/modify" })
	public void detailQnA(Long qid, Model model) {
		log.info("QnA read(id={})", qid);

		QnA qna = qnaService.read(qid);

		model.addAttribute("qna", qna);
	}

	/*
	 * QnA 수정 보내기
	 */
	@PreAuthorize("hasRole('MEMBER')")
	@PostMapping("/update")
	public String update(QnAUpdateDto dto) {
		log.info("update dto={}", dto);

		qnaService.update(dto);

		return "redirect:/info/qna/detail?qid=" + dto.getQid();
	}

	/*
	 * QnA 삭제 페이지
	 */
	@PreAuthorize("hasRole('MEMBER')")
	@PostMapping("/delete")
	public String delete(long qid) {
		log.info("delete(id={})", qid);

		qnaService.delete(qid);

		return "redirect:/info/qna";
	}

}
