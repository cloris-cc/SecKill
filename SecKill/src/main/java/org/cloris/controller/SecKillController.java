package org.cloris.controller;

import org.cloris.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecKillController {

	@PostMapping("/secKill")
	@ResponseBody
	public String secKill(Model model, User user, @RequestParam("goodsId") Long id) {
		if(user == null) {
			return "login";
		}
		
		
		System.out.println(user);
		System.out.println(id);
		return "hahaha";
	}

}
