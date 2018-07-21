package org.cloris.service.impl;

import org.cloris.dao.UserDao;
import org.cloris.domain.User;
import org.cloris.service.UserService;
import org.cloris.utils.MD5Util;
import org.cloris.vo.CodeMessage;
import org.cloris.vo.LoginInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public User findById(String id) {
		return userDao.findById(id);
	}

	@Override
	public CodeMessage login(LoginInfo loginInfo) {
		if (loginInfo == null) {
			return CodeMessage.SERVER_ERROR;
		}
		// 判断用户(手机号)是否存在
		User user = findById(loginInfo.getMobile());
		if (user == null) {
			return CodeMessage.MOBILE_NOT_EXIST;
		}
		// 验证密码
		String formPass = loginInfo.getPassword();
		String salt = user.getSalt();
		String password = MD5Util.formPassToDBPass(formPass, salt);
		if (!user.getPassword().equals(password)) {
			return CodeMessage.PASSWORD_WRONG;
		}
		return CodeMessage.SUCCESS;
	}

}
