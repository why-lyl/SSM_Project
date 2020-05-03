package com.team.erp.framework.service;

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

}