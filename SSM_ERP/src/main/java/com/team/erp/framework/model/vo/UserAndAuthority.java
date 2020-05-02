package com.team.erp.framework.model.vo;

import java.io.Serializable;

public class UserAndAuthority implements Serializable{
	
	private int userAuthorityId;
	private int userId;
	private int authorityId;
	
	public UserAndAuthority() {
		super();
	}

	public UserAndAuthority(int userAuthorityId, int userId, int authorityId) {
		super();
		this.userAuthorityId = userAuthorityId;
		this.userId = userId;
		this.authorityId = authorityId;
	}

	public int getUserAuthorityId() {
		return userAuthorityId;
	}

	public void setUserAuthorityId(int userAuthorityId) {
		this.userAuthorityId = userAuthorityId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(int authorityId) {
		this.authorityId = authorityId;
	}

	@Override
	public String toString() {
		return "UserAndAuthority [userAuthorityId=" + userAuthorityId + ", userId=" + userId + ", authorityId="
				+ authorityId + "]";
	}
	
}
