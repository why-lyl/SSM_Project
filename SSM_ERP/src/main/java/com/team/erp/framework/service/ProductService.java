package com.team.erp.framework.service;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.PageInfo;
import com.team.erp.framework.model.Product;

public interface ProductService {
	
	 String showProuduct(HttpServletRequest request);
	 
	 PageInfo<Product> selectProductAll(int pageNum);

}
