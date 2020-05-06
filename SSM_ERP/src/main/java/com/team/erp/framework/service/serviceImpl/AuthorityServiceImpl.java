package com.team.erp.framework.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.erp.framework.mapper.AuthorityMapper;
import com.team.erp.framework.model.Authority;
import com.team.erp.framework.model.vo.DepartmentAndAuthority;
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

}
