package com.team.erp.framework.service.serviceImpl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.team.erp.framework.mapper.StaffMapper;
import com.team.erp.framework.mapper.UserMapper;
import com.team.erp.framework.model.Staff;
import com.team.erp.framework.model.User;
import com.team.erp.framework.model.vo.UserAndAuthority;
import com.team.erp.framework.service.UserService;
import com.team.erp.util.MD5Util;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper um;
	
	@Autowired
	private StaffMapper sm;
    /**
     * 登录认证
     */
	@Override
	public String checkLogin(String username, String password, String selectionBox, HttpServletRequest request,HttpServletResponse response) {
		// shiro拿到当前用户
		Subject subject = SecurityUtils.getSubject();
		//System.out.println(subject);
		//将用户名操作保存在sesssion里
		subject.getSession().setAttribute("USERNAME", username);
		
		//进行认证
		//拿到令牌(将账号和密码放到令牌里面)
		//此处因为在sping-shiro配置文件中配置了对密码的加密方式和加密次数。所以会对传进去的密码进行加密
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		//System.out.println("是加密后的密码吗？"+token);
		//System.out.println("是加密后的密码吗？"+token.getPassword());
		token.setRememberMe(true);
		try {
			//认证过程放在try里面 认证失败会抛出异常 去自定义的realm类里面认证 
			subject.login(token);//因为spring-shrio.xml配置，所以会到自定义的realm去认证
			//登录成功
			//此刻对Cookie做处理
			/* 为了完成记住密码的功能需要将用户名和密码保存到cookie里面
			 * 注意事项:shiro已经将servlet里面的cookie屏蔽了，所以说要用shiro封装好的cookie
			 */
			SimpleCookie c1 = new SimpleCookie();
			SimpleCookie c2 = new SimpleCookie();
			SimpleCookie c3 = new SimpleCookie();
			c1.setName("USERID"); //设置名字
			c2.setName("NEWPASSWORD"); //设置名字
			c3.setName("SELECTIONBOX");//设置登录页面复选框的名字
		    //String s = String.valueOf(3);//将int类型的值转换为String类型的值
			String userId = String.valueOf(um.selectUserByUserName(username).getUserId());//将int类型的值转换为String类型的值
			c1.setValue(userId);
			c2.setValue(username + password);
			
			//设置cookie的储存时间，这里是以秒为单位的
			c1.setMaxAge(60*60*3); //60秒 60分钟 3小时
			c2.setMaxAge(60*60*3); 
			c3.setMaxAge(60*60*3); 
			
			if (selectionBox.equals("YES")) {
//				SimpleCookie c4 = new SimpleCookie();
//				SimpleCookie c5 = new SimpleCookie();
//				
//				c4.setName("USERNAME"); //设置名字
//				c5.setName("PASSWORD");
//				
//				//设置值
//				c4.setValue(username); //设置值为传入的用户名
//			//  c2.setValue(password); //设置值为传入的明文密码(非数据库里面储存的密码)
//				c5.setValue(um.selectUserByUserName(username).getPassword());//设置值为加密密码(数据库里面储存的密码)
//				//设置cookie的储存时间
//				c4.setMaxAge(0); //60秒 60分钟 3小时
//				c5.setMaxAge(0);
//				
//				//回写给浏览器
//				c4.saveTo(request, response);
//				c5.saveTo(request, response);
				
				//因为是根据selectionBox的值来判断的，所以要分情况
				c3.setValue(selectionBox);
				//回写给浏览器
				c1.saveTo(request, response);
				c2.saveTo(request, response);
				c3.saveTo(request, response);
			}else {//此种情况为selectionBox.equals("NO")的情况，为了记住密码的展示功能而设计
				
				c3.setValue(selectionBox);
				
				//回写给浏览器
				c1.saveTo(request, response);
				c2.saveTo(request, response);
				c3.saveTo(request, response);
			}
//			 String parameter = request.getParameter("username");
//			 request.setAttribute("user",  parameter);
//			 System.out.println( parameter);
			//System.out.println("登录成功");
			return "SUCCESS";
			
		} catch (AuthenticationException e) {
			// 登录失败
			//System.out.println("登录失败");
			return "ERROR";
		}
		//return null;
	}
	
	/**
     * 注册认证
     */
	@Override
	public String checkRegister(String username, String password, String repassword) {
		System.out.println(username + password + repassword);
		User temp = um.selectUserByUserName(username);//通过用户名得到用户
		if (temp != null) {//因为有用户名才能查出，所以可以判断是否有用户名
			System.out.println("存在相同的用户名");
			return "REPEATNAME";//ERROR1
		}else if(!password.equals(repassword)){
			System.out.println("两次密码不一致");
			return "DIFFERENTPASSWARD";//ERROR2
		}
		String userPassword = new MD5Util().getPasswordByMD5(password, username);
		//System.out.println(userPassword);
		//System.out.println(username);
		int num = um.addUserByProperty(null,username, userPassword);//添加用户，num==1，表示添加用户成功
		//System.out.println(num);
		if (num == 1) {
			/*初始化权限*/
			User user = um.selectUserByUserName(username);
			UserAndAuthority uaa = new UserAndAuthority();
			uaa.setUserId(user.getUserId());
			uaa.setAuthorityId(1); //对应Authority里的值
			int num2 = um.addUserAndAuthority(uaa);//添加权限
			int num3 =  sm.addStaffAccountIdByUserId(user.getUserId());
			if (num3 == 1) {
				Staff staff = sm.selectStaffByAccountId(user.getUserName());
				sm.updateStaffDpartmentByDepartmentId(staff.getStaffId());
			}
			if (num2 != 1 ) {
				return "FAILDAUT";
			}
		}else {
			return "FAILDAUT";
		}
		Subject subject = SecurityUtils.getSubject();// shiro拿到当前用户
		//将用户名操作保存在sesssion里，页面展示时需要用到
		subject.getSession().setAttribute("USERNAME", username);
		//进行认证
		//拿到令牌(将账号和密码放到令牌里面)
		//此处因为在sping-shiro配置文件中配置了对密码的加密方式和加密次数。所以会对传进去的密码进行加密
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			//认证过程放在try里面 认证失败会抛出异常 去自定义的realm类里面认证 
			subject.login(token);//因为spring-shrio.xml配置，所以会到自定义的realm去认证
			return "SUCCESS";
		} catch (AuthenticationException e) {
			return "ERROR";
		}
	}
	
	/**
     * 查询指定的cookie
     */
	@Override
	public String queryCookie(HttpServletRequest request, HttpServletResponse response) {
		User user = new User();
		Gson gson = new Gson();
		
		//1、拿到所有的cookie对象 -->通过request
		Cookie[] cookies = request.getCookies();
		
		//2、遍历数组
		/*
		 * //返回值只能返回一个，所以这次尝试失败 if(cookies!=null) { for (Cookie cookie : cookies) {
		 * if(cookie.getName().equals("SELECTIONBOX")){ String selectionBox =
		 * cookie.getValue(); String selectionBoxs = gson.toJson(selectionBox);
		 * System.out.println(selectionBoxs); return selectionBoxs; } }
		 * 
		 * }
		 */
		
		if (cookies!=null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("USERID")) {
					//um.selectUserByUserId(Integer.parseInt(cookie.getValue())).getUserName() 通过id获得用户名
					//Integer.parseInt()将String类型转化为int类
					//下面一行代码是设置用户名
					//System.out.println("想要获得的cookie储存的值" + cookie.getValue());
					user.setUserName(um.selectUserByUserId(Integer.parseInt(cookie.getValue())).getUserName());
					String username = user.getUserName();//设置用户名
					//System.out.println(username);
					//此处的user只设置了userName，所以打印的值为
					//User [userId=0, userName=cpa, password=null, createdate=null]
					//因此在此处加上userId的设置
					user.setUserId(Integer.parseInt(cookie.getValue()));//此处为设置userId
					//System.out.println(user);
					if(cookie.getName().equals("SELECTIONBOX")){
						String selectionBox = cookie.getValue();
						String selectionBoxs = gson.toJson(selectionBox);
						System.out.println(selectionBoxs);
						return selectionBoxs;
					}
				}
			}
		}
		
		if (cookies!=null) {
			for (Cookie cookie : cookies) {
			    if (cookie.getName().equals("NEWPASSWORD")) {
			    	//System.out.println("对密码的分离执行");
					//获得cookie中NEWPASSWORD的值
					String newPassword = cookie.getValue();
					//System.out.println(newPassword);
					//将真正的密码分割出来 使用字符串substring方法
					//下列代码是将获得的newPassword从username的长度开始隔断，只剩下password部分，（还需要在理解）
					String password = newPassword.substring(user.getUserName().length());
					//对分理出的密码进行设置，将密码传入user中，然后才进行json字符串的转换
					user.setPassword(password);
					
				}
			}
		}
		//System.out.println(user);
		
		if (user.getUserName()!=null) {
			//将这个对象转换为json字符串，使用gson
			String userString = gson.toJson(user);
			System.out.println(userString);
		    userString = userString.replace("}", ",");
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("SELECTIONBOX")){
					String selectionBox =cookie.getValue(); 
					//将SELECTIONBOX加入到user的json对象中,这里所写是为了写为
					userString = userString +"\"" + "selectionBox"+"\"" +":" +"\""+selectionBox+"\"" + "}";
				    System.out.println(userString);
				   // return userString + selectionBoxs;
				}
			}
			return userString;
		}else {
			User userNull = new User(0,"","",null,"") ;
			String userString = gson.toJson(user);
			System.out.println(userString);
			return userString;
		}
		
			
		//return null;
	}

	@Override
	public User selectUserByUserName(String userName) {
		
		return um.selectUserByUserName(userName);
	}

}
