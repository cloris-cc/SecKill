package org.cloris.service;

import javax.servlet.http.HttpServletResponse;

import org.cloris.domain.User;
import org.cloris.vo.LoginInfo;

public interface UserService {
	User findById(String id);

	/**
	 * 处理登录请求
	 */
	boolean login(HttpServletResponse response, LoginInfo loginInfo);

	/**
	 * 添加Cookie到Redis缓存中
	 */
	void addCookie(HttpServletResponse response, User user);

	/**
	 * 通过Cookie<token>从Redis缓存中获取User
	 */
	User findByToken(HttpServletResponse response, String token);

}
