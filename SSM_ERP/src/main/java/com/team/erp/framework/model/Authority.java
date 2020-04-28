package com.team.erp.framework.model;

import java.io.Serializable;

public class Authority implements Serializable{
	
	private int authorityId;
	private String authorityName;
	private String authorityDesc;
	
	
	//无参构造
	public Authority() {
		super();
	}

	//有参构造
	public Authority(int authorityId, String authorityName, String authorityDesc) {
		super();
		this.authorityId = authorityId;
		this.authorityName = authorityName;
		this.authorityDesc = authorityDesc;
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

	@Override
	public String toString() {
		return "Authority [authorityId=" + authorityId + ", authorityName=" + authorityName + ", authorityDesc="
				+ authorityDesc + "]";
	}
	
	
	
	

}
