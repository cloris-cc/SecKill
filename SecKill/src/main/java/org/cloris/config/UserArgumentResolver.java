package org.cloris.config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cloris.domain.User;
import org.cloris.service.UserService;
import org.cloris.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

	@Autowired
	UserService userService;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// 若 parameter 是 User 类型, 则执行下面的方法
		Class<?> clazz = parameter.getParameterType();
		return clazz == User.class;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		// 得到 Controller 的 request 和 response
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
		// 取出token
		// 1. 从请求参数取出
		// 2. 从 CookieValue 中取出
		String paramToken = request.getParameter(UserServiceImpl.COOKIE_NAME);
		String cookieToken = getCookieValue(request, UserServiceImpl.COOKIE_NAME);

		if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
			return null;
		}
		String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
		User user = userService.findByToken(response, token);
		return user;
	}

	/**
	 * 从 Request 中取得 Cookie 值
	 */
	private String getCookieValue(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		if(cookies == null || cookies.length <= 0) {
			return null;
		}
		
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(cookieName)) {
				return cookie.getValue();
			}
		}
		return null;
	}

}
