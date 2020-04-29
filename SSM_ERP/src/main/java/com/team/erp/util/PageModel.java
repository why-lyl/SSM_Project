package com.team.erp.util;

import java.util.List;

import com.google.gson.Gson;
import com.team.erp.framework.model.Staff;

public class PageModel {
	private String total;
	private List<Staff> data;
	
	public static String toJson(Object object) {
		return new Gson().toJson(object);
	}
	
	
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public List<Staff> getList() {
		return data;
	}
	public void setList(List<Staff> data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "PageModel [total=" + total + ", data=" + data + "]";
	}
	
	public PageModel() {
		super();
	}
	
	public PageModel(String total,List<Staff> data) {
		super();
		this.total = total;
		this.data = data;
	}
	
}