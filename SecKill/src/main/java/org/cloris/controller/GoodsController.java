package org.cloris.controller;

import org.cloris.domain.User;
import org.cloris.service.UserService;
import org.cloris.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GoodsController {
	
	@Autowired
	UserService userService;

	@GetMapping("/goods")
	public String goodsList(Model model,
			@CookieValue(value = UserServiceImpl.COOKIE_NAME, required = false) String cookieToken,
			@RequestParam(value = UserServiceImpl.COOKIE_NAME, required = false) String paramToken) {
//		model.addAttribute("user", new User());

		if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
			return "login";
		}
		String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
		
		User user = userService.findByToken(token);
		
		model.addAttribute("user", user);
		return "goods";
	}
}
