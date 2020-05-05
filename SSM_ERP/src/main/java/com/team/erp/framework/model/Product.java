package com.team.erp.framework.model;

import java.io.Serializable;

public class Product implements Serializable{
	private int productId;
	private String productName;
	private String productType;
	private String productSort;
	private String productAmount;
	private double productPrice;
	private double productTotal;
	public Product() {
		super();
	}
	public Product(int productId, String productName, String productType, String productSort, String productAmount,
			double productPrice, double productTotal) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productType = productType;
		this.productSort = productSort;
		this.productAmount = productAmount;
		this.productPrice = productPrice;
		this.productTotal = productTotal;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductSort() {
		return productSort;
	}
	public void setProductSort(String productSort) {
		this.productSort = productSort;
	}
	public String getProductAmount() {
		return productAmount;
	}
	public void setProductAmount(String productAmount) {
		this.productAmount = productAmount;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public double getProductTotal() {
		return productTotal;
	}
	public void setProductTotal(double productTotal) {
		this.productTotal = productTotal;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productType=" + productType
				+ ", productSort=" + productSort + ", productAmount=" + productAmount + ", productPrice=" + productPrice
				+ ", productTotal=" + productTotal + "]";
	}
	

}
