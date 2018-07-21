package org.cloris.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.cloris.service.UserService;
import org.cloris.vo.LoginInfo;
import org.cloris.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

	@Autowired
	UserService userService;

	@GetMapping("/")
	public String index() {
		return "login";
	}

	/**
	 * 处理登录请求
	 */
	@PostMapping("/login")
	@ResponseBody
	public Result<Boolean> login(HttpServletResponse response, @Valid LoginInfo loginInfo) {
		System.out.println(loginInfo);
		userService.login(response, loginInfo);
		return Result.success(true);
	}

}
