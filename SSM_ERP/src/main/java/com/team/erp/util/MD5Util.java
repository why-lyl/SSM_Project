package com.team.erp.util;

import org.apache.shiro.crypto.hash.SimpleHash;

public class MD5Util {
	// 在注册的时候输入的密码
	private String userPassword;
	// 将用户名用作盐
	private String userName;
    //数字表示加密次数
	public String getPasswordByMD5(String password, String salt) {
		String simpleHash = new SimpleHash("MD5", password, salt, 520).toString();
		return simpleHash;
	}
}
