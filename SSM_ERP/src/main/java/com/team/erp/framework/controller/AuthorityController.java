package com.team.erp.framework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team.erp.util.controller.BaseController;

@Controller
@RequestMapping("/authorityController")
public class AuthorityController extends BaseController{
	
	@RequestMapping("goStaffAuthority.do")
	public String goStaffAuthority() {
		System.out.println("前往职工权限分配页面");
		
		return "Authority/staffAuthr";
		
	}
	@RequestMapping("goDepartsAuthority.do")
	public String goDepartsAuthority() {
		System.out.println("前往部门权限分配页面");
		
		return "Authority/departsAuthr";
		
	}

}
