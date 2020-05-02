package com.team.erp.framework.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.erp.framework.mapper.StaffMapper;
import com.team.erp.framework.model.Staff;
import com.team.erp.framework.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService {
	
	@Autowired
	private StaffMapper sm;

	@Override
	public PageInfo<Staff> selectStaffAll(int pageNum) {
		System.out.println(pageNum);
		PageHelper ph = new PageHelper();
		ph.startPage(pageNum, 8);//这里修改每页展示的条数
		List<Staff> staffs = sm.selectStaffAll();
		PageInfo<Staff> info = new PageInfo<>(staffs);

		System.out.println(info + "-------------");
		return info;
	}

	@Override
	public Staff selectStaffByStaffId(Integer StaffId) {
		
		return sm.selectStaffByStaffId(StaffId);
	}

	@Override
	public int addStaffAll(Staff Staff) {
		//相当于对mapper层的封装
		return sm.addStaffAll(Staff);
	}

	@Override
	public int updateStaffDpartmentByDepartmentId(int staffId) {
		
		return sm.updateStaffDpartmentByDepartmentId(staffId);
	}

}
