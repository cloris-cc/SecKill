package org.cloris.controller;

import org.cloris.domain.User;
import org.cloris.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GoodsController {

	@Autowired
	UserService userService;

	@GetMapping("/goods")
	public String goodsList(Model model, User user) {
//		model.addAttribute("user", new User());

		// 每次都要验证token是否为空才返回login页面
//		if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
//			return "login";
//		}
//		String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
//
//		User user = userService.findByToken(response, token);

		model.addAttribute("user", user);
		return "goods";
	}
}
