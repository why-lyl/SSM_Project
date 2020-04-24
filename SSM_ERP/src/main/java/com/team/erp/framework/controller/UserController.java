package com.team.erp.framework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/userController")
public class UserController {
	
	@RequestMapping("/checkLogin.ajax")
	@ResponseBody
	public String checkLogin() {
		System.out.println("连接后台成功");
		
		return null;
		
	}
	

}
