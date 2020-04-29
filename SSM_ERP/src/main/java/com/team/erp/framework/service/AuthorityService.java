package com.team.erp.framework.service;

import java.util.List;

import com.team.erp.framework.model.Authority;

public interface AuthorityService {

	Authority selectAuthorityByAuthoritById(Integer AuthorityId);
	List<Authority> selectAuthoritysByUserName(String userName);
	List<Authority> selectAuthorityAll();
	
}
