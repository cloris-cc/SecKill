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
		// 取缓存
		User user = template.opsForValue().get(id);
		if (user != null) {
			return user;
		}
		// 取数据库
		user = userDao.findById(id);
		if (user != null) {
			template.opsForValue().set(id, user);
		}
		return user;
	}
	
	@Override
	public Boolean updatePassword(String token, String id, String password) {
		// 更新数据库
		userDao.updatePassword(id, password);
		// 处理缓存
		template.delete(id);
		template.opsForValue().set("tk" + token, userDao.findById(id));
		return true;
	}

	@Override
	public String login(HttpServletResponse response, LoginInfo loginInfo) {
		if (loginInfo == null) {
			throw new GlobalException(CodeMessage.SERVER_ERROR);
		}
		// 判断用户(手机号)是否存在
		User user = findById(loginInfo.getMobile());
		if (user == null) {
			throw new GlobalException(CodeMessage.MOBILE_NOT_EXIST);
		}
		// 验证密码
		String formPass = loginInfo.getPassword();
		String salt = user.getSalt();
		String password = MD5Util.formPassToDBPass(formPass, salt);
		if (!user.getPassword().equals(password)) {
			throw new GlobalException(CodeMessage.PASSWORD_WRONG);
		}

		// 生成 Cookie, 存储到 Redis 缓存中
		String token = UUIDUtil.uuid();
		addCookie(response, token, user);

		return token;
	}

	@Override
	public void addCookie(HttpServletResponse response, String token, User user) {
		System.out.println("-----token: " + token);
		System.out.println("-----user: " + user);
		template.opsForValue().set("tk" + token, user);
		Cookie cookie = new Cookie(COOKIE_NAME, token);
		// Cookie生命周期为7天
		cookie.setMaxAge(3600 * 24 * 7);
		cookie.setPath("/");
		// 将 cookie 添加到 response 中
		response.addCookie(cookie);
	}

	@Override
	public User findByToken(HttpServletResponse response, String token) {
		if (StringUtils.isEmpty(token)) {
			return null;
		} else {
			User user = template.opsForValue().get("tk" + token);
			if (user != null) { // 每次使用时重置cookie有效期
				addCookie(response, token, user);
			}
			return user;
		}
	}

	

}
