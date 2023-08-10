package com.itwill.myleaves.web.mngr;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.itwill.myleaves.dto.notice.NoticeCreateDto;
import com.itwill.myleaves.dto.notice.NoticeUpdateDto;
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
@RequestMapping("/mngr/notice")
public class MngrNoticeController {
	
	private final MngrNoticeService mngrNoticeService;
	private final UploadFileService uploadFileService;
	
	/**
	 * 관리자 공지사항 list
	 * @param model
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/list")
	public String list(Model model, NoticePaging paging) {
		log.info("mngr notice list()");
		
		if(paging == null) {
			paging = new NoticePaging();
		}
		
		// log.info(paging.toString());
		
		Map<String, Object> result = mngrNoticeService.read(paging);
		model.addAttribute("fixList", result.get("yFixList"));
		model.addAttribute("notFixList", result.get("nFixList"));
		model.addAttribute("paging", result.get("paging"));
		
		return "mngr/info/notice/list";
	}
	
	/**
	 * 관리자 공지사항 detail
	 * @param model
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/detail")
    public String read1(Long nid, Model model) {
        log.info("read(nid={})", nid);
        
        Notice notice = mngrNoticeService.read(nid);
        model.addAttribute("notice", notice);
        
        List<UploadFileReadDto> files = uploadFileService.read(nid);
        model.addAttribute("files", files);
        
        return "mngr/info/notice/detail";
    }
	
	
	/**
	 * 관리자 공지사항 write
	 * @param model
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
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
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/write")
	@ResponseBody
	public Notice create(@RequestBody NoticeCreateDto data) {
		log.info("mngr notice create(data = {})", data);
		
		Notice result = mngrNoticeService.create(data);
		
		return result;
	}
	
	
	/**
	 * 관리자 공지사항 modify
	 * @param model
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/modify")
    public String modify(Long nid, Model model) {
        log.info("modify(nid={})", nid);
        
        Notice notice = mngrNoticeService.read(nid);
        model.addAttribute("notice", notice);
        
        List<UploadFileReadDto> files = uploadFileService.read(nid);
        model.addAttribute("files", files);
        
        return "mngr/info/notice/modify";
    }
	
	/**
	 * 관리자 공지사항 update
	 * @param data
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/modify")
	@ResponseBody
	public void update(@RequestBody NoticeUpdateDto data) {
		log.info("mngr notice update(data = {})", data);
		
		mngrNoticeService.update(data);
	}
	
	/**
	 * 관리자 공지사항 delete
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/delete")
    public String delete(Long nid) {
    	log.info("delete(nid = {})", nid);
    	
    	// postService를 이용해서 DB 테이블에서 포스트를 삭제하는 서비스 호출:
    	mngrNoticeService.delete(nid);
    	
    	return "redirect:/mngr/notice/list";
    }
	
	/**
	 * 공지사항 file upload
	 * @param file
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/upload")
	@ResponseBody
	public int uploadFile(
			@RequestParam("uploadFile") List<MultipartFile> fileList,
			@RequestParam("nid") Long nid) {		
		int order = uploadFileService.fileOrderValue(nid) + 1;
		
		try {
			for(int i = 0; i < fileList.size(); i++){
				 //파일 저장
				 uploadFileService.save(fileList.get(i), nid, order + i);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return order;
		
	}
	
	/**
	 * 파일 다운로드
	 * @param dto
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/download")
	@ResponseBody
	public byte[] downloadFile(@RequestBody DownloadFileReqDto dto) {
		UploadFile result = null;
		result = uploadFileService.readFile(dto.getUfid());
		
		return result.getAtchdFile();
	}
	
	/**
	 * 해당 ufid 파일 삭제
	 * @param dto
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/deleteFile")
	@ResponseBody
	public void deleteFile(@RequestBody DownloadFileReqDto dto, Long nid) {
		uploadFileService.deleteFile(dto.getUfid());
		
	}
	
}
