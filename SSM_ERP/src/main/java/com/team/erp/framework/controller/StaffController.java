package com.team.erp.framework.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.team.erp.framework.model.Department;
import com.team.erp.framework.model.Staff;
import com.team.erp.framework.model.vo.SearchInfo;
import com.team.erp.util.controller.BaseController;

@Controller
@RequestMapping("/staffController")
public class StaffController extends BaseController{
	
   @RequestMapping("selectStaff.ajax")
   @ResponseBody
	public PageInfo<Staff> showStaff(SearchInfo searchInfo,String userDepart) {
		System.out.println(searchInfo.getCurrentPage());
		System.out.println("到达了职工层");
		PageInfo<Staff> info = ss.selectStaffAll(searchInfo.getCurrentPage());
		System.out.println(info);
		System.out.println(userDepart);
		return info;
	}
   
   @RequestMapping("goStaff.do")
	public String goQueryUser(HttpServletRequest request) {
	    System.out.println("员工查看");
	    List<Department> departments = ds.selectDepartmentAll();
	    request.getSession().setAttribute("departs", departments);
		return "User/userList";
	}
   
   @RequestMapping("goUserEdit.do")
	public String goUserEdit(Staff staff,HttpServletRequest request) {
	    
	    System.out.println("员工编辑");
	    System.out.println(staff); 
	    Staff staffs = ss.selectStaffByStaffId(staff.getStaffId());
	    System.out.println(staffs);
	    request.getSession().setAttribute("sta", staffs);
	    List<Department> departments = ds.selectDepartmentAll();
	    request.getSession().setAttribute("departs", departments);
		return "User/userEdit";
	}
   
   @RequestMapping("goUserAdd.do")
	public String goUserAdd(HttpServletRequest request) {
	    System.out.println("增添员工页面");
	    List<Department> departments = ds.selectDepartmentAll();
	    request.getSession().setAttribute("departs", departments);
	    System.out.println(departments);
		return "User/userAdd";
	}
   
   @RequestMapping("staffAdd.ajax")
   @ResponseBody
  	public int staffAdd(Staff staff,String password,String repassword) {
	    System.out.println("获得的要添加到staff表的值"+staff+password+repassword);
  	    System.out.println("增添员工执行");
  	    int addStaffAll = ss.addStaffAll(staff);
  	    int userAdd = ss.userAdd(staff.getAccountId(), password, repassword);
  	    System.out.println("用户添加成功为1："+ userAdd);
  		return addStaffAll;
  	}
   
   @RequestMapping("isRepeat.ajax")
   @ResponseBody
  	public String isRepeat(String account) {
	    System.out.println("获得前端的账号"+account);
  	    System.out.println("验证账号是否已存在执行");
  	    Staff staff = ss.selectStaffByAccountId(account);
  	    if (staff == null) {
  	    	return "NO";
		}
  	    System.out.println("根据账号查出的staff"+staff);
  		return null;
  	}
   
   @RequestMapping("staffEidt.ajax")
   @ResponseBody
  	public String staffEidt(Staff staff,int staffId,String accountId,String newAccountId,String password) {
	    System.out.println("获得修改员工的值"+staff);
	    System.out.println("获得要修改员工的id:"+staffId);
	    System.out.println("获得修改员工的旧账号"+accountId);
	    System.out.println("获得修改员工的新账号"+newAccountId);
	    System.out.println("获得修改员工的新密码"+password);
	   ss.staffEidt(staff, staffId, accountId, newAccountId, password);
  	    System.out.println("修改员工任务执行");
  		return "SUCCESS";
  	}
   
   @RequestMapping("staffDel.ajax")
   @ResponseBody
  	public String staffDel(int staffId) {
	    ss.deleteStaffAndUser(staffId);
  	    System.out.println("删除员工任务执行");
  		return "SUCCESS";
  	}
  
}
