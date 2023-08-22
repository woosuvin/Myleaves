package com.itwill.myleaves.web.sellbuy;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.myleaves.dto.sellbuy.BuyCreateDto;
import com.itwill.myleaves.repository.sellbuy.Buy;
import com.itwill.myleaves.service.sellbuy.BuyService;
import com.itwill.myleaves.service.sellbuy.SellService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SellBuyRestController {
	
	private final BuyService buyService;
	private final SellService sellService;
	
	/**
	 * sold: 분양 완료일 때
	 * @param dto
	 * @return
	 */
	@PostMapping("/sellbuy/sold")
	public ResponseEntity<String> create(@RequestBody BuyCreateDto dto) {
		//log.info("dto={}", dto);
		buyService.create(dto);
		sellService.update(dto.getSellId(), dto.getSold());
		return ResponseEntity.ok("success");
	}
	
	/**
	 * 거래중, 분양중으로 변경
	 * @param sellId
	 * @param sold
	 * @return
	 */
	@PutMapping("/sellbuy/update/{sellId}/{sold}")
	public ResponseEntity<String> update(@PathVariable Long sellId, @PathVariable Long sold) {
		sellService.update(sellId, sold);
		
		return ResponseEntity.ok("success");
	}
	
}
