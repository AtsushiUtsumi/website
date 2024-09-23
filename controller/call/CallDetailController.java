package com.au.jiro.controller.call;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.au.jiro.dao.crud.call.Call;
import com.au.jiro.service.call.CallService;

@Controller
@RequestMapping("/call")
public class CallDetailController {

	@Autowired
	private CallService callService;

	// 入力チェック
	private boolean inputCheck(CallDetailForm form) {
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
  	public String registerIndex(CallDetailForm form, Model model) {
  		return "call/callRegister";
  	}

  	@PostMapping("/register")
  	public String register(CallDetailForm form, Model model) {

	    if (!inputCheck(form)) {
		    return "call/callRegister";
		}

	    callService.register(new Call());

  		return "call/callRegister";
  	}

	/**
	 * 編集画面
	 * 
	 * @param form
	 * @param model
	 * @return
	 */
  	@GetMapping("/edit")
  	public String editIndex(CallDetailForm form, Model model) {
  		return "call/callEdit";
  	}

  	@PostMapping(path = "/edit", params="update")
  	public String update(CallDetailForm form, Model model) {
	    callService.update(new Call());
  		return "call/callEdit";
  	}

  	@PostMapping(path = "/edit", params="delete")
  	public String delete(CallDetailForm form, Model model) {
	    callService.delete(new Call());
  		return "call/callEdit";
  	}
}
