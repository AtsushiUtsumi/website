package com.au.jiro.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.au.jiro.dao.search.user.UserSearchEntity;
import com.au.jiro.dao.search.user.UserSearchDao;
import com.au.jiro.dao.search.user.UserSearchParams;

@Controller
@RequestMapping("/user/list")
public class UserListController {

	final String OWN_PAGE = "user/userList";

	@Autowired
	private UserSearchDao userSearchDao;

	/**
	 * 初期表示
	 * 
	 * @return
	 */
	@GetMapping()
	public String index(UserListForm form, Model model) {
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
	public String search(UserListForm form, Model model) {

		List<UserSearchEntity> resultList = userSearchDao.search(new UserSearchParams());

		// TODO: 詰め替え作業
		// form.setRows(rows);

		return OWN_PAGE;
	}
}
