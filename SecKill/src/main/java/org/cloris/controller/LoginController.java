package org.cloris.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.cloris.service.UserService;
import org.cloris.vo.LoginInfo;
import org.cloris.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

	@Autowired
	UserService userService;

	/**
	 * 跳转页面
	 */
	@RequestMapping("/")
	public String index() {
		return "login";
	}

	/**
	 * 处理登录请求
	 */
	@PostMapping("/login")
	@ResponseBody
	public Result<String> login(HttpServletResponse response, @Valid LoginInfo loginInfo) {
		System.out.println(loginInfo);
		String token = userService.login(response, loginInfo);
		return Result.success(token);
	}

	@PostMapping("/register")
	@ResponseBody
	public Result<String> register(@Valid LoginInfo loginInfo) {
		userService.register(loginInfo);
		return Result.success("Registered");
	}

}
