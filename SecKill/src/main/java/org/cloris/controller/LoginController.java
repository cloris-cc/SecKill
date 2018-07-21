package org.cloris.controller;

import javax.validation.Valid;

import org.cloris.service.UserService;
import org.cloris.vo.CodeMessage;
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

	@PostMapping("/login")
	@ResponseBody
	public Result<Boolean> login(@Valid LoginInfo loginInfo) {
		System.out.println(loginInfo);
		// 参数校验,验证手机号码和表单密码
		// 用 JSR303 
//		String formPass = loginInfo.getPassword();
//		String mobile = loginInfo.getMobile();
//		if (StringUtils.isEmpty(formPass)) {
//			return Result.error(CodeMessage.PASSWORD_EMPTY);
//		}
//		if (StringUtils.isEmpty(mobile)) {
//			return Result.error(CodeMessage.MOBILE_EMPTY);
//		}
//		if (!ValidatorUtil.isMobile(mobile)) {
//			return Result.error(CodeMessage.MOBILE_WRONG);
//		}
		// 登录
		CodeMessage codeMessage = userService.login(loginInfo);
		if (codeMessage.getCode() == 200) {
			return Result.success(true);
		} else {
			return Result.error(codeMessage);
		}
	}

}
