package com.itwill.myleaves.web.cart;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
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
	
	@PreAuthorize("hasRole('MEMBER')")
	@GetMapping("/list")
	public void read(Cart cart, Model model) {
//		log.info("read()");
		List<Cart> cartList = cartService.read(cart);
		List<Store> storeList = new ArrayList<>();
		
		for(Cart c : cartList) {
			Store store = storeService.read(c.getItemId());
			storeList.add(store);
		}
		
		Map<Long, String> thumbnails = new HashMap<>();
		for(Store store: storeList){
			thumbnails.put(store.getItemId(), Base64.getEncoder().encodeToString(store.getThumbnail()));
        }
        model.addAttribute("images", thumbnails);
		model.addAttribute("carts", cartList);
		model.addAttribute("stores", storeList);
	}
	
	
	
	
}
