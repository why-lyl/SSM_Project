package com.team.erp.util.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.team.erp.framework.service.AuthorityService;
import com.team.erp.framework.service.DepartmentService;
import com.team.erp.framework.service.StaffService;
import com.team.erp.framework.service.UserService;

/**
 * Description:
 * 专门为controller层服务
 * 这里注入的service对象可以在controller层进行使用
 * @className BaseController
 * @author @liuYL
 * @createDate 2020年4月26日
 */
public class BaseController {
	//都是引入业务层的对象
	@Autowired
	public UserService us;
	
	@Autowired
	public AuthorityService as;
	
	@Autowired
	public StaffService ss;
	
	@Autowired
	public DepartmentService ds;

}
