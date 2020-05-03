package com.team.erp.framework.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.erp.framework.model.User;
import com.team.erp.framework.model.vo.Result;
import com.team.erp.framework.model.vo.UserAndAuthority;

public interface UserService {

	/**
	 * 登录认证
	 */
	String checkLogin(String username, String password, String selectionBox, HttpServletRequest request,
			HttpServletResponse response);

	String queryCookie(HttpServletRequest request, HttpServletResponse response);
	
	User selectUserByUserName(String userName);

	String checkRegister(String username, String password, String repassword);
	
	String showWelcome(HttpServletRequest request, HttpServletResponse response);
	
	

}
