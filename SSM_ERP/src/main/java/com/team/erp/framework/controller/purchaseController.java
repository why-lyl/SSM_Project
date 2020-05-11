package com.team.erp.framework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.team.erp.framework.model.Purchase;
import com.team.erp.framework.model.vo.SearchInfo;
import com.team.erp.util.controller.BaseController;

@Controller
@RequestMapping("/purchaseController")
public class purchaseController extends BaseController{
	
	@RequestMapping("goPurchase.do")//allpurchase_list  userpurchase_list
	public String goPurchase() {
		
		return "Purchase/allpurchase_list";
		
	}
	
	@RequestMapping("goApply.do")
	public String goApply() {
		//System.out.println("进入申请层");
		return "Apply/allapply_list";
		
	}
	
	@RequestMapping("goPurchaseAdd.do")
	public String goPurchaseAdd() {
		System.out.println("进入申请层");
		return "Purchase/purchase_add";
		
	}
	
	@RequestMapping("selectPurchase.ajax")
	@ResponseBody
	public PageInfo<Purchase> selectPurchase(SearchInfo searchInfo){
		
		System.out.println("获得采购商品分页参数，完成分页");
		System.out.println(searchInfo.getCurrentPage());
		PageInfo<Purchase> info = prs.selectPurchaseAll(searchInfo.getCurrentPage());
		System.out.println(info + "采购商品信息");
		return info;
	}
	
	@RequestMapping("purchaseAdd.ajax")
	@ResponseBody
	public String purchaseAdd(Purchase purchase){
		
		System.out.println("添加采购商品申请信息");
		System.out.println("获得的采购信息如下:"+ purchase);
		prs.addPurchase(purchase);
		return null;
	}
	
	@RequestMapping("agreeApply.ajax")
	@ResponseBody
	public String agreeApply(String applyStatus){
		System.out.println("同意获得的值为"+applyStatus);
		int one = applyStatus.lastIndexOf(",");//取的逗号所在的索引值
		String applyStatu = applyStatus.substring(0, one);//逗号前面的字符串
		String purchaseIds = applyStatus.substring((one+1),applyStatus.length());//逗号后面的字符串
		int purchaseId = Integer.parseInt(purchaseIds);
		prs.updatePurchaseByPurchaseIdAgree(purchaseId, applyStatu);//同意操作
		return null;
	}
	
	@RequestMapping("disagreeApply.ajax")
	@ResponseBody
	public String disagreeApply(String applyStatus){
		System.out.println("同意获得的值为"+applyStatus);
		int one = applyStatus.lastIndexOf(",");//取的逗号所在的索引值
		String applyStatu = applyStatus.substring(0, one);//逗号前面的字符串
		System.out.println(applyStatu);
		String purchaseIds = applyStatus.substring((one+1),applyStatus.length());//逗号后面的字符串
		int purchaseId = Integer.parseInt(purchaseIds);
		prs.updatePurchaseByPurchaseIdDisagree(purchaseId, applyStatu);//不采纳操作
		return null;
	}

}
