package com.itwill.myleaves.web.cart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.myleaves.repository.cart.Cart;
import com.itwill.myleaves.repository.store.Store;
import com.itwill.myleaves.service.cart.CartService;
import com.itwill.myleaves.service.store.StoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
	
	private final CartService cartService;
	private final StoreService storeService;
	
	@GetMapping("/list")
	public void read(Cart cart, Model model) {
		log.info("read()");
		List<Cart> cartList = cartService.read(cart);
		List<Store> storeList = new ArrayList<>();
		
		for(Cart c : cartList) {
			Store store = storeService.read(c.getItemId());
			storeList.add(store);
		}
		
		model.addAttribute("carts", cartList);
		model.addAttribute("stores", storeList);
	}
	
	/**
	 * 수빈
	 * 장바구니에서 결제 페이지로 이동
	 * @param userId
	 * @return
	 */
	@PostMapping("/orderDetail")
	public String insert(String userId) {
		return "redirect:/cart/orderDetail?userId=" + userId;
	}
	
	
}
