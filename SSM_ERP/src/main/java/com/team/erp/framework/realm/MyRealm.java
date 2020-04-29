package com.team.erp.framework.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.team.erp.framework.model.Authority;
import com.team.erp.framework.model.User;
import com.team.erp.framework.service.AuthorityService;
import com.team.erp.framework.service.UserService;

/*
 * MyRealm的配置步骤
 * 1、首先注入要用到的Service层
 * 2、授权
 * 
 * 3、认证
 * 1) 将AuthenticationToken对象强制装换成令牌类对象
 * 2) 判断数据库中是否有输入用户名相关的参数配置认证的密码
 * 3) 准备四大参数
 * 4) 开始进行shiro认证
 * 
 */
public class MyRealm extends AuthorizingRealm{
	
	@Autowired
	private UserService us;
	
	@Autowired
	private AuthorityService as;
	
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("执行授权操作");
		//System.out.println(principals.getPrimaryPrincipal()); 
		//principals.getPrimaryPrincipal()得到身份-->一般身份都是账号
		//给每个登录成功用户授权（基于角色）
		Set<String> roles = new HashSet<String>();
		//查询出登录用户的所有具备的角色(调用业务层来查)
		List<Authority> authorities = as.selectAuthoritysByUserName((String)(principals.getPrimaryPrincipal()));
		//所有权限加到set集合里面
		for (Authority authority : authorities) {
			roles.add(authority.getAuthorityName());
		}
		//授权
		return new SimpleAuthorizationInfo(roles);
		//return null;
	}
	
	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		//1、将AuthenticationToken对象强制装换成令牌类对象
	    UsernamePasswordToken myToken = (UsernamePasswordToken)token;
	   
	    //2、判断数据库中是否有输入用户名相关的参数配置认证的密码
  		User user = us.selectUserByUserName(myToken.getUsername());
  	    System.out.println("myrealm被访问了！");
  		//System.out.println(myToken.getUsername());//通过令牌获得名字
  		//System.out.println(user);
  	   
  		if (user != null) {
			//3、准备四大参数
  		    //(1) 得到身份
  		 	 Object principal = myToken.getPrincipal();//一般来说账号就是身份
			// System.out.println(principal);
			//(2)获取hashedCredentials 此处获得的密码需要和令牌里的密码作比对
			String hashedCredentials = user.getPassword();
			//(3)得到加盐方式 -->将这种加盐方式运用到令牌里面的密码加盐
			ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUserName());
			//(4)得到当前realm的名字
			String realmName = this.getName();
			//System.out.println(realmName);
			/*
			 * 开始进行shiro认证，将principal和令牌里面的身份进行比对
			 * 将hashedCredentials和令牌里面的密码（进行了md5加密，加密520次，以credentialsSalt进行加盐）比对
			 * 如果都成功比对，则认证成功，否者就抛出异常
			 */
			return new SimpleAuthenticationInfo(principal, hashedCredentials, credentialsSalt, realmName);
		}else{
  			throw new AuthenticationException();
  		}
	}
	
	
	

}
