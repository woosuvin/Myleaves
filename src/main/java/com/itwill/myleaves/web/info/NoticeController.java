package com.itwill.myleaves.web.info;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwill.myleaves.dto.uploadFile.DownloadFileReqDto;
import com.itwill.myleaves.dto.uploadFile.UploadFileReadDto;
import com.itwill.myleaves.repository.notice.Notice;
import com.itwill.myleaves.repository.notice.NoticePaging;
import com.itwill.myleaves.repository.uploadfile.UploadFile;
import com.itwill.myleaves.service.notice.MngrNoticeService;
import com.itwill.myleaves.service.uploadfile.UploadFileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/info/notice")
public class NoticeController {
	
	private final MngrNoticeService mngrNoticeService;
	private final UploadFileService uploadFileService;
	
	/**
	 * 사용자 공지사항 list
	 * @param model
	 * @return
	 */
	@GetMapping({"", "/"})
	public String list(Model model, NoticePaging paging) {
		log.info("notice list()");
		
		if(paging == null) {
			paging = new NoticePaging();
		}
		
		Map<String, Object> result = mngrNoticeService.read(paging);
		model.addAttribute("fixList", result.get("yFixList"));
		model.addAttribute("notFixList", result.get("nFixList"));
		model.addAttribute("paging", result.get("paging"));
		
		return "/info/notice/list";
	}
	
	/**
	 * 사용자 공지사항 detail
	 * @param model
	 * @return
	 */
	@GetMapping("/detail")
	public void detail(Long nid, Model model) {
		log.info("notice detail()");

		mngrNoticeService.updateView(nid);
		Notice notice = mngrNoticeService.read(nid);
        model.addAttribute("notice", notice);
        
        List<UploadFileReadDto> files = uploadFileService.read(nid);
        model.addAttribute("files", files);
	}
	
	/**
	 * 파일 다운로드
	 * @param dto
	 * @return
	 */
//	@PostMapping("/download")
//	@ResponseBody
//	public UploadFile downloadFile(@RequestBody DownloadFileReqDto dto) {
//		UploadFile result = null;
//		
//		result = uploadFileService.readFile(dto.getUfid());
//		
//		return result;
//	}
	
	@PostMapping("/download")
	@ResponseBody
	public byte[] downloadFile(@RequestBody DownloadFileReqDto dto) {
		UploadFile result = null;
		result = uploadFileService.readFile(dto.getUfid());
		
		return result.getAtchdFile();
	}


}
