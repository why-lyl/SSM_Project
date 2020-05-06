package com.team.erp.framework.model.vo;

import java.io.Serializable;
import java.util.List;

public class DepartmentAndAuthority implements Serializable{
	
	private int departmentAuthorityId;
	private int departmentId;
	private int authorityId;
	private List<DepartmentAndAuthority> daa;
	public DepartmentAndAuthority() {
		super();
	}
	public DepartmentAndAuthority(int departmentAuthorityId, int departmentId, int authorityId,
			List<DepartmentAndAuthority> daa) {
		super();
		this.departmentAuthorityId = departmentAuthorityId;
		this.departmentId = departmentId;
		this.authorityId = authorityId;
		this.daa = daa;
	}
	public int getDepartmentAuthorityId() {
		return departmentAuthorityId;
	}
	public void setDepartmentAuthorityId(int departmentAuthorityId) {
		this.departmentAuthorityId = departmentAuthorityId;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public int getAuthorityId() {
		return authorityId;
	}
	public void setAuthorityId(int authorityId) {
		this.authorityId = authorityId;
	}
	public List<DepartmentAndAuthority> getDaa() {
		return daa;
	}
	public void setDaa(List<DepartmentAndAuthority> daa) {
		this.daa = daa;
	}
	@Override
	public String toString() {
		return "DepartmentAndAuthority [departmentAuthorityId=" + departmentAuthorityId + ", departmentId="
				+ departmentId + ", authorityId=" + authorityId + ", daa=" + daa + "]";
	}
	
	
}
