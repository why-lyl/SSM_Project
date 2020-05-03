package com.team.erp.framework.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.erp.framework.mapper.DepartmentMapper;
import com.team.erp.framework.model.Department;
import com.team.erp.framework.service.DepartmentService;

@Service
public class DepartmentServicImpl implements DepartmentService {
    
	@Autowired
	private DepartmentMapper dm;
	
	@Override
	public List<Department> selectDepartmentAll() {
		
		return dm.selectDepartmentAll();
	}

}
