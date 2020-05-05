package com.team.erp.framework.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.erp.framework.model.Department;
import com.team.erp.util.controller.BaseController;

@Controller
@RequestMapping("/departmentController")
public class departmentController extends BaseController{
	
	@RequestMapping("goDepartment.do")
	public String goDepartment(HttpServletRequest request) {
		System.out.println("前往部门展示");
		List<Department> departments = ds.selectDepartmentAll();
		request.getSession().setAttribute("Departments", departments);
		return "Department/departList";
		
	}
	
	@RequestMapping("goOrganization.do")
	public String goOrganization() {
		System.out.println("前往组织展示");
		return "Organization/organization-list";
		
	}
	
	@RequestMapping("goDepartAdd.do")
	public String goDepartAdd() {
		System.out.println("前往部门添加");
		return "Department/departsAdd";
	}
	
	@RequestMapping("departmentEidt.do")
	public String departmentEidt(int departmentId,HttpServletRequest request) {
		System.out.println("前往部门修改");
		Department department = ds.selectDepartmentByDepartmentId(departmentId);
		request.getSession().setAttribute("department", department);
		return "Department/departsEidt";
	}
	

	@RequestMapping("isRepeatDepart.ajax")
	@ResponseBody
	public String isRepeatDepart(String departmentName) {
		System.out.println("拿到的部门名"+departmentName);
		System.out.println("前往部门查重");
		Department department = ds.selectDepartmentByDepartmentName(departmentName);
		System.out.println("部门信息"+department);
		if (department!=null) {
			return "YES";
		}else {
			return "NO";
		}
		
	}
	@RequestMapping("departmentAdd.ajax")
	@ResponseBody
	public String departmentAdd(Department department) {
		System.out.println("拿到的部门信息"+department);
		System.out.println("部门添加操作执行");
		ds.addDepartment(department);
		return null;
	}
	
	@RequestMapping("departmentEidt.ajax")
	@ResponseBody
	public String departmentEidt(Department department) {
		System.out.println("修改时拿到的部门信息"+department);
		System.out.println("部门修改操作执行");
		int updateByPrimaryKey = ds.updateByPrimaryKey(department);
		if (updateByPrimaryKey==1) {
			System.out.println("单表的部门更新成功");
		}
		return null;
	}
	
	@RequestMapping("delDepartment.ajax")
	@ResponseBody
	public String delDepartment(int departmentId) {
		System.out.println("拿到的部门Id"+departmentId);
		System.out.println("部门删除操作执行");
		int delDepartment = ds.deleteDepartmentByDepartmentId(departmentId);
		if(delDepartment==1) {
			System.out.println("单表删除部门信息成功");
		}
		return null;
	}
}
