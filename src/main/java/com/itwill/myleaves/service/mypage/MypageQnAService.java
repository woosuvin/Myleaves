package com.itwill.myleaves.service.mypage;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.myleaves.dto.qna.QnAUpdateDto;
import com.itwill.myleaves.repository.qna.QnA;
import com.itwill.myleaves.repository.qna.QnARepository;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MypageQnAService {
	
	private final QnARepository qnaRepository;
	
	// 내가 쓴 QnA 리스트 가져오기
	@Transactional(readOnly = true)
	public List<QnA> allread(String userId) {
		log.info("read()");
		
		return qnaRepository.findAllByUserIdOrderByQidDesc(userId);
	}
	
	// 내가 쓴 QnA 상세보기 
	@Transactional(readOnly = true)
	public QnA readMyDetail(String userId , Long qid) {
		log.info("readMyDetail(userId={}, qid={})",userId , qid);
		
		return qnaRepository.findById(qid).orElseThrow();
	}
	
	// 내가 쓴 QnA 수정하기
	@Transactional
    public void updateMyQna(QnAUpdateDto dto) {
        log.info("updateMyQna(dto={})" , dto);
        
        QnA entity = qnaRepository.findById(dto.getQid()).orElseThrow();
        
        entity.update(dto); 
        
    }
	
	// 내가 쓴 QnA 삭제하기
	public void deleteMyQna(Long qid) {
	       log.info("deleteMyQna(qid={})" , qid);
	       qnaRepository.deleteById(qid);   
	    }
	
	
	
	// 리스트 에서 내가 쓴 QnA 여러개 삭제
	public void deleteMyQnaAll(List<Long> qid) {
		for (Long qids : qid) {
			qnaRepository.deleteById(qids);
		}
	}
	
}
