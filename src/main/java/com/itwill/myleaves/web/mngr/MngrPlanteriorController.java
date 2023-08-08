package com.itwill.myleaves.web.mngr;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.myleaves.repository.planterior.Bookmark;
import com.itwill.myleaves.repository.planterior.Planterior;
import com.itwill.myleaves.service.palnterior.MypageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mngr/planterior")
public class MngrPlanteriorController {

	private final MypageService mypageService;

	// 북마크 가져오기 -> 삭제는 js가 담당함.
	@GetMapping
	public String read(Model model) {
		log.info("read");
		String user = "admin";

		// 북마크 가져오기
		List<Bookmark> list = mypageService.bookmarkRead(user);
		log.info("sizeb={}", list.size());

		List<Planterior> plist = mypageService.read();
		log.info("sizep={}", plist.size());

		// 보낼 list
		List<Planterior> result = new ArrayList<>();

		for (int i = 0; i < plist.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				if (plist.get(i).getPlanteriorId() == list.get(j).getPlanteriorId()) {
					log.info("result = {}", plist.get(i));
					result.add(plist.get(i));
				}
			}
		}
		log.info("size={}", result.size());

		model.addAttribute("cardList", result);
		
		return "mngr/planterior/list";
	}

}
