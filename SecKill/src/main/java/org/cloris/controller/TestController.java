package org.cloris.controller;

import java.util.List;

import org.cloris.dao.UserDao;
import org.cloris.domain.User;
import org.cloris.vo.CodeMessage;
import org.cloris.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@Autowired
	UserDao userDao;

	@GetMapping("/test")
	@ResponseBody
	public Result<String> test() {
		return Result.success("test");
	}

	@GetMapping("/test2")
	@ResponseBody
	public Result<String> error() {
		return Result.error(CodeMessage.SERVER_ERROR);
	}

	@GetMapping("/hello")
	public String hello(Model model) {
		model.addAttribute("name", "cloris");
		return "test";
	}

	@GetMapping("/users")
	@ResponseBody
	public Result<List<User>> list() {
		List<User> users = userDao.findAll();
		return Result.success(users);
	}

	@GetMapping("/login")
	public String login() {

		return "login";
	}

}
