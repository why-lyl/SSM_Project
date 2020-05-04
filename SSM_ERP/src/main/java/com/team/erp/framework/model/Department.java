package com.team.erp.framework.model;

import java.io.Serializable;

public class Department implements Serializable{
	private int departmentId;
	private String departmentName;
	private String departmentDesc;
	private String authrs;
	public Department() {
		super();
	}
	public Department(int departmentId, String departmentName, String departmentDesc, String authrs) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.departmentDesc = departmentDesc;
		this.authrs = authrs;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDepartmentDesc() {
		return departmentDesc;
	}
	public void setDepartmentDesc(String departmentDesc) {
		this.departmentDesc = departmentDesc;
	}
	public String getAuthrs() {
		return authrs;
	}
	public void setAuthrs(String authrs) {
		this.authrs = authrs;
	}
	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", departmentName=" + departmentName + ", departmentDesc="
				+ departmentDesc + ", authrs=" + authrs + "]";
	}
	
	
	

}
