package com.team.erp.framework.service;

import com.github.pagehelper.PageInfo;
import com.team.erp.framework.model.Purchase;

public interface PurchaseService {
	
	PageInfo<Purchase> selectPurchaseAll(int pageNum);
	
	//根据Id更新采购表里面的申请状态为同意
	int updatePurchaseByPurchaseIdAgree(int purchaseId,String applyStatus);
	
	//根据Id更新采购表里面的申请状态为不采纳
	int updatePurchaseByPurchaseIdDisagree(int purchaseId,String applyStatus);
	
	int addPurchase(Purchase purchase);//根据采购信息信息插入采购信息
}
