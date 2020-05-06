package com.team.erp.framework.model;

import java.io.Serializable;
import java.util.List;

import com.team.erp.framework.model.vo.DepartmentAndAuthority;

public class Authority implements Serializable{
	
	private int authorityId;
	private String authorityName;
	private String authorityDesc;
	private List<DepartmentAndAuthority> daa;
	
	//无参构造
	public Authority() {
		super();
	}

	//有参构造
	public Authority(int authorityId, String authorityName, String authorityDesc, List<DepartmentAndAuthority> daa) {
		super();
		this.authorityId = authorityId;
		this.authorityName = authorityName;
		this.authorityDesc = authorityDesc;
		this.daa = daa;
	}

	public int getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(int authorityId) {
		this.authorityId = authorityId;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public String getAuthorityDesc() {
		return authorityDesc;
	}

	public void setAuthorityDesc(String authorityDesc) {
		this.authorityDesc = authorityDesc;
	}

	public List<DepartmentAndAuthority> getDaa() {
		return daa;
	}

	public void setDaa(List<DepartmentAndAuthority> daa) {
		this.daa = daa;
	}

	@Override
	public String toString() {
		return "Authority [authorityId=" + authorityId + ", authorityName=" + authorityName + ", authorityDesc="
				+ authorityDesc + ", daa=" + daa + "]";
	}

	
	
	
	

}
