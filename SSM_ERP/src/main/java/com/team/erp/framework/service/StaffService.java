package com.team.erp.framework.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import com.team.erp.framework.model.Staff;

public interface StaffService {
	
	PageInfo<Staff> selectStaffAll(int pageNum);
	
	Staff selectStaffByStaffId(Integer StaffId);
	
	Staff selectStaffByAccountId(String userName);
	
	int addStaffAll(Staff Staff);
	
	int updateStaffDpartmentByDepartmentId(int staffId);
	
	int userAdd(String username,String password,String repassword);
	
	String staffEidt(Staff staff, int staffId, String accountId, String newAccountId, String password);
	
	int deleteStaffAndUser(int staffId);
	
	Staff selectStaffByStaffName(String StaffName);//根据用户名字查询用户
	
	PageInfo<Staff> selectStaffBystaffNameL(String staffName,int pageNum);//注意mapper.xml里面的名字不能重复，否者会报错

}