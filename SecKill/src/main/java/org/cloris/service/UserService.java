package org.cloris.service;

import javax.servlet.http.HttpServletResponse;

import org.cloris.domain.User;
import org.cloris.vo.LoginInfo;

public interface UserService {
	User findById(String id);

	/**
	 * 处理登录请求
	 */
	String login(HttpServletResponse response, LoginInfo loginInfo);

	/**
	 * 添加Cookie到Redis缓存中
	 */
	void addCookie(HttpServletResponse response, String token, User user);

	/**
	 * 通过Cookie<token>从Redis缓存中获取User
	 */
	User findByToken(HttpServletResponse response, String token);

	/**
	 * 修改密码
	 */
	Boolean updatePassword(String token, String id, String password);

}
