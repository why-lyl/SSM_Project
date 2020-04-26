package com.team.erp.framework.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.erp.util.controller.BaseController;

@Controller
@RequestMapping("/userController")
public class UserController extends BaseController{
	
	@RequestMapping("/checkLogin.ajax")
	@ResponseBody
	public String checkLogin(String username, String password, String selectionBox, HttpServletRequest request, HttpServletResponse response) {
     // System.out.println("连接后台成功");
		
     //	System.out.println(username +" "+ password +" "+ selectionBox);
		//System.out.println(us);
		//调用业务层来完成登录验证
		/*
		 * 多态的条件:
		 * 1、必须要有继承或者实现
		 * 2、必须要有方法重写
		 * 3、父类引用指向子类对象或者接口引用指向实现类对象
		 */
		String info = us.checkLogin(username,password,selectionBox, request, response);//接口引用指向实现类对象
		return info;
	}
	
	@RequestMapping("index.do")
	public String goIndex(HttpServletRequest req, HttpServletResponse response) {
        //返回至主页面
		return "index";
	}
	
	@RequestMapping("loginOut.do")
	public String loginOut(HttpServletRequest req, HttpServletResponse response) {
        //退出登录
		Subject subject = SecurityUtils.getSubject();//得到当前用户
		subject.logout();//退出登录
		return "login";
	}
	@RequestMapping("goLogin.do")
	public String goLogin(HttpServletRequest req, HttpServletResponse response) {
        //跳转登录页面
		return "login";
	}
	
	@RequestMapping("goWelcome.do")
	public String goWelcome(HttpServletRequest req, HttpServletResponse response) {
        //跳转展示页面
		//准备一些服务器信息......用req 
		
		return "welcome";
	}
}
