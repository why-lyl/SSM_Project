package com.team.erp.framework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.team.erp.framework.model.Staff;
import com.team.erp.framework.model.vo.SearchInfo;
import com.team.erp.util.controller.BaseController;

@Controller
@RequestMapping("/staffController")
public class StaffController extends BaseController{
	
   @RequestMapping("selectStaff.ajax")
   @ResponseBody
	public PageInfo<Staff> showStaff(SearchInfo searchInfo) {
		System.out.println(searchInfo.getCurrentPage());
		System.out.println("到达了职工层");
		PageInfo<Staff> info = ss.selectStaffAll(searchInfo.getCurrentPage());
		System.out.println(info);
		return info;
	}
   
   @RequestMapping("goStaff.do")
	public String goQueryUser() {
	    System.out.println("员工查看");
		return "User/userList";
	}
   
   @RequestMapping("goUserEdit.do")
	public String goUserEdit() {
	    System.out.println("员工编辑");
		return "User/userEdit";
	}
   
   @RequestMapping("goUserAdd.do")
	public String goUserAdd() {
	    System.out.println("增添员工");
		return "User/userAdd";
	}
   
}
