package com.team.erp.framework.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.erp.framework.mapper.DepartmentMapper;
import com.team.erp.framework.model.Department;
import com.team.erp.framework.service.DepartmentService;

@Service
public class DepartmentServicImpl implements DepartmentService {
    
	@Autowired
	private DepartmentMapper dm;
	
	@Override
	public List<Department> selectDepartmentAll() {
		
		return dm.selectDepartmentAll();
	}

	@Override
	public Department selectDepartmentByDepartmentId(Integer departmentId) {
		
		return dm.selectDepartmentByDepartmentId(departmentId);
	}

	@Override
	public Department selectDepartmentByDepartmentName(String departmentName) {
		
		return dm.selectDepartmentByDepartmentName(departmentName);
	}

	@Override
	public int addDepartment(Department department) {
		
		return dm.addDepartment(department);
	}

	@Override
	public int deleteDepartmentByDepartmentId(Integer departmentId) {
		
		return dm.deleteDepartmentByDepartmentId(departmentId);
	}

	@Override
	public int updateByPrimaryKey(Department department) {
		
		return dm.updateByPrimaryKey(department);
	}

}
