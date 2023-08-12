package com.itwill.myleaves.service.sellbuy;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.itwill.myleaves.dto.sellbuy.SellCreateDto;
import com.itwill.myleaves.dto.sellbuy.SellUpdateDto;
import com.itwill.myleaves.dto.wish.WishSellCreateDto;
import com.itwill.myleaves.repository.sellbuy.Buy;
import com.itwill.myleaves.repository.sellbuy.BuyRepository;
import com.itwill.myleaves.repository.sellbuy.BuyWish;
import com.itwill.myleaves.repository.sellbuy.BuyWishRepository;
import com.itwill.myleaves.repository.sellbuy.Sell;
import com.itwill.myleaves.repository.sellbuy.SellRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class SellService {
	
	private final SellRepository sellRepository;
	private final BuyRepository buyRepository;
	private final BuyWishRepository buyWishRepository;
	
	/**
	 * 관심상품 삭제
	 * @param userId
	 * @param sellId
	 */
	public void delete(String userId, long sellId) {
//		log.info("delete(userId={}, sellId={})", userId, sellId);
		buyWishRepository.deleteByUserIdAndSellId(userId, sellId);
	}
	
	/**
	 * 관심삼품 추가
	 * @param dto
	 * @return
	 */
	public BuyWish create(WishSellCreateDto dto) {
//		log.info("create(dto={})", dto);
		BuyWish entity = BuyWish.builder()
					.userId(dto.getUserId())
					.sellId(dto.getSellId())
					.build();
		
		buyWishRepository.saveAndFlush(entity);
//		log.info("entity={}", entity);
		return entity;
	}
	
	/**
	 * 마이페이지 분양 리스트
	 * @param userId
	 * @return
	 */
	public List<Sell> readSellList(String userId) {
		return sellRepository.findByUserId(userId);
	}

	/**
	 * 마이페이지 입양 리스트
	 * @param buyerId
	 * @return
	 */
	public List<Buy> readBuyList(String buyerId) {
		return buyRepository.findByBuyerId(buyerId);
	}

	/**
	 * 입양 메뉴 리스트
	 * @return
	 */
	public List<Sell> read() {
//		log.info("read()");
		return sellRepository.findByOrderBySoldAscSellIdDesc();
	}
	
	/**
	 * 입양 메뉴 리스트 페이징 
	 * @param pageable
	 * @return
	 */
	public Page<Sell> readWPaging(Pageable pageable){
		return sellRepository.findByOrderBySoldAscSellIdDesc(pageable);
	}
	

	/**
	 * 입양 메뉴 상세보기 
	 * @param sellId
	 * @return
	 */
	public Sell read(long sellId) {
		return sellRepository.findById(sellId).orElseThrow();
	}

	/**
	 * 분양 메뉴
	 * @param dto
	 * @return
	 */
	public Sell create(SellCreateDto dto) {
//		log.info("create(dto={})", dto);

		Sell entity = dto.toEntity();
//		log.info("entity = {}", entity);

		sellRepository.save(entity);
//		log.info("entity = {}", entity);

		return entity;
	}

	/**
	 * 분양 수정
	 * @param dto
	 * @throws IOException
	 */
	@Transactional
	public void update(SellUpdateDto dto) throws IOException {
//		log.info("update({})", dto);

		Sell entity = sellRepository.findById(dto.getSellId()).orElseThrow();
		entity.update(dto);
	}

	/**
	 * 분양 삭제
	 * @param sellId
	 */
	public void delete(long sellId) {
//		log.info("delete(sellId={})", sellId);
		sellRepository.deleteById(sellId);

	}
}
