package com.team.erp.framework.service;

import com.github.pagehelper.PageInfo;

import com.team.erp.framework.model.Staff;

public interface StaffService {
	
	PageInfo<Staff> selectStaffAll(int pageNum);
	
	Staff selectStaffByStaffId(Integer StaffId);
	
	int addStaffAll(Staff Staff);
	
	int updateStaffDpartmentByDepartmentId(int staffId);
	
}
