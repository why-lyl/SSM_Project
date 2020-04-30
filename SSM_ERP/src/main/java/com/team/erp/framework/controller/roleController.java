package com.team.erp.framework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/roleController")
public class roleController {
	
	@RequestMapping("goUserRole.do")
	public String goUserRole() {
	    System.out.println("职工权限查看");
		return "Authority/userAuthr";
	}
	
	@RequestMapping("userRoleEidt.do")
	public String userRoleEidt() {
	    System.out.println("职工权限修改");
		return "Authority/userAuthrEidt";
	}
	
	@RequestMapping("goDepartsRole.do")
	public String goDepartsRole() {
	    System.out.println("部门权限");
		return "Authority/departsAuthr";
	}
	
	@RequestMapping("departsRoleEidt.do")
	public String departsRoleEidt() {
	    System.out.println("部门权限");
		return "Authority/departsAuthrEidt";
	}

}
