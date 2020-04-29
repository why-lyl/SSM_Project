package com.team.erp.framework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description:
 * 测试controller层
 * @className TestController
 * @author @liuYL
 * @createDate 2020年4月23日
 */

//加上注解表示这是控制层
@Controller
//指定访问该控制层的路由（差不多就是路径的意思，待加深理解）
@RequestMapping("/testController")
public class TestController {
	/**
	 * @ResponseBody理解:返回给接口时，需要添加次注解，返回给页面时，则不需要
	 * 谁发送了请求就把结果返回给谁-->浏览器发送了请求就把结果返回给浏览器，ajax发送了请求就把结果返回给ajax
	 * (有待再理解)
	 */
	//给每个方法指定对应的路由就可访问该方法
	@RequestMapping("/test.do")
	@ResponseBody 
	public String Test() {
		return "Come on,little boy";
		
	}
}