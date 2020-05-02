package com.team.erp.framework.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
	public String goUserEdit(Staff staff,HttpServletRequest request) {
	    
	    System.out.println("员工编辑");
	    System.out.println(staff); 
	    Staff staffs = ss.selectStaffByStaffId(staff.getStaffId());
	    System.out.println(staffs);
	    request.getSession().setAttribute("sta", staffs);
		return "User/userEdit";
	}
   
   @RequestMapping("goUserAdd.do")
	public String goUserAdd() {
	    System.out.println("增添员工页面");
		return "User/userAdd";
	}
   
   @RequestMapping("staffAdd.ajax")
   @ResponseBody
  	public int staffAdd(Staff staff) {
	    System.out.println("获得的要添加到staff表的值"+staff);
  	    System.out.println("增添员工执行");
  	    int addStaffAll = ss.addStaffAll(staff);
  		return addStaffAll;
  	}
   
}
