package com.itwill.myleaves.service.faq;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.myleaves.dto.faq.FaQCreateDto;
import com.itwill.myleaves.dto.faq.FaQUpdateDto;
import com.itwill.myleaves.repository.faq.FaQ;
import com.itwill.myleaves.repository.faq.FaQRepository;


import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class FaQService {

	private final FaQRepository faqRepository;
	
	/**
	 * 관리자
	 * @return
	 */
	
	/*
	 * FaQ 글 리스트 불러오기 관리자 페이지
	 */
	@Transactional(readOnly = true)
	public List<FaQ> read(){
		//log.info("read()");
		
		return faqRepository.findByOrderByFqidDesc();
	}
	/*
	 * FaQ 글 상세보기 관리자 페이지
	 */
	@Transactional(readOnly = true)
	public FaQ read(Long fqid) {
		//log.info("read(fqid={})" , fqid);
		return faqRepository.findById(fqid).orElseThrow();
	}
	
	/*
	 * FaQ 글 작성하기 관리자 페이지
	 */
	
	@Transactional(readOnly = true)
	public FaQ create(FaQCreateDto dto) {
		//log.info("FaQ(dto={})", dto);
		
		FaQ entity = dto.toEntity();
		//log.info("before Save entity={}", entity);
		
		faqRepository.saveAndFlush(entity);
		//log.info("after Save entity={}", entity);
		
		return entity;
	}
	
	
	/*
	 * FaQ 글 수정하기 관리자 페이지
	 */
	@Transactional
	public void update(FaQUpdateDto dto) {
		//log.info("updatefaq(dto={})" , dto);
		
		FaQ entity = faqRepository.findById(dto.getFqid()).orElseThrow();
		
		entity.update(dto);
	}
	
	/*
	 * FaQ 글 삭제하기 관리자 페이지
	 */

	public void delete(Long fqid) {
	       //log.info("delete{}" , fqid);
	       faqRepository.deleteById(fqid);   
	    }
}
