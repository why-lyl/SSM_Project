package com.team.erp.framework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/purchaseController")
public class purchaseController {
	
	@RequestMapping("goPurchase.do")//allpurchase_list  userpurchase_list
	public String goPurchase() {
		
		return "Purchase/allpurchase_list";
		
	}

}
