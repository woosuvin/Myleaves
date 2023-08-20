package com.itwill.myleaves.web.planterior;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.myleaves.dto.planterior.TotalCreateDto;
import com.itwill.myleaves.dto.planterior.PlanteriorCategoryCreateDto;
import com.itwill.myleaves.dto.planterior.PlanteriorCreateDto;
import com.itwill.myleaves.repository.member.Member;
import com.itwill.myleaves.repository.planterior.Bookmark;
import com.itwill.myleaves.repository.planterior.Planterior;
import com.itwill.myleaves.repository.planterior.PlanteriorCategory;
import com.itwill.myleaves.repository.planterior.PlanteriorRepository;
import com.itwill.myleaves.service.palnterior.BookmarkService;
import com.itwill.myleaves.service.palnterior.CategoryService;
import com.itwill.myleaves.service.palnterior.MypageService;
import com.itwill.myleaves.service.palnterior.PlanteriorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/planterior")
public class PlanteriorHomecontroller {

	// 의존성 주입!!!!
	private final PlanteriorService planteriorService;
	private final BookmarkService bookmarkService;
	private final CategoryService categoryService;
	private final MypageService mypageService;

	@GetMapping
	// catertgorydto-> param
	public String planterior(Model model, Authentication auth) {
//		log.info("planterior");

		// 플랜테리어 카드 전체 출력
		List<Planterior> list = planteriorService.read();
		model.addAttribute("cardList", list);
		
//		// 페이징
//		Boolean hasNext = list.hasNext(); // 다음 Slice가 존재하는지 존재할 경우 return 값 true
//		log.info("확인: {}", hasNext);
////		Pageable nextContains = list.nextPageable().
//		List<Planterior> nextpl = list.getContent();
//		int num = list.getNumber();
//		
//		model.addAttribute("num", num);
//		model.addAttribute("next", nextpl);
//		model.addAttribute("hasNext", hasNext);
		

		// 이미지
		Map<Long, String> thumbnails = new HashMap<>();
		for (Planterior p : list) {
			thumbnails.put(p.getPlanteriorId(), Base64.getEncoder().encodeToString(p.getThumbnail()));
		}
		model.addAttribute("images", thumbnails);

		// 북마크
		List<Bookmark> bookList = bookmarkService.read();
		Map<Long, Long> bookmarkMap = new HashMap<>();

		if (auth != null && auth.isAuthenticated()) {
			// 아이디 비교
			// 현재 로그인한 사용자 정보 가져오기
			Member loggedInUser = (Member) auth.getPrincipal();
			for (Bookmark p : bookList) {
				if (p.getUserId().equals(loggedInUser.getUserId())) {
					bookmarkMap.put(p.getPlanteriorId(), p.getPlanteriorId());
				}
			}
		}
		model.addAttribute("bookmark", bookmarkMap);

		// 관리자
		List<Planterior> result = new ArrayList<>();
		List<Planterior> plist = mypageService.read();
		List<Bookmark> listMngr = mypageService.bookmarkRead("admin");

		Map<Long, String> thumbnails1 = new HashMap<>();
		for (Planterior p : list) {
			thumbnails1.put(p.getPlanteriorId(), Base64.getEncoder().encodeToString(p.getThumbnail()));
		}
		model.addAttribute("imagesMngr", thumbnails1);

		for (int i = 0; i < plist.size(); i++) {
			for (int j = 0; j < listMngr.size(); j++) {
				if (plist.get(i).getPlanteriorId() == listMngr.get(j).getPlanteriorId()) {
					result.add(plist.get(i));
				}
			}
		}
		model.addAttribute("mngrCount", result.size());
		model.addAttribute("mngrList", result);

		return "planterior/home";
	}

	// 검색
	@GetMapping("search")
	public String filterRead(Model model, PlanteriorCategoryCreateDto dto, Authentication auth) {
		// log.info("filterRead(dto={})", dto);

		if (dto.getConditionContent().isEmpty()) {
			List<PlanteriorCategory> stateList = categoryService.findState(dto.getStateContent());
			List<Planterior> list = planteriorService.read();
			List<Bookmark> bookList = bookmarkService.read();

			// (1) 검색에 해당하는 플래테리어 카드 가져오기.
			List<Planterior> preResult = new ArrayList<>();

			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < stateList.size(); j++) {
					if (list.get(i).getPlanteriorId() == stateList.get(j).getPlanteriorId()) {
						preResult.add(list.get(i));
					}
				}
			}
			// log.info("검색 플랜테리어 사이즈= {}", preResult.size());

			Map<Long, String> thumbnails = new HashMap<>();
			for (Planterior p : preResult) {
				thumbnails.put(p.getPlanteriorId(), Base64.getEncoder().encodeToString(p.getThumbnail()));
			}
			model.addAttribute("images", thumbnails);

			// (2) 북마크 가져오기
			Map<Long, Long> bookmarkMap = new HashMap<>();

			if (auth != null && auth.isAuthenticated()) {

				// 아이디 비교
				// 현재 로그인한 사용자 정보 가져오기
				Member loggedInUser = (Member) auth.getPrincipal();
				for (Bookmark p : bookList) {
					if (p.getUserId().equals(loggedInUser.getUserId())) {
						bookmarkMap.put(p.getPlanteriorId(), p.getPlanteriorId());
					}
				}
			}

			// (3) md 추천
			List<Planterior> result = new ArrayList<>();
			List<Planterior> plist = mypageService.read();
			List<Bookmark> listMngr = mypageService.bookmarkRead("admin");

			Map<Long, String> thumbnails1 = new HashMap<>();
			for (Planterior p : list) {
				thumbnails1.put(p.getPlanteriorId(), Base64.getEncoder().encodeToString(p.getThumbnail()));
			}
			model.addAttribute("imagesMngr", thumbnails1);

			for (int i = 0; i < plist.size(); i++) {
				for (int j = 0; j < listMngr.size(); j++) {
					if (plist.get(i).getPlanteriorId() == listMngr.get(j).getPlanteriorId()) {
						result.add(plist.get(i));
					}
				}
			}

			model.addAttribute("mngrList", result);
			model.addAttribute("cardList", preResult);
			model.addAttribute("bookmark", bookmarkMap);
			model.addAttribute("count", preResult.size());
			return "planterior/home";

		} else {
			List<PlanteriorCategory> stateList = categoryService.findStateAndCondition(dto.getStateContent(),
					dto.getConditionContent());
			List<Planterior> list = planteriorService.read();
			List<Bookmark> bookList = bookmarkService.read();

			// (1) 검색에 해당하는 플래테리어 카드 가져오기.
			List<Planterior> preResult = new ArrayList<>();

			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < stateList.size(); j++) {
					if (list.get(i).getPlanteriorId() == stateList.get(j).getPlanteriorId()) {
						preResult.add(list.get(i));
					}
				}
			}
			// log.info("검색 플랜테리어 사이즈= {}", preResult.size());

			Map<Long, String> thumbnails = new HashMap<>();
			for (Planterior p : preResult) {
				thumbnails.put(p.getPlanteriorId(), Base64.getEncoder().encodeToString(p.getThumbnail()));
			}
			model.addAttribute("images", thumbnails);
			model.addAttribute("cardList", preResult);

			// (2) 북마크 가져오기
			Map<Long, Long> bookmarkMap = new HashMap<>();

			if (auth != null && auth.isAuthenticated()) {

				// 아이디 비교
				// 현재 로그인한 사용자 정보 가져오기
				Member loggedInUser = (Member) auth.getPrincipal();
				for (Bookmark p : bookList) {
					if (p.getUserId().equals(loggedInUser.getUserId())) {
						bookmarkMap.put(p.getPlanteriorId(), p.getPlanteriorId());
					}
				}
			}

			// (3) md 추천
			List<Planterior> result = new ArrayList<>();
			List<Planterior> plist = mypageService.read();
			List<Bookmark> listMngr = mypageService.bookmarkRead("admin");

			Map<Long, String> thumbnails1 = new HashMap<>();
			for (Planterior p : list) {
				thumbnails1.put(p.getPlanteriorId(), Base64.getEncoder().encodeToString(p.getThumbnail()));
			}
			model.addAttribute("imagesMngr", thumbnails1);

			for (int i = 0; i < plist.size(); i++) {
				for (int j = 0; j < listMngr.size(); j++) {
					if (plist.get(i).getPlanteriorId() == listMngr.get(j).getPlanteriorId()) {
						result.add(plist.get(i));
					}
				}
			}
			model.addAttribute("mngrList", result);
			model.addAttribute("bookmark", bookmarkMap);
			model.addAttribute("count", preResult.size());
			return "planterior/home";
		}

	}

	@PreAuthorize("hasRole('MEMBER')")
	@GetMapping("/create")
	public void create() {
		//log.info("planteriorCreate");

	}

	@PreAuthorize("hasRole('MEMBER')")
	@PostMapping("/create")
	public String create(TotalCreateDto dto) throws IOException {
		//log.info("create(dto ={}) post", dto);

		dto.setThumbnail(dto.getFile().getBytes());

		// form에서 가져온 data DB insert
		Planterior entity = planteriorService.create(dto.planteriorCreateDto());
		//log.info("확인 = {}", entity.getPlanteriorId());

		categoryService.create(dto.planteriorCategoryCreateDto(entity.getPlanteriorId()));

		return "redirect:/planterior";

	}

}
