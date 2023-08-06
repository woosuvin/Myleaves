package com.itwill.myleaves.web.mypage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.weaver.ast.Or;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.myleaves.dto.order.TotalOrderUpdateDto;
import com.itwill.myleaves.repository.order.OrderDetail;
import com.itwill.myleaves.repository.order.TotalOrder;
import com.itwill.myleaves.repository.store.Store;
import com.itwill.myleaves.repository.store.StoreWish;
import com.itwill.myleaves.service.order.TotalOrderService;
import com.itwill.myleaves.service.store.MypageStoreService;
import com.itwill.myleaves.service.store.StoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
// 지현 

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/store")
public class MyPageStoreController {
	private final MypageStoreService myPageStoreService;
	private final TotalOrderService orderService;
	private final StoreService storeService;
	
	@GetMapping("/storeWish")
	public void wishList(StoreWish storeWishList, Model model) {
		log.info("read()");
		
		List<Store> list = myPageStoreService.read(storeWishList);
		
		model.addAttribute("wishStore", list);
	
	}
	
	@GetMapping("/orderList")
	public void orderList(String userId, Model model) {
		log.info("read()");
		List<TotalOrder> list = orderService.read(userId);
		
		model.addAttribute("totalOrders", list);
	}
	
	@GetMapping("/orderDetail")
	public void orderDetail(Long orderId, Model model) {
		TotalOrder order = orderService.read(orderId);
		
		// itemId로 찾아줄것
		Map<Long ,Store> storeList = new HashMap<>();
		List<OrderDetail> detailList = orderService.readOrderDetails(order.getOrderId());
		
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
	
	@PutMapping("/orderDetail/update/{orderId}")
	public ResponseEntity<String> update(@PathVariable long orderId, @RequestBody TotalOrderUpdateDto dto){
		// log.info("update(dto={})", dto);
		log.info("update(orderId={}, dto={})", orderId, dto);
		// orderService.update(dto);
		orderService.update(orderId, dto);
		// return "redirect:/mypage/store/orderDetail";
		return ResponseEntity.ok("Success");
	}
	
}
