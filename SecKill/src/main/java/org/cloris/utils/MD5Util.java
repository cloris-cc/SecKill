package org.cloris.utils;

import org.springframework.util.DigestUtils;

public class MD5Util {

	public static String md5(String str) {
		return DigestUtils.md5DigestAsHex(str.getBytes());
	}

	private static final String salt = "1a2b3c4d";

	public static String inputPassToFormPass(String inputPass) {
		String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
		System.out.println(str);
		return md5(str);
	}

	public static String formPassToDBPass(String formPass, String salt) {
		String str = "" + salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4);
		return md5(str);
	}

	public static String inputPassToDbPass(String inputPass, String saltDB) {
		String formPass = inputPassToFormPass(inputPass);
		String dbPass = formPassToDBPass(formPass, saltDB);
		return dbPass;
	}

}
