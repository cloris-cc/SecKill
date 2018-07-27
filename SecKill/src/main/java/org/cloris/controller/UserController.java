package org.cloris.controller;

import org.cloris.domain.User;
import org.cloris.service.UserService;
import org.cloris.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/user")
	@ResponseBody
	public Result<User> user(Model model, User user) {
		return Result.success(user);
	}
}
