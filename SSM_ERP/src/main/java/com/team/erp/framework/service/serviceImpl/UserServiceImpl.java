package com.team.erp.framework.service.serviceImpl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.team.erp.framework.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    /**
     * 登录认证
     */
	@Override
	public String checkLogin(String username, String password, String selectionBox) {
		// shiro拿到当前用户
		Subject subject = SecurityUtils.getSubject();
		//进行认证
		//拿到令牌(将账号和密码放到令牌里面)
		//此处因为在sping-shiro配置文件中配置了对密码的加密方式和加密次数。所以会对传进去的密码进行加密
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			//认证过程放在try里面 认证失败会抛出异常 去自定义的realm类里面认证
			subject.login(token);//因为spring-shrio.xml配置，所以会到自定义的realm去认证
		} catch (AuthenticationException e) {
			// TODO: handle exception
		}
		return null;
	}

}
