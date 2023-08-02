package com.itwill.myleaves.service.qna;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.myleaves.dto.qna.QnACreateDto;
import com.itwill.myleaves.dto.qna.QnAUpdateDto;
import com.itwill.myleaves.repository.qna.QnA;
import com.itwill.myleaves.repository.qna.QnARepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class QnAService {
	
	private final QnARepository qnaRepository;
	
	/*
	 * QnA 글 리스트 불러오기
	 */
    @Transactional(readOnly = true)
    public List<QnA> read(){
        log.info("read()");
        
        return qnaRepository.findByOrderByQidDesc();
    }
    /*
     * QnA 글 상세보기
     */
    @Transactional(readOnly = true)
    public QnA read(Long qid) {
        log.info("read(id={})" , qid);
        // 그 리스트들 중 포스트 ID 값을 찾는 값을 리턴
        return qnaRepository.findById(qid).orElseThrow();
    }
	
	/*
	 * QnA 글 새로 작성하기
	 */
	public QnA create(QnACreateDto dto) {
		log.info("QnA(dto={})" , dto);
		
		QnA entity = dto.toEntity();
		log.info("entity={}" , entity);
		
		qnaRepository.save(entity);
		log.info("entity={}" , entity);
		
		return entity;
	}
	/*
	 * QnA 글 수정하기
	 */
	@Transactional // (1)
    public void update(QnAUpdateDto dto) {
        log.info("update(dto={})" , dto);
        
        QnA entity = qnaRepository.findById(dto.getQid()).orElseThrow();
        
        entity.update(dto); 
        
    }
	
}	
