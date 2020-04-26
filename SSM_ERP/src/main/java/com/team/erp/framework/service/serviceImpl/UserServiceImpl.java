package com.team.erp.framework.service.serviceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.erp.framework.mapper.UserMapper;
import com.team.erp.framework.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper um;
    /**
     * 登录认证
     */
	@Override
	public String checkLogin(String username, String password, String selectionBox, HttpServletRequest request,HttpServletResponse response) {
		// shiro拿到当前用户
		Subject subject = SecurityUtils.getSubject();
		
		//将用户名操作保存在sesssion里
		subject.getSession().setAttribute("USERNAME", username);
		
		//进行认证
		//拿到令牌(将账号和密码放到令牌里面)
		//此处因为在sping-shiro配置文件中配置了对密码的加密方式和加密次数。所以会对传进去的密码进行加密
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(true);
		try {
			//认证过程放在try里面 认证失败会抛出异常 去自定义的realm类里面认证 
			subject.login(token);//因为spring-shrio.xml配置，所以会到自定义的realm去认证
			//登录成功
			//此刻对Cookie做处理
			/* 为了完成记住密码的功能需要将用户名和密码保存到cookie里面
			 * 注意事项:shiro已经将servlet里面的cookie屏蔽了，所以说要用shiro封装好的cookie
			 */
			if (selectionBox.equals("YES")) {
//				SimpleCookie c1 = new SimpleCookie();
//				SimpleCookie c2 = new SimpleCookie();
//				
//				c1.setName("USERNAME"); //设置名字
//				c2.setName("PASSWORD");
//				
//				//设置值
//				c1.setValue(username); //设置值为传入的用户名
//			//  c2.setValue(password); //设置值为传入的明文密码(非数据库里面储存的密码)
//				c2.setValue(um.selectUserByUserName(username).getPassword());//设置值为加密密码(数据库里面储存的密码)
//				//设置cookie的储存时间
//				c1.setMaxAge(60*60*3); //60秒 60分钟 3小时
//				c2.setMaxAge(60*60*3);
//				
//				//回写给浏览器
//				c1.saveTo(request, response);
//				c2.saveTo(request, response);
				
				SimpleCookie c1 = new SimpleCookie();
				c1.setName("USERID"); //设置名字
			    //String s = String.valueOf(3);//将int类型的值转换为String类型的值
				String userId = String.valueOf(um.selectUserByUserName(username).getUserId());//将int类型的值转换为String类型的值
				c1.setValue(userId);
				//设置cookie的储存时间
				c1.setMaxAge(60*60*3); //60秒 60分钟 3小时
				//回写给浏览器
				c1.saveTo(request, response);
				
			}
			
			
			//System.out.println("登录成功");
			return "SUCCESS";
			
		} catch (AuthenticationException e) {
			// 登录失败
			//System.out.println("登录失败");
			return "ERROR";
		}
		//return null;
	}

	

}
