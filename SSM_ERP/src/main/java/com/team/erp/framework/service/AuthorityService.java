package com.team.erp.framework.service;

import java.util.List;

import com.team.erp.framework.model.Authority;
import com.team.erp.framework.model.vo.DepartmentAndAuthority;

public interface AuthorityService {

	Authority selectAuthorityByAuthoritById(Integer AuthorityId);
	List<Authority> selectAuthoritysByUserName(String userName);
	List<Authority> selectAuthorityAll();
	List<DepartmentAndAuthority> selectDAAAll();//查得DAA中间表的所有信息
	List<DepartmentAndAuthority> selectDAABydepartmentId(int departmentId);//通过departmentId查得DAA中间表的相关信息
	int deleteDAABydepartmentId(int departmentId);//根据departmentId删除部门与权限的中间表信息
	int addDepartmentAndAuthorityByProperty(int departmentId,int authorityId);
}
