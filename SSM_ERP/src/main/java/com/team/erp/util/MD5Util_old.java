package com.team.erp.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5Util_old {
	
	        //模拟将数据库里面的密码进行加密
			public static void main(String[] args) {
				//加密方式 
				String algorithmName = "MD5";
				//原密码
				Object source = "123";
				//设置加盐方式
				ByteSource salt = ByteSource.Util.bytes("yl");
				//设置加密多少次
				int hashIterations = 520;
				SimpleHash shash = new SimpleHash(algorithmName, source, salt, hashIterations);
				System.out.println(shash);
			}

}
