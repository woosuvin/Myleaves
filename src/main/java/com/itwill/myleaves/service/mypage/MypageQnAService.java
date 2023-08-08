package com.itwill.myleaves.service.mypage;

import org.springframework.stereotype.Service;

import com.itwill.myleaves.repository.qna.QnARepository;
import com.itwill.myleaves.repository.sellbuy.BuyWishRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MypageQnAService {
	private final QnARepository qnaRepository;
}
