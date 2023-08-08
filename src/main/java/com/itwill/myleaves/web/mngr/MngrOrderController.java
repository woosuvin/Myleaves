package com.itwill.myleaves.web.mngr;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.myleaves.dto.order.TotalOrderUpdateDto;
import com.itwill.myleaves.repository.totalOrder.TotalOrder;
import com.itwill.myleaves.service.totalOrder.TotalOrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mngr")
public class MngrOrderController {

	private final TotalOrderService totalOrderService;
	
	@GetMapping("/order/list")
	public void mngrHome(Model model) {
		log.info("mngr order");
		List<TotalOrder> list = totalOrderService.read();
		model.addAttribute("totalOrders", list);
	}
	
	
}
