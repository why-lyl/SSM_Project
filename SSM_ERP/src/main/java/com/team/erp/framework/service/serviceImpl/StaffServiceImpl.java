package com.team.erp.framework.service.serviceImpl;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.erp.framework.mapper.StaffMapper;
import com.team.erp.framework.mapper.UserMapper;
import com.team.erp.framework.model.Staff;
import com.team.erp.framework.model.User;
import com.team.erp.framework.model.vo.UserAndAuthority;
import com.team.erp.framework.service.StaffService;
import com.team.erp.util.MD5Util;

@Service
public class StaffServiceImpl implements StaffService {
	
	@Autowired
	private StaffMapper sm;
	
	@Autowired
	private UserMapper um;

	@Override
	public PageInfo<Staff> selectStaffAll(int pageNum) {
		System.out.println(pageNum);
		PageHelper ph = new PageHelper();
		ph.startPage(pageNum, 8);//这里修改每页展示的条数
		List<Staff> staffs = sm.selectStaffAll();
		PageInfo<Staff> info = new PageInfo<>(staffs);

		System.out.println(info + "-------------");
		return info;
	}

	@Override
	public Staff selectStaffByStaffId(Integer StaffId) {
		
		return sm.selectStaffByStaffId(StaffId);
	}

	@Override
	public int addStaffAll(Staff Staff) {
		//相当于对mapper层的封装
		return sm.addStaffAll(Staff);
	}

	@Override
	public int updateStaffDpartmentByDepartmentId(int staffId) {
		
		return sm.updateStaffDpartmentByDepartmentId(staffId);
	}

	@Override
	public Staff selectStaffByAccountId(String userName) {
		
		return sm.selectStaffByAccountId(userName);
	}

	@Override
	public int userAdd(String accountId, String password, String repassword) {
		String userPassword = new MD5Util().getPasswordByMD5(password, accountId);//对密码进行加密
		int num = um.addUserByProperty(null,accountId, userPassword);//添加用户，num==1，表示添加用户成功
		if (num == 1) {
			/*初始化权限*/
			User user = um.selectUserByUserName(accountId);
			UserAndAuthority uaa = new UserAndAuthority();
			uaa.setUserId(user.getUserId());
			uaa.setAuthorityId(1); //对应Authority里的值，这里设置的权限是只能访问职工权限
			int num2 = um.addUserAndAuthority(uaa);//添加权限
			 
		
	    }
		return num;

	}

	@Override
	public String staffEidt(Staff staff, int staffId, String accountId, String newAccountId, String password) {
		int num1 = sm.deleteStaffByStaffId(staffId);
		if (num1==1) {
			int num2 = sm.addStaff(staff);
			User user = um.selectUserByUserName(accountId);
			if (user==null) {//staff表中有账户，但是user表中没有相关账户，也要在user表中添加信息
				//只要user为空就要添加
				System.out.println("staff表中有账户,user表中无账户,添加信息到user表执行");
				um.addUserByProperty(null, newAccountId, null);
				sm.updateStaffAccountIdBynewAccountId(accountId, newAccountId);//更新职员信息
				String userPassword = new MD5Util().getPasswordByMD5(password, newAccountId);//对密码进行加密
				um.updateUserByUserName(newAccountId, newAccountId, userPassword);//对应的更新用户表
			}
			
			if(password!=null) {//此种情况为User中存在userName，上面的为User中为空的情况
				System.out.println("修改User表执行");
				sm.updateStaffAccountIdBynewAccountId(accountId, newAccountId);
				String userPassword = new MD5Util().getPasswordByMD5(password, newAccountId);//对密码进行加密
			    um.updateUserByUserName(accountId, newAccountId, userPassword);//对应的更新用户表
				
			}
		}
		return null;
	}
	@Override
	public int deleteStaffAndUser(int staffId) {
		Staff staff = sm.selectStaffByStaffId(staffId);
		System.out.println("删除查询"+staff);
		if (staff.getAccountId()!=null) {
			um.deleteUserByUserName(staff.getAccountId());
		}
		sm.deleteStaffByStaffId(staffId);
		return 0;
	}

	@Override
	public Staff selectStaffByStaffName(String StaffName) {
		
		return sm.selectStaffByStaffName(StaffName);
	}

	@Override
	public PageInfo<Staff> selectStaffBystaffNameL(String staffName, int pageNum) {
		
		System.out.println(pageNum);
		PageHelper ph = new PageHelper();
		ph.startPage(pageNum, 8);//这里修改每页展示的条数
		List<Staff> staffs = sm.selectStaffBystaffNameL(staffName);
		PageInfo<Staff> info = new PageInfo<>(staffs);
		System.out.println(info + "根据名字查出职员");
		return info;
	}

	
}
