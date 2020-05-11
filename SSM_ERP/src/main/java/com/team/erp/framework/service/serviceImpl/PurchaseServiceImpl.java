package com.team.erp.framework.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.erp.framework.mapper.PurchaseMapper;
import com.team.erp.framework.model.Purchase;
import com.team.erp.framework.service.PurchaseService;

@Service
public class PurchaseServiceImpl implements PurchaseService{
	
	@Autowired
	private PurchaseMapper prm;
	
	@Override
	public PageInfo<Purchase> selectPurchaseAll(int pageNum) {
		System.out.println("获得的采购商品当前页数为"+pageNum);
		PageHelper ph = new PageHelper();
		ph.startPage(pageNum,8);//这里修改每页展示的条数
		List<Purchase> products = prm.selectPurchaseAll();
		PageInfo<Purchase> info = new PageInfo<>(products);
		System.out.println(info + "采购商品信息");
		return info;
	}

	@Override
	public int updatePurchaseByPurchaseIdAgree(int purchaseId, String applyStatus) {
		
		return prm.updatePurchaseByPurchaseIdAgree(purchaseId, applyStatus);
	}

	@Override
	public int updatePurchaseByPurchaseIdDisagree(int purchaseId, String applyStatus) {
		
		return prm.updatePurchaseByPurchaseIdDisagree(purchaseId, applyStatus);
	}

	@Override
	public int addPurchase(Purchase purchase) {
		
		return prm.addPurchase(purchase);
	}

}
