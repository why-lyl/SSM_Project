package com.team.erp.framework.mapper;

import java.util.List;

import com.team.erp.framework.model.Product;

public interface ProductMapper {
	
	/**
	 * 查询方法
	 */
	
	Product selectProductByProductId(Integer productId); //根据产品id查询产品
	Product selectProductByProductName(String productName);//根据产品名字查询产品信息
	List<Product> selectProductAll();//无参数查出产品的所有信息
	
	/**
	 * 更新方法
	 */
	
	int updateByPrimaryKeySelective(Product record);//根据选择的产品信息更新产品
	int updateByPrimaryKey(Product record);//根据主键更新产品信息
	
	/**
	 * 添加方法
	 */
	
	int addProduct(Product product);//插入产品1
	int addProductAll(Product product);//插入产品信息2
	
	/**
	 * 删除方法
	 */
	
	int deleteProductByProductId(Integer productId);//根据产品id删除产品
	int deleteProductByProductName(String productName);//根据产品name删除产品

}


