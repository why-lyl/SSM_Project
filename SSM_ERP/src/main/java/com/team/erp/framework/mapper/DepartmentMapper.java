package com.team.erp.framework.mapper;

import java.util.List;

import com.team.erp.framework.model.Department;

public interface DepartmentMapper {
	
	/**
	 * 查询方法
	 */
	
	Department selectDepartmentByDepartmentId(Integer DepartmentId); //根据部门id查询部门
	Department selectDepartmentByDepartmentName(String DepartmentName);//根据部门名字查询部门信息
	List<Department> selectDepartmentAll();//无参数查出部门的所有信息
	
	/**
	 * 更新方法
	 */
	
	int updateByPrimaryKeySelective(Department record);//根据选择的部门信息更新部门
	int updateByPrimaryKey(Department record);//根据主键更新部门信息
	
	/**
	 * 添加方法
	 */
	
	int addDepartment(Department Department);//插入部门1
	int addDepartmentAll(Department Department);//插入部门信息2
	
	/**
	 * 删除方法
	 */
	
	int deleteDepartmentByDepartmentId(Integer DepartmentId);//根据部门id删除部门
	int deleteDepartmentByDepartmentName(String DepartmentName);//根据部门name删除部门

}
