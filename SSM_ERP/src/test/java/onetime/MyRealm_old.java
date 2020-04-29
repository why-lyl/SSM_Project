package onetime;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.team.erp.framework.mapper.UserMapper;
import com.team.erp.framework.model.User;

/*
 * 这个MyRealm调用的是UserMapper层的东西来进行验证的，
 * 而新写的是调用Service层来进行验证的，不知道两者是否都能实现验证功能，
 * 为了节约时间，还是就用新的Myrealm了这里等有时间才进行理解了
 */
public class MyRealm_old extends AuthorizingRealm{//AuthorizingRealm抽象类
	
	@Autowired
	private UserMapper um;
    
	/**
	 *shrio授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}
    
	/**
	 *shrio认证
	 *开始登录验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//System.out.println("myrealm被访问了！");
		//1、将AuthenticationToken对象强制装换成令牌类对象
		UsernamePasswordToken myToken = (UsernamePasswordToken)token;
		//2、判断数据库中是否有输入用户名相关的参数
		User user = um.selectUserByUserName(myToken.getUsername());
		if (user != null) {
			//3、准备四大参数
			//(1) 得到身份
			Object principal = myToken.getPrincipal();//一般来说账号就是身份
			//System.out.println(principal);
			//(2)获取hashedCredentials 此处获得的密码需要和令牌里的密码作比对
			String hashedCredentials = user.getPassword();
			//(3)得到加盐方式 -->将这种加盐方式运用到令牌里面的密码加盐
			ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUserName());
			//(4)得到当前realm的名字
			String realmName = this.getName();
			/*
			 * 开始进行shiro认证，将principal和令牌里面的身份进行比对
			 * 将hashedCredentials和令牌里面的密码（进行了md5加密，加密520次，以credentialsSalt进行加盐）比对
			 * 如果都成功比对，则认证成功，否者就抛出异常
			 */
			return new SimpleAuthenticationInfo(principal, hashedCredentials, credentialsSalt, realmName);
		}else {
			//return (AuthenticationInfo) new AuthenticationException();//此为老师讲解，不知道是否确实如此，
			//我使用时总是有异常
			throw new AuthenticationException();//此为网上的参考，不知是否正确，目前无法验证
		}
		
		//return null;
		
	}
	    
}
