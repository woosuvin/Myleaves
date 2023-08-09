package com.itwill.myleaves.service.notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.myleaves.dto.notice.NoticeCreateDto;
import com.itwill.myleaves.dto.notice.NoticeReadInterface;
import com.itwill.myleaves.dto.notice.NoticeUpdateDto;
import com.itwill.myleaves.repository.notice.Notice;
import com.itwill.myleaves.repository.notice.NoticePaging;
import com.itwill.myleaves.repository.notice.NoticeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MngrNoticeService {
	
	private final NoticeRepository noticeRepository;
	
	// 공지사항 전체 리스트
	@Transactional(readOnly = true)
	public Map<String, Object> read(NoticePaging paging) {
		log.info("read()");
		
		int pageNum = paging.getOffset();
		
		Map<String, Object> map = new HashMap<>();
		List<NoticeReadInterface> fixList = noticeRepository.searchByFixNotice();
		map.put("yFixList", fixList);
		
		if(paging.getOffset() > 1) {
			paging.setRows(10 - fixList.size());
			paging.setOffset((paging.getOffset() - 1) * paging.getRows());
		} else {
			paging.setOffset(0);
			paging.setRows(10 - fixList.size());
		}
		
		List<NoticeReadInterface> nFixList = noticeRepository.searchByNotFixNoticePaging(paging.getOffset(), paging.getRows());
		map.put("nFixList", nFixList);
		
		paging.setOffset(pageNum);
		paging.pagingTotal(noticeRepository.searchByFixNoticeAll().size());
		
		log.info(paging.toString());
		map.put("paging", paging);
		
		
		return map;
	}
	
	// 공지사항 생성
    public Notice create(NoticeCreateDto dto) {
        log.info("create(dto={})", dto);
        
        // DTO를 Entity로 변환:
        Notice entity = dto.toEntity();
        log.info("entity={}", entity);
        
        // DB 테이블에 저장(insert)
        noticeRepository.save(entity);
        log.info("entity={}", entity);
        
        return entity;
    }
    
    // id로 공지사항 하나를 찾는 메서드
    @Transactional(readOnly = true)
    public Notice read(Long id) {
        log.info("read(id={})", id);
        
        return noticeRepository.findById(id).orElseThrow();
    }

    // 공지사항 삭제
	public void delete(Long id) {
		log.info("delete(id = {})", id);
		
		noticeRepository.deleteById(id);
	}

	// 공지사항 업데이트
	@Transactional
	public void update(NoticeUpdateDto dto) {
		log.info("update(dto = {})", dto);
		
		// (1) 메서드에 @Transactional 애너테이션을 설정하고,
		// (2) DB에서 엔터티를 검색하고,
		// (3)검색한 엔터티를 수정하면,
		// 트랜잭션이 끝나는 시점에 DB update가 자동으로 수행됨!
		
		Notice entity = noticeRepository.findById(dto.getNid()).orElseThrow(); // (2)
		entity.update(dto);	// (3)
	}
	
	// 사용자 공지사항 read
	@Transactional
	public int updateView(Long nid) {
		return noticeRepository.updateView(nid);
	}
	
}
