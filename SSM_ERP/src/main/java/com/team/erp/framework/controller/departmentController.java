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
	
	@RequestMapping("goOrganization.ajax")
	public String goOrganization() {
		System.out.println("前往部门展示");
		return "Organization/organization-list";
		
	}

}
