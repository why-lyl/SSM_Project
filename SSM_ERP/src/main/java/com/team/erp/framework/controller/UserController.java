package com.team.erp.framework.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.erp.framework.model.User;
import com.team.erp.framework.model.vo.Result;
import com.team.erp.util.controller.BaseController;

@Controller
@RequestMapping("/userController")
public class UserController extends BaseController{
	
	@RequestMapping("/checkLogin.ajax")
	@ResponseBody
	public String checkLogin(String username, String password, String selectionBox, HttpServletRequest request, HttpServletResponse response) {
      System.out.println("连接后台成功");
		
     //	System.out.println(username +" "+ password +" "+ selectionBox);
		//System.out.println(us);
		//调用业务层来完成登录验证
		/*
		 * 多态的条件:
		 * 1、必须要有继承或者实现
		 * 2、必须要有方法重写
		 * 3、父类引用指向子类对象或者接口引用指向实现类对象
		 */
        if (username!=null) {//不知道为什么验证了两次，刷新后显示的用户名就不在了，因此这样写,原来刷新就会重新验证，现在不会了
        	String info = us.checkLogin(username,password,selectionBox, request, response);//接口引用指向实现类对象
    		System.out.println("后台返回的"+info);
    		System.out.println("后台的用户名"+username);
    		return info;
		}
		return null;
		    
	}
	
	@RequestMapping("index.do")
	public String goIndex() {
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
	public String goLogin() {
        //跳转登录页面
		return "login";
	}
	
	@RequestMapping("goRegister.do")
	public String goRegister() {
        //跳转注册页面
		return "register";
	}
	
	@RequestMapping("checkRegister.ajax")
	@ResponseBody
	public String checkRegister(String username, String password,String repassword) {
        //返回注册页面的值
		String info = us.checkRegister(username,password,repassword);//接口引用指向实现类对象
		return info;
	}
	
	@RequestMapping("goWelcome.do")
	public String goWelcome(HttpServletRequest request, HttpServletResponse response) {
		//跳转展示页面
		//准备一些服务器信息......用request
		us.showWelcome(request, response);
		return "welcome";
	}
	
	@RequestMapping("/queryCookie.ajax")
	@ResponseBody
	public String queryCookie(HttpServletRequest request, HttpServletResponse response) {
		
		//查询是否有指定的cookie
		String info = us.queryCookie(request, response);
		return info;
	}
	
//	@RequestMapping("/getServerTime.ajax")
//	@ResponseBody
//	public String getServerTime(HttpServletRequest request, HttpServletResponse response) {
//		
//			Date date=new Date();
//			SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");//格式化时间
//		    String formatDate = dateFormat.format(date.getTime());
//			
//			
//			return formatDate;
//		}
	
	
}
