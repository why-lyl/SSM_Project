package com.team.erp.framework.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.team.erp.framework.model.Staff;

public interface StaffMapper {
	
	/**
	 * 查询方法
	 */
	Staff selectStaffByStaffId(Integer StaffId); //根据用户id查询用户
	Staff selectStaffByStaffName(String StaffName);//根据用户名字查询用户
	Staff selectStaffByAccountId(String userName);//根据用户名字查询职员（单表查询）
	List<Staff> selectStaffAll();//查询出所有与员工有关的信息
	List<Staff> selectStaffBystaffNameL(@Param("staffName")String staffName);//根据员工名查询出与员工的相关信息
	
	/**
	 * 更新方法
	 */
	
	int updateByPrimaryKeySelective(Staff record);//根据选择的用户信息更新用户
	int updateByPrimaryKey(Staff record);//根据主键更新用户信息
	//String departmentName,(暂定)
	int updateStaffDpartmentByDepartmentId(int staffId);//根据职工id更新职工信息
	int updateStaffAccountIdBynewAccountId(@Param("accountId")String accountId,@Param("newAccountId")String newAccountId);//根据旧账号更新新账号
	/**
	 * 添加方法
	 */
	int addStaff(Staff Staff);//根据用户信息插入用户
	int addStaffAll(Staff Staff);//根据用户信息插入员工所有信息
	int addStaffAccountIdByUserId(int userId);//根据用户id插入职工账号，注册初始化操作时使用
	
	/**
	 * 删除方法
	 */
	int deleteStaffByStaffId(int StaffId);//根据用户id删除用户
	int deleteStaffByStaffName(String StaffName);//根据职员name删除用户
	int deleteStaffByAccountId(String AccountId);//根据职员账号删除用户

}
