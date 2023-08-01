package com.itwill.myleaves.web.mngr;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwill.myleaves.dto.notice.NoticeCreateDto;
import com.itwill.myleaves.repository.notice.Notice;
import com.itwill.myleaves.repository.notice.NoticePaging;
import com.itwill.myleaves.service.notice.MngrNoticeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/mngr/notice")
public class MngrNoticeController {
	
	private final MngrNoticeService mngrNoticeService;
	
	/**
	 * 관리자 공지사항 list
	 * @param model
	 * @return
	 */
	@GetMapping("/list")
	public String list(Model model, NoticePaging paging) {
		log.info("mngr notice list()");
		
		if(paging == null) {
			paging = new NoticePaging();
		}
		
		Map<String, Object> result = mngrNoticeService.read(paging);
		model.addAttribute("fixList", result.get("yFixList"));
		model.addAttribute("notFixList", result.get("nFixList"));
		model.addAttribute("paging", result.get("paging"));
		
		return "mngr/info/notice/list";
	}
	
	/**
	 * 관리자 공지사항 detail, modify
	 * @param model
	 * @return
	 */
	 @GetMapping({ "/detail", "/modify" })
    public void read(Long nid, Model model) {
        log.info("read(nid={})", nid);
        
        Notice notice = mngrNoticeService.read(nid);
        model.addAttribute("notice", notice);
    }
	
	/**
	 * 관리자 공지사항 write
	 * @param model
	 * @return
	 */
	@GetMapping("/write")
	public String write(Model model) {
		log.info("mngr notice write()");
		
		return "/mngr/info/notice/write";
	}
	
	/**
	 * 관리자 공지사항 create
	 * @param data
	 * @return
	 */
	@PostMapping("/write")
	@ResponseBody
	public void create(@RequestBody NoticeCreateDto data) {
		log.info("mngr notice create(data = {})", data);
		
		mngrNoticeService.create(data);
	}
	
}
