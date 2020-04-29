package com.team.erp.framework.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

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
		
		/*
		 * 用req的方法获得的信息
		 */
		String userIp = req.getRemoteAddr();// 拿到来访者的IP地址
		String serverName = req. getServerName();//获得服务器的名字  
		String localIp = req.getLocalAddr();//获得服务器ip
		String userName = req.getRemoteUser();//获取当前缓存的用户，比如Spring Security做权限控制后就会将用户登录名缓存到这里
		//int serverPort = req.getServerPort();//获得服务器端口
		
	    Date date = new Date();//获得时间
	    SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");//格式化时间
	    //System.out.println("date=" + dateFormat.format(date.getTime()));
	    String formatDate = dateFormat.format(date.getTime());
		/*
		 * 用req的方法设置获得的信息的名字，好用于EL表达式
		 */
		
		req.setAttribute("userIp", userIp);//引号里的是自定义的名，EL表达式需要使用
		req.setAttribute("serverName", serverName);//后面的是变量名
		req.setAttribute("localIp", localIp);
		req.setAttribute("userName", userName);
		req.setAttribute("LOGINTIME", formatDate);
		req.setAttribute("name", "看不见我");
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
