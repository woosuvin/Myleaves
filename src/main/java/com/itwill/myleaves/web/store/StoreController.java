package com.itwill.myleaves.web.store;

import java.util.Base64;
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
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.myleaves.repository.sellbuy.BuyWish;
import com.itwill.myleaves.repository.sellbuy.Sell;
import com.itwill.myleaves.repository.store.Store;
import com.itwill.myleaves.repository.store.StoreWish;
import com.itwill.myleaves.service.store.MypageStoreService;
import com.itwill.myleaves.service.store.StoreService;
import com.itwill.myleaves.web.mypage.MyPageStoreController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 수빈
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {
	
	private final StoreService storeService;
	private final MypageStoreService mypageStoreService;
	
	@GetMapping("/list")
	public void read(Model model, @PageableDefault(page = 0, size = 8) Pageable pageable) {
		//log.info("storeList:GET");
		
		Page<Store> list = storeService.readUserPage(pageable);
		model.addAttribute("stores", list);
		
		Map<Long, String> thumbnails = new HashMap<>();
		for(Store store: list){
			thumbnails.put(store.getItemId(), Base64.getEncoder().encodeToString(store.getThumbnail()));
        }
        model.addAttribute("images", thumbnails);
		
        int totalPage = list.getTotalPages()-1;
		int nowPage = list.getPageable().getPageNumber()+1; //지금 페이지 0 + 1 => 1 페이지부터 시작
		int startPage = Math.max(nowPage-4, 1);
		int endPage = Math.min(nowPage+5, list.getTotalPages());
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
	}
	
	@PreAuthorize("hasRole('MEMBER')")
	@GetMapping("/detail")
	public void read(String userId, long itemId, Model model) {
		//log.info("read(itemId={})", itemId);
		
		List<StoreWish> storeWishList = storeService.readStoreWish(itemId);
		//log.info("{}", storeWishList);
		
		Boolean result = null;
		
		for (StoreWish x : storeWishList) {
			if (x.getUserId().equals(userId)) {
				result = true;
				break;
			} else {
				result = false;
			}
		}
		model.addAttribute("wish", result);
		
		Store store = storeService.read(itemId); // itemId로 store 테이블에서 검색
		String image = Base64.getEncoder().encodeToString(store.getThumbnail());
		
		model.addAttribute("image", image);
		model.addAttribute("store", store); // model에 저장
	}
}
