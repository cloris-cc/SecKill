package org.cloris.service;

import org.cloris.domain.User;
import org.cloris.vo.LoginInfo;

public interface UserService {
	User findById(String id);
	
	boolean login(LoginInfo loginInfo);
}
