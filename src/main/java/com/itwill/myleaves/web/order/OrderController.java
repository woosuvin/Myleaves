package com.itwill.myleaves.web.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.myleaves.dto.address.AddressCreateDto;
import com.itwill.myleaves.dto.order.TotalOrderCreateDto;
import com.itwill.myleaves.dto.order.TotalOrderUpdateDto;
import com.itwill.myleaves.repository.cart.Cart;
import com.itwill.myleaves.repository.store.Store;
import com.itwill.myleaves.repository.totalOrder.TotalOrder;
import com.itwill.myleaves.service.address.AddressService;
import com.itwill.myleaves.service.cart.CartService;
import com.itwill.myleaves.service.orderDetail.OrderDetailService;
import com.itwill.myleaves.service.store.StoreService;
import com.itwill.myleaves.service.totalOrder.TotalOrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
	
	private final TotalOrderService totalOrderService;
	private final OrderDetailService orderDetailService;
	private final AddressService addressService;
	private final CartService cartService;
	private final StoreService storeService;
	
	
	@GetMapping("/orderDetail")
	public void read(Cart cart, Model model) {
		List<Cart> cartList = cartService.read(cart);
		
		Map<Long, Store> storeList = new HashMap<>();
		
		Long totalItemPrice = (long) 0;
		Long totalCnt = (long)0;
		for(Cart c : cartList) {
			totalItemPrice += ( storeService.read(c.getItemId()).getPrice() * c.getCnt()); 
			totalCnt += c.getCnt();
			storeList.put(c.getItemId(), storeService.read(c.getItemId()));
		}
		
		model.addAttribute("carts", cartList);
		model.addAttribute("price", totalItemPrice);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("stores", storeList);
	}
	
	/**
	 * 무통장
	 * 최종 결제 create
	 * 1. 빈 total_order insert
	 * 2. cart의 내용을 orderDetail에 insert
	 * 3. 1번의 order_id의 정보 갖고오기
	 * 4. address insert
	 * 5. total_order를 주문 내용으로 UPDATE하기(결제 정보는 지금 update)
	 * 6. 다 됐으면 orderSuccess로 redirect
	 */
	@PostMapping("/orderDetail")
	public String create(TotalOrderCreateDto tcdto, AddressCreateDto adto, TotalOrderUpdateDto tudto) {
		TotalOrder blankTotalOrder =  totalOrderService.create(tcdto); // 1. 빈 total_order insert
		
		Long orderId = blankTotalOrder.getOrderId(); // 2. 1번의 order_id의 정보 갖고오기
		
		// 카트의 아이템 가지고 온다.
		List<Cart> cartList = cartService.findByUserId(blankTotalOrder.getUserId());
		for(Cart c: cartList) {
			Long itemId= c.getItemId();
			// 6. store 에서 inven 각 cnt 만큼 빼기
			Store store = storeService.read(itemId);
			store.setInven(store.getInven() - c.getCnt());
			
			if(store.getInven() == 0) {
				store.setSold(1); // 재고 0이면 풀절로 바뀜
			}
			
			Long price = storeService.read(c.getItemId()).getPrice() * c.getCnt();
			Long cnt = (long) c.getCnt();
			orderDetailService.create(orderId, itemId, price, cnt); // 3. cart의 내용을 orderDetail에 insert
		}
		
		
		addressService.create(adto); // 4. address insert
		
		totalOrderService.update(orderId, tudto); //  5. total_order를 주문 내용으로 UPDATE하기(결제 정보는 지금 update)
		
		// 7. 결제 후 장바구니 비우기
		cartService.delete(blankTotalOrder.getUserId());
		
		return "redirect:/order/orderSuccess";
		
	}
	
	@GetMapping("/orderSuccess")
	public void read() {
		
	}
	
	
}
