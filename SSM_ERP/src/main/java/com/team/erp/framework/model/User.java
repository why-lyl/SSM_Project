package com.team.erp.framework.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Description:
 * 用户实体类，手写的话要根据表来写
 * @className User
 * @author @liuYL
 * @createDate 2020年4月25日
 */
public class User implements Serializable{//序列化可以将对象储存在硬盘上
	private int userId;
	private String userName;
	private String password;
	private Date createdate;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	//无参构造方法
	public User() {
		super();
	}
	//包含日期的构造方法
	public User(int userId, String userName, String password) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
	}
	//不包含日期的构造方法
	public User(int userId, String userName, String password, Date createdate) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.createdate = createdate;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", createdate="
				+ createdate + "]";
	} 
	
	

}
