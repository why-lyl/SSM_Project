package com.team.erp.framework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.team.erp.framework.model.Department;
import com.team.erp.framework.model.Product;
import com.team.erp.framework.model.vo.SearchInfo;
import com.team.erp.util.controller.BaseController;

@Controller
@RequestMapping("/inventoryController")
public class InventoryController extends BaseController{
	
	@RequestMapping("goAllInventory.do")
	public String goAllInventory() {
		System.out.println("前往库存展示页面");
		return "Inventory/allinventory_list";
		
	}
	
	@RequestMapping("selectProduct.ajax")
	@ResponseBody
	public PageInfo<Product> selectProduct(SearchInfo searchInfo) {
		System.out.println("获得库存分页参数，完成分页");
		System.out.println(searchInfo.getCurrentPage());
		PageInfo<Product> info = ps.selectProductAll(searchInfo.getCurrentPage());
		System.out.println(info);
		return info;		
	}
	
	@RequestMapping("goAddProduct.do")
	public String goAddProduct() {
		
		return "Inventory/inventory_add";
			
	}

}
