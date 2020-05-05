package com.team.erp.framework.service;

import java.util.List;


import com.team.erp.framework.model.Department;

public interface DepartmentService {
	
	Department selectDepartmentByDepartmentId(Integer departmentId); //根据部门id查询部门
	
	Department selectDepartmentByDepartmentName(String departmentName);//根据部门名字查询部门信息
	
	List<Department> selectDepartmentAll();
	
	int addDepartment(Department department);
	
	int deleteDepartmentByDepartmentId(Integer departmentId);//根据部门id删除部门
	
	int updateByPrimaryKey(Department department);//根据主键更新部门信息

}
