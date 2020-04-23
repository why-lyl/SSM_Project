package com.team.erp.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.team.erp.framework.service.TestService;

/**
 * Description:
 * 测试Service层
 * @className TestServiceSSM
 * @author @liuYL
 * @createDate 2020年4月23日
 */

//加上相关注解表示spring测试
@RunWith(SpringJUnit4ClassRunner.class)
//扫描相关xml
@ContextConfiguration({"classpath:spring.xml"})

public class TestServiceSSM {
	
	//自动注入接口对象以便于测试
		@Autowired
		private TestService ts;
		
		@Test
		public void DemoTest() {
			ts.DemoTest();
		}

}
