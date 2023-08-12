package com.itwill.myleaves.web.mngr;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.myleaves.dto.order.TotalOrderUpdateDto;
import com.itwill.myleaves.dto.order.TotalOrderdSearchDto;
import com.itwill.myleaves.repository.orderDetail.OrderDetail;
import com.itwill.myleaves.repository.store.Store;
import com.itwill.myleaves.repository.totalOrder.TotalOrder;
import com.itwill.myleaves.service.store.StoreService;
import com.itwill.myleaves.service.totalOrder.TotalOrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mngr/order")
public class MngrOrderController {

	private final TotalOrderService totalOrderService;
	private final StoreService storeService;
	
	/**
	 * 주문 관리 페이지 리스트
	 * 지현 페이징 추가 8/12
	 * @param model
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/list")
	public void mngrHome(Model model, @PageableDefault(page = 0, size = 10) Pageable pageable) {
//		log.info("mngr order");
		Page<TotalOrder> list = totalOrderService.read(pageable);
		int totalPage = list.getTotalPages()-1;
		int nowPage = list.getPageable().getPageNumber()+1; //지금 페이지 0 + 1 => 1 페이지부터 시작
		int startPage = Math.max(nowPage-4, 1);
		int endPage = Math.min(nowPage+5, list.getTotalPages());
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("totalOrders", list);
	}
	
	/**
	 * 수빈
	 * 주문 관리 -> 주문 상세 보기
	 * @param orderId
	 * @param model
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/detail")
	public void orderDetail(Long orderId, Model model) {
		TotalOrder order = totalOrderService.read(orderId);
		
		// itemId로 찾아줄것
		Map<Long ,Store> storeList = new HashMap<>();
		List<OrderDetail> detailList = totalOrderService.readOrderDetails(order.getOrderId());
		
		Map<String, Long> prices = new HashMap<>();
		
		// 총 결제 금액
		Long totalDetail = (long) 0;
		for(OrderDetail details: detailList) {
			totalDetail += details.getPrice();
			storeList.put(details.getItemId(), storeService.read(details.getItemId()));
		}
		prices.put("totalDetail", totalDetail);
		
		// 배송비
		Long deliveryPrice = order.getPrice() - totalDetail;
		prices.put("deliveryPrice", deliveryPrice);
		
		model.addAttribute("prices", prices);
		model.addAttribute("order", order);
		model.addAttribute("details", detailList);
		model.addAttribute("items", storeList);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/search")
	public String search(TotalOrderdSearchDto dto, Model model, @PageableDefault(page = 0, size = 10) Pageable pageable) throws ParseException {
//		log.info("search(dto={})",dto);
		
		Page<TotalOrder> list = totalOrderService.search(dto, pageable);
		
		int totalPage = list.getTotalPages()-1;
		int nowPage = list.getPageable().getPageNumber()+1; //지금 페이지 0 + 1 => 1 페이지부터 시작
		int startPage = Math.max(nowPage-4, 1);
		int endPage = Math.min(nowPage+5, list.getTotalPages());
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		model.addAttribute("totalOrders", list);		
		return "/mngr/order/list";
	}
	
}
