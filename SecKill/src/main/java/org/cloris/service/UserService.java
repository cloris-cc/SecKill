package org.cloris.service;

import org.cloris.domain.User;
import org.cloris.vo.CodeMessage;
import org.cloris.vo.LoginInfo;

public interface UserService {
	User findById(String id);
	
	CodeMessage login(LoginInfo loginInfo);
}
