package org.cloris.service;

import javax.servlet.http.HttpServletResponse;

import org.cloris.domain.User;
import org.cloris.vo.LoginInfo;

public interface UserService {
	User findById(String id);

	boolean login(HttpServletResponse response, LoginInfo loginInfo);

	User findByToken(String token);
}
