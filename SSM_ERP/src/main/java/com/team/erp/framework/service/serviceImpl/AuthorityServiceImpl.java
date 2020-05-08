package com.team.erp.framework.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.erp.framework.mapper.AuthorityMapper;
import com.team.erp.framework.mapper.UserMapper;
import com.team.erp.framework.model.Authority;
import com.team.erp.framework.model.User;
import com.team.erp.framework.model.vo.DepartmentAndAuthority;
import com.team.erp.framework.model.vo.UserAndAuthority;
import com.team.erp.framework.service.AuthorityService;
/*
 * ServiceImpl层的编写
 * 1) 加入注解@Service
 * 2) 注入需要用到的Mapper,记得加入注解@Autowired，返回值时会用到
 * 3) 重写方法，这里好像就是写了一下返回值
 */

/* 关于接口的一些理解
 * 1号的selectAuthorityByAuthoritById名是继承自Service层的名字,可以在Service层修改名字
 * 2号的selectAuthorityByAuthoritById名是Mapper层的方法接口名，可以在Mapper层进行修改，
 * 这也是为什么要注入Mapper的原因吧
 * 这二者的名字之所以一样是因为方便吧，可以直接复制粘贴，也方便阅读，
 * 实际上，这二者的名字是可以不一样的
 */
@Service
public class AuthorityServiceImpl implements AuthorityService{
	
	@Autowired
	private AuthorityMapper am;
	
	@Autowired
	private UserMapper um;
	
	@Override
	public Authority selectAuthorityByAuthoritById(Integer AuthorityId) {//1号
		
		return am.selectAuthorityByAuthoritById(AuthorityId);//2号
	}

	@Override
	public List<Authority> selectAuthorityAll() {
		
		return am.selectAuthorityAll();
	}

	@Override
	public List<Authority> selectAuthoritysByUserName(String string) {
		
		return am.selectAuthoritysByUserName(string);
	}

	@Override
	public List<DepartmentAndAuthority> selectDAAAll() {
		
		return am.selectDAAAll();
	}

	@Override
	public List<DepartmentAndAuthority> selectDAABydepartmentId(int departmentId) {
		
		return am.selectDAABydepartmentId(departmentId);
	}

	@Override
	public int deleteDAABydepartmentId(int departmentId) {
		
		return am.deleteDAABydepartmentId(departmentId);
	}

	@Override
	public int addDepartmentAndAuthorityByProperty(int departmentId, int authorityId) {
		
		return am.addDepartmentAndAuthorityByProperty(0, departmentId, authorityId);
	}

	@Override
	public List<UserAndAuthority> selectUAAByuserId(Integer userId) {
		
		return am.selectUAAByuserId(userId);
	}

	@Override
	public int deleteUAAByUserId(int userId) {
		
		return am.deleteUAAByUserId(userId);
	}

	@Override
	public int addUserAndAuthorityByProperty(int userId, int authorityId) {
		
		return am.addUserAndAuthorityByProperty(0,userId, authorityId);
	}

	@Override
	public User isUserNull(List<String> authorityId, String accountId) {
		User user = um.selectUserByUserName(accountId);
		System.out.println("得到的复选框信息是:"+authorityId);
		if (user==null) {//通过账户名查得的user信息，为空则表示未有这个用户名(账号名),所以要添加账户
			System.out.println("user表为空");
			//想法:在user表中插入一个Id，然后进行后面的操作，还是要添加上对应的userName，否者无法添加对应的账号
			int addUser = um.addUserByProperty(null,accountId,null);//添加userId，无其它属性
			if (addUser != 0) {
				System.out.println("添加userId及userName成功");
				User userNew = um.selectUserByUserName(accountId);
				return userNew;
			}
		}
		return user;
	}

	@Override
	public String disAut(List<String> authorityId, String accountId) {
		// TODO Auto-generated method stub
		return null;
	}

}
