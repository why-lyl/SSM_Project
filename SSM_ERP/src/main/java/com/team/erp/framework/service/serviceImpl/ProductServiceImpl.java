package com.team.erp.framework.service.serviceImpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.erp.framework.mapper.ProductMapper;
import com.team.erp.framework.model.Product;
import com.team.erp.framework.model.Staff;
import com.team.erp.framework.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductMapper pm;

	@Override
	public String showProuduct(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<Product> selectProductAll(int pageNum) {
		System.out.println("获得的库存商品当前页数为"+pageNum);
		PageHelper ph = new PageHelper();
		ph.startPage(pageNum,8);//这里修改每页展示的条数
		List<Product> products = pm.selectProductAll();
		PageInfo<Product> info = new PageInfo<>(products);
		System.out.println(info + "库存商品信息");
		return info;
	}

}
