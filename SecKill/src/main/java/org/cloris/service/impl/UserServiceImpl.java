package org.cloris.service.impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.cloris.dao.UserDao;
import org.cloris.domain.User;
import org.cloris.service.UserService;
import org.cloris.utils.MD5Util;
import org.cloris.utils.UUIDUtil;
import org.cloris.utils.exception.GlobalException;
import org.cloris.vo.CodeMessage;
import org.cloris.vo.LoginInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {

	public static final String COOKIE_NAME = "token";

	@Autowired
	UserDao userDao;
	@Autowired
	RedisTemplate<String, User> template;

	@Override
	public User findById(String id) {
		return userDao.findById(id);
	}

	@Override
	public boolean login(HttpServletResponse response, LoginInfo loginInfo) {
		if (loginInfo == null) {
//			return CodeMessage.SERVER_ERROR;
			throw new GlobalException(CodeMessage.SERVER_ERROR);
		}
		// 判断用户(手机号)是否存在
		User user = findById(loginInfo.getMobile());
		if (user == null) {
//			return CodeMessage.MOBILE_NOT_EXIST;
			throw new GlobalException(CodeMessage.MOBILE_NOT_EXIST);
		}
		// 验证密码
		String formPass = loginInfo.getPassword();
		String salt = user.getSalt();
		String password = MD5Util.formPassToDBPass(formPass, salt);
		if (!user.getPassword().equals(password)) {
//			return CodeMessage.PASSWORD_WRONG;
			throw new GlobalException(CodeMessage.PASSWORD_WRONG);
		}

		// 生成 Cookie, 存储到 Redis 缓存中
		String token = UUIDUtil.uuid();
		System.out.println("-----token: " + token);
		System.out.println("-----user: " + user);
		template.opsForValue().set("tk" + token, user);
		Cookie cookie = new Cookie(COOKIE_NAME, token);
		cookie.setMaxAge(3600);
		cookie.setPath("/");
		// 将 cookie 添加到 response 中
		response.addCookie(cookie);

		return true;
	}

	@Override
	public User findByToken(String token) {
		if (StringUtils.isEmpty(token)) {
			return null;
		} else {
			System.out.println("1 ------: " + template.opsForValue().get("tk" + token));
			System.out.println("2 ------: " + (User) template.opsForValue().get("tk" + token));
			return (User) template.opsForValue().get("tk" + token);
		}
	}

}
