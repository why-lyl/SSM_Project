package com.team.erp.framework.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.erp.util.controller.BaseController;

@Controller
@RequestMapping("/userController")
public class UserController extends BaseController{
	
	@RequestMapping("/checkLogin.ajax")
	@ResponseBody
	public String checkLogin(String username, String password, String selectionBox, HttpServletRequest req, HttpServletResponse response) {
     // System.out.println("连接后台成功");
		
     //	System.out.println(username +" "+ password +" "+ selectionBox);
		//System.out.println(us);
		//调用业务层来完成登录验证
		String info = us.checkLogin(username,password,selectionBox);
		return info;
		
	}
	

}
