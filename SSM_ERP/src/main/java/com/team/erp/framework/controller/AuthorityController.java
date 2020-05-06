package com.team.erp.framework.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.erp.framework.model.Authority;
import com.team.erp.framework.model.Department;
import com.team.erp.framework.model.vo.DepartmentAndAuthority;
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
	public String goDepartsAuthority(HttpServletRequest request) {
		System.out.println("前往部门权限分配页面");
		
		List<Department> department = ds.selectDepartmentAll();
		request.getSession().setAttribute("departments", department);
		System.out.println("获得的部门信息"+department);
		return "Authority/departsAuthr";
		
	}
	
	@RequestMapping("departsAuthorityEidt.do")
	public String changeDepartmentAuthority(int departmentId,HttpServletRequest request) {
		System.out.println("获得的需要修改权限的部门Id是:"+departmentId);
		Department department = ds.selectDepartmentByDepartmentId(departmentId);
		request.getSession().setAttribute("department", department);
		List<Authority> authorities = as.selectAuthorityAll();
		request.getSession().setAttribute("AllAuthorities", authorities);
		List<DepartmentAndAuthority> authoritylist = as.selectDAABydepartmentId(departmentId);
		
		request.getSession().setAttribute("authrs", authoritylist);
		System.out.println("得到部门权限中间表信息:"+authoritylist);
		return "Authority/departsAuthrEidt";
		
	}
	
	@RequestMapping("departmentAuthorityEidt.ajax")
	@ResponseBody//value="authorityId",required = false 在这里很重要，少了前面一个会无法接收参数且报错，少了后面一个则会导致无法传空值的情况
	public String departmentAuthorityEidt(@RequestParam(value="authorityId",required = false)List<String> authorityId,int departmentId) {
		System.out.println("接收到部门权限修改时的相关信息");
		System.out.println("接收到有关部门与权限中间表的信息"+authorityId+departmentId);
		if (authorityId==null) {
			System.out.println("部门权限选择为空时");
			return "SUCCESS";
		}
	    //String string = authorityId.get(2);//需要注意的是取集合里的参数时不能超过它的范围
	    //System.out.println(string);
		int num1 = as.deleteDAABydepartmentId(departmentId);//首先删除部门原有的所有权限
		System.out.println("删除部门相关权限成功时为:"+num1);
		if (num1==1) {
			System.out.println("删除部门有关权限成功");
		}
		for (int i = 0; i <=authorityId.size()-1; i++) {
			int autId = Integer.parseInt(authorityId.get(i));//因为是通过String集合获得的值是String类型，所以这里要强制转换为int类型
			int num2 = as.addDepartmentAndAuthorityByProperty(departmentId, autId);
	}
		return "SUCCESS";
		
	}

}
