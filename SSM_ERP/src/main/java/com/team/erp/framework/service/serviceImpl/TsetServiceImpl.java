package com.team.erp.framework.service.serviceImpl;

import org.springframework.stereotype.Service;

import com.team.erp.framework.service.TestService;

@Service
public class TsetServiceImpl implements TestService {

	public void DemoTest() {
		System.out.println("service被正常调用");
		
	}

}
