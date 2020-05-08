package com.team.erp.framework.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.team.erp.framework.model.Purchase;

public interface PurchaseMapper {
	
	/** 查询方法在下====================================================================*/
	Purchase selectPurchaseByPurchaseId(Integer PurchaseId); //根据采购商品id查询采购商品
	Purchase selectPurchaseByPurchaseName(String PurchaseName);//根据采购商品名字查询采购商品
	List<Purchase> selectPurchaseAll();//查询出所有与采购商品有关的信息
	List<Purchase> selectPurchaseBypurchaseNameL(@Param("purchaseName")String purchaseName);//根据采购商品名查询出与采购商品的相关信息
	/** 查询方法在上====================================================================*/
	

	/** 更新方法在下====================================================================*/
	int updateByPrimaryKeySelective(Purchase record);//根据选择的采购信息主键更新采购信息
	int updateByPrimaryKey(Purchase record);//根据主键更新采购信息
	/** 更新方法在上====================================================================*/
	
	
	/**添加方法在下====================================================================*/
	int addPurchase(Purchase purchase);//根据采购信息信息插入采购信息
	int addPurchaseAll(Purchase purchase);//根据采购信息信息插入采购单所有信息
	/**添加方法在上====================================================================*/
	
	
	/** 删除方法在下====================================================================*/
	int deletePurchaseByPurchaseId(int purchaseId);//根据采购信息id删除采购信息
	int deletePurchaseByPurchaseName(String purchaseName);//根据采购信息name删除采购信息
	/** 删除方法在上====================================================================*/

}
