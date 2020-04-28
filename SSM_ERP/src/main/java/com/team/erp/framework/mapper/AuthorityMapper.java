package com.team.erp.framework.mapper;

import java.util.List;

import com.team.erp.framework.model.Authority;



/**
 * Description:
 * @className AuthorityMapper
 * @author @liuYL
 * @createDate 2020年4月28日
 */
public interface AuthorityMapper {
	
	/**
	 * 查询方法
	 */
	
	Authority selectAuthorityByAuthoritById(Integer AuthorityId);//根据AuthorityId查询数据
	Authority selectAuthorityByAuthoritByName(Integer AuthorityName);//根据AuthorityName查询数据
	List<Authority> selectAuthorityAll();//用一个集合将装Authority的信息装下
	/**
	 * 更新方法
	 */
	
	int updateByPrimaryKeySelective(Authority record);//根据选择的权限主键更新权限信息
	int updateByPrimaryKey(Authority record);//根据权限主键更新权限信息
	
	/**
	 * 添加方法
	 */
	
	int addAuthority(Authority authority);//根据传入的权限信息插入权限信息
	
	/**
	 * 删除方法
	 */
	
	int deleteAuthorityByAuthorityId(Integer authorityId);//根据权限id删除权限
	int deleteAuthorityByAuthorityName(String authorityName);//根据权限name删除权限

}
