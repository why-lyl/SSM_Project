package com.team.erp.framework.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {

	/**
	 * 登录认证
	 */
	String checkLogin(String username, String password, String selectionBox, HttpServletRequest request,
			HttpServletResponse response);

	String queryCookie(HttpServletRequest request, HttpServletResponse response);

}
