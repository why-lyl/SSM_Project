package com.team.erp.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.team.erp.framework.mapper.TestMapper;

/**
 * Description:
 * 测试mybatis和spring是否配置成功
 * @className TestMapper
 * @author @liuYL
 * @createDate 2020年4月23日
 */

//加上相关注解表示spring测试
@RunWith(SpringJUnit4ClassRunner.class)
//扫描相关xml

@ContextConfiguration({"classpath:spring.xml"})
public class TestMapperSpring {
	
	//自动注入接口对象以便于测试
	@Autowired
	private TestMapper tm;
	
	@Test
	public void m() {
		//ibatis是没有mybatis的前身
		//System.out.println(tm);//org.apache.ibatis.binding.MapperProxy@78641d23 说明是TestMapper的代理对象
		System.out.println(tm.getUserNameByUserId(2));
		System.out.println(tm.getUserNameByUserId(1));
	}

}
