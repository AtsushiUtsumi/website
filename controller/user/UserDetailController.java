package com.au.jiro.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.au.jiro.dao.crud.user.User;
import com.au.jiro.service.user.UserService;

@Controller
@RequestMapping("/user")
public class UserDetailController {

	@Autowired
	private UserService userService;

	// 入力チェック
	private boolean inputCheck(UserDetailForm form) {
		return form != null;
	}

	/**
	 * 登録画面
	 * 
	 * @param form
	 * @param model
	 * @return
	 */
  	@GetMapping("/register")
  	public String registerIndex(UserDetailForm form, Model model) {
  		return "user/userRegister";
  	}

  	@PostMapping("/register")
  	public String register(UserDetailForm form, Model model) {

	    if (!inputCheck(form)) {
		    return "user/userRegister";
		}

	    userService.register(new User());

  		return "user/userRegister";
  	}

	/**
	 * 編集画面
	 * 
	 * @param form
	 * @param model
	 * @return
	 */
  	@GetMapping("/edit")
  	public String editIndex(UserDetailForm form, Model model) {
  		return "user/userEdit";
  	}

  	@PostMapping(path = "/edit", params="update")
  	public String update(UserDetailForm form, Model model) {
	    userService.update(new User());
  		return "user/userEdit";
  	}

  	@PostMapping(path = "/edit", params="delete")
  	public String delete(UserDetailForm form, Model model) {
	    userService.delete(new User());
  		return "user/userEdit";
  	}
}
