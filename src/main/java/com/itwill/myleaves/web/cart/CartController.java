package com.itwill.myleaves.web.cart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.myleaves.dto.cart.CartListDto;
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
	
	@GetMapping("/order")
	public void order(Model model) {
		log.info("order:GET");
	}
	
}
