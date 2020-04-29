package com.team.erp.framework.mapper;

import com.team.erp.framework.model.User;

/**
 * Description:
 * 此层用来写有关用户的增删改查的接口方法，及方法中的传入参数类型，和返回值类型
 * 方法不外乎增删改查
 * @className UserMapper
 * @author @liuYL
 * @createDate 2020年4月25日
 */

public interface UserMapper {
	
	/**
	 * 查询方法
	 */
	
	User selectUserByUserId(Integer userId); //根据用户id查询用户
	User selectUserByUserName(String userName);//根据用户名字查询用户
	/**
	 * 更新方法
	 */
	
	int updateByPrimaryKeySelective(User record);//根据选择的用户信息更新用户
	int updateByPrimaryKey(User record);//根据主键更新用户信息
	
	/**
	 * 添加方法
	 */
	
	int addUser(User user);//根据用户信息插入用户
	
	/**
	 * 删除方法
	 */
	
	int deleteUserByUserId(Integer userId);//根据用户id删除用户
	int deleteUserByUserName(String userName);//根据用户name删除用户
}
