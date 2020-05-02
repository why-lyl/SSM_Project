package com.team.erp.framework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/departmentController")
public class departmentController {
	
	@RequestMapping("goDepartment.do")
	public String goDepartment() {
		System.out.println("前往部门展示");
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
		return "Department/departsUpdate";
	}
}
