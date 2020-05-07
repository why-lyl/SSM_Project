package com.team.erp.framework.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.erp.framework.model.Authority;
import com.team.erp.framework.model.Department;
import com.team.erp.framework.model.Staff;
import com.team.erp.framework.model.User;
import com.team.erp.framework.model.vo.DepartmentAndAuthority;
import com.team.erp.framework.model.vo.UserAndAuthority;
import com.team.erp.util.controller.BaseController;

@Controller
@RequestMapping("/authorityController")
public class AuthorityController extends BaseController{
	
	@RequestMapping("staffAuthorityEidt.do")
	public String staffAuthorityEidt(int staffId,HttpServletRequest request,String departmentName) {
		System.out.println("前往职工权限修改页面");
		System.out.println("职工权限分配时接收到的职工Id"+staffId+departmentName);
		Staff staff = ss.selectStaffByStaffId(staffId);
		System.out.println("得到的Staff信息:"+staff);
	    String accountId = staff.getAccountId();//得到账号名字,其实可以直接通过前端页面val.accountId得到
	    if (accountId != null) {//防止报user为空的异常
	    	 User user = us.selectUserByUserName(accountId);
	    	 if (user!= null) {//防止报userId为空的异常
	    		 int userId = user.getUserId();
		    	 List<UserAndAuthority> UAA = as.selectUAAByuserId(userId);//上面的几行代码都是为查出UAA中间表服务的
		    	 request.getSession().setAttribute("UAA", UAA);
			}
	    } 
		Department department = ds.selectDepartmentByDepartmentName(departmentName);
		System.out.println("得到的部门信息"+department);
		int departmentId = department.getDepartmentId();
		List<DepartmentAndAuthority> DAA= as.selectDAABydepartmentId(departmentId);
		System.out.println("获得的部门权限中间表"+DAA);
		List<Authority> authorityAll = as.selectAuthorityAll();
		request.getSession().setAttribute("staff", staff);
		request.getSession().setAttribute("DAA", DAA);
		request.getSession().setAttribute("Authorities", authorityAll);
		
		return "Authority/staffAuthrEidt";
		
	}
	
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
			System.out.println("部门权限选择为空");
		}
	    //String string = authorityId.get(2);//需要注意的是取集合里的参数时不能超过它的范围
	    //System.out.println(string);
		int num1 = as.deleteDAABydepartmentId(departmentId);//首先删除部门原有的所有权限
		System.out.println("删除部门相关权限成功时为:"+num1);
		for (int i = 0; i <=authorityId.size()-1; i++) {
			int autId = Integer.parseInt(authorityId.get(i));//因为是通过String集合获得的值是String类型，所以这里要强制转换为int类型
			int num2 = as.addDepartmentAndAuthorityByProperty(departmentId, autId);
			System.out.println("修改部门权限得到的值:"+num2);
	}
		return "SUCCESS";
	
	}
	
	@RequestMapping("staffAuthorityEidt.ajax")
	@ResponseBody
	public String staffAuthorityEidt(@RequestParam(value="authorityId",required = false)List<String> authorityId, String accountId,HttpServletRequest request) {
		System.out.println("接收到职工权限修改时的相关信息");
		System.out.println("接收到职工的账号"+accountId);
		User user = us.selectUserByUserName(accountId);//因为在mapper.xml中设置了外键的封装，所以传回的值，有些多
		System.out.println("得到的复选框信息是:"+authorityId);
		if (user==null) {//通过账户名查得的user信息，为空则表示未有这个用户名(账号名),所以要添加账户
			System.out.println("user表为空");
			//想法:在user表中插入一个Id，然后进行后面的操作，还是要添加上对应的userName，否者无法添加对应的账号
			int addUser = us.addUserByProperty(accountId);//添加userId，无其它属性
			if (addUser == 1) {
				System.out.println("添加userId及userName成功");
			}
		}
		int userId = user.getUserId();
		//System.out.println("得到的用户为:"+user+userId);
        List<UserAndAuthority> UAA = as.selectUAAByuserId(userId);
        System.out.println("得到的用户与权限的中间表为:"+UAA);
        if (UAA!= null) {
        	int num = as.deleteUAAByUserId(userId);
    			System.out.println("删除用户权限成功"+num);
    			for (int i = 0; i <=authorityId.size()-1; i++) {
    				int autId = Integer.parseInt(authorityId.get(i));//因为是通过String集合获得的值是String类型，所以这里要强制转换为int类型
    				as.addUserAndAuthorityByProperty(userId, autId);
    				//int num2 = as.addDepartmentAndAuthorityByProperty(departmentId, autId);
    			}
		}
        
        
		return "SUCCESS";
		
	}

}
