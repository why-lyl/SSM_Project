package com.team.erp.util.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.team.erp.framework.service.UserService;

/**
 * Description:
 * 专门为controller层服务
 * @className BaseController
 * @author @liuYL
 * @createDate 2020年4月26日
 */
public class BaseController {
	//都是引入业务层的对象
	@Autowired
	public UserService us;

}
