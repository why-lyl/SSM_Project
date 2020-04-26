package com.team.erp.framework.mapper;


/**
 * Description:
 * 测试Mapper
 * 此层用来写方法的定义，及需要传入的参数定义
 * @className TestMapper
 * @author @liuYL
 * @createDate 2020年4月23日
 */
public interface TestMapper {
	
	//根据用户id查找用户姓名
	String getUserNameByUserId(int userId);

}
