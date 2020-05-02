package com.team.erp.framework.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Staff implements Serializable{
	//精确到秒的时间装换格式("yyyy-MM-dd  hh:mm:ss")
	private int staffId;
	private String staffName;
	private String staffSex;
	private int staffAge;
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date staffBirthday;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date staffJoin;
	private String staffTel;
	private String staffEmail;
	private String staffDepart;
	private String accountId;
	
	
	//无参构造
	public Staff() {
		super();
	}
	
	//有参构造
	
	public Staff(int staffId, String staffName, String staffSex, int staffAge, Date staffBirthday, String staffTel,
			String staffEmail, String staffDepart, Date staffJoin, String accountId) {
		super();
		this.staffId = staffId;
		this.staffName = staffName;
		this.staffSex = staffSex;
		this.staffAge = staffAge;
		this.staffBirthday = staffBirthday;
		this.staffTel = staffTel;
		this.staffEmail = staffEmail;
		this.staffDepart = staffDepart;
		this.staffJoin = staffJoin;
		this.accountId = accountId;
	}
	
	public int getStaffId() {
		return staffId;
	}

	

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffSex() {
		return staffSex;
	}

	public void setStaffSex(String staffSex) {
		this.staffSex = staffSex;
	}

	public int getStaffAge() {
		return staffAge;
	}

	public void setStaffAge(int staffAge) {
		this.staffAge = staffAge;
	}

	public Date getStaffBirthday() {
		return staffBirthday;
	}

	public void setStaffBirthday(Date staffBirthday) {
		this.staffBirthday = staffBirthday;
	}

	public String getStaffTel() {
		return staffTel;
	}

	public void setStaffTel(String staffTel) {
		this.staffTel = staffTel;
	}

	public String getStaffEmail() {
		return staffEmail;
	}

	public void setStaffEmail(String staffEmail) {
		this.staffEmail = staffEmail;
	}
    
	
	public String getStaffDepart() {
		return staffDepart;
	}

	public void setStaffDepart(String staffDepart) {
		this.staffDepart = staffDepart;
	}

	public Date getStaffJoin() {
		return staffJoin;
	}

	public void setStaffJoin(Date staffJoin) {
		this.staffJoin = staffJoin;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	@Override
	public String toString() {
		return "Staff [staffId=" + staffId + ", staffName=" + staffName + ", staffSex=" + staffSex + ", staffAge="
				+ staffAge + ", staffBirthday=" + staffBirthday + ", staffTel=" + staffTel + ", staffEmail="
				+ staffEmail + ", staffDepart=" + staffDepart + ", staffJoin=" + staffJoin + ", accountId=" + accountId
				+ "]";
	}

}
