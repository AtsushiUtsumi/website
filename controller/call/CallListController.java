package com.au.jiro.controller.call;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.au.jiro.dao.search.call.CallSearchEntity;
import com.au.jiro.controller.cache.UserHoldCache;
import com.au.jiro.controller.cache.UserRestoreCache;
import com.au.jiro.dao.search.call.CallSearchDao;
import com.au.jiro.dao.search.call.CallSearchParams;

@Controller
@RequestMapping("/call/list")
public class CallListController {

	final String OWN_PAGE = "call/callList";

	@Autowired
	private CallSearchDao callSearchDao;

	/**
	 * 初期表示
	 * 
	 * @return
	 */
	@GetMapping()
	// @UserLog
	@UserRestoreCache
	public String index(CallListForm form, Model model) {
		return OWN_PAGE;
	}

	/**
	 * 「検索」ボタン押下
	 * 
	 * @param form
	 * @param model
	 * @return
	 */
	@PostMapping(params = "search")
	// @UserLog
	@UserHoldCache
	public String search(CallListForm form, Model model) {

		List<CallSearchEntity> resultList = callSearchDao.search(new CallSearchParams());

		// TODO: 詰め替え作業
		// form.setRows(rows);

		return OWN_PAGE;
	}
}
