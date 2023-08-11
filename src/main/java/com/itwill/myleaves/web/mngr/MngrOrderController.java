package com.itwill.myleaves.web.mngr;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	 * @param model
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/list")
	public void mngrHome(Model model) {
//		log.info("mngr order");
		List<TotalOrder> list = totalOrderService.read();
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
	public String search(TotalOrderdSearchDto dto, Model model) throws ParseException {
//		log.info("search(dto={})",dto);
		
		List<TotalOrder> list = totalOrderService.search(dto);
		model.addAttribute("totalOrders", list);		
		return "/mngr/order/list";
	}
	
}
