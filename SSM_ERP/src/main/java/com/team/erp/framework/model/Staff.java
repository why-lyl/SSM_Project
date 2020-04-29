package com.team.erp.framework.model;

import java.util.Date;

public class Staff {
	
	private int staffId;
	private String staffName;
	private String staffSex;
	private int staffAge;
	private Date staffBirthday;
	private String staffTel;
	private String staffEmail;
	
	//无参构造
	public Staff() {
		super();
	}
	
	//无生日构造
	public Staff(int staffId, String staffName, String staffSex, int staffAge, String staffTel, String staffEmail) {
		super();
		this.staffId = staffId;
		this.staffName = staffName;
		this.staffSex = staffSex;
		this.staffAge = staffAge;
		this.staffTel = staffTel;
		this.staffEmail = staffEmail;
	}
    
	//有生日构造
	public Staff(int staffId, String staffName, String staffSex, int staffAge, Date staffBirthday, String staffTel,
			String staffEmail) {
		super();
		this.staffId = staffId;
		this.staffName = staffName;
		this.staffSex = staffSex;
		this.staffAge = staffAge;
		this.staffBirthday = staffBirthday;
		this.staffTel = staffTel;
		this.staffEmail = staffEmail;
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

	@Override
	public String toString() {
		return "Staff [staffId=" + staffId + ", staffName=" + staffName + ", staffSex=" + staffSex + ", staffAge="
				+ staffAge + ", staffBirthday=" + staffBirthday + ", staffTel=" + staffTel + ", staffEmail="
				+ staffEmail + "]";
	}
	
}
