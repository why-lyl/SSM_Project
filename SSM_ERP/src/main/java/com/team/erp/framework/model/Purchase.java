package com.team.erp.framework.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Purchase implements Serializable{//进行序列化
	private int purchaseId;
	private String purchaseName;
	private String purchaseStaff;
	private String purchaseSort;
	private String purchaseAmount;
	private double purchasePrice;
	private double purchaseTotal;
	private String applyStatus;
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date applyTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date purchaseTime;
	public Purchase() {
		super();
	}
	public Purchase(int purchaseId, String purchaseName, String purchaseStaff, String purchaseSort,
			String purchaseAmount, double purchasePrice, double purchaseTotal, String applyStatus, Date applyTime,
			Date purchaseTime) {
		super();
		this.purchaseId = purchaseId;
		this.purchaseName = purchaseName;
		this.purchaseStaff = purchaseStaff;
		this.purchaseSort = purchaseSort;
		this.purchaseAmount = purchaseAmount;
		this.purchasePrice = purchasePrice;
		this.purchaseTotal = purchaseTotal;
		this.applyStatus = applyStatus;
		this.applyTime = applyTime;
		this.purchaseTime = purchaseTime;
	}
	public int getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}
	public String getPurchaseName() {
		return purchaseName;
	}
	public void setPurchaseName(String purchaseName) {
		this.purchaseName = purchaseName;
	}
	public String getPurchaseStaff() {
		return purchaseStaff;
	}
	public void setPurchaseStaff(String purchaseStaff) {
		this.purchaseStaff = purchaseStaff;
	}
	public String getPurchaseSort() {
		return purchaseSort;
	}
	public void setPurchaseSort(String purchaseSort) {
		this.purchaseSort = purchaseSort;
	}
	public String getPurchaseAmount() {
		return purchaseAmount;
	}
	public void setPurchaseAmount(String purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}
	public double getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public double getPurchaseTotal() {
		return purchaseTotal;
	}
	public void setPurchaseTotal(double purchaseTotal) {
		this.purchaseTotal = purchaseTotal;
	}
	public String getApplyStatus() {
		return applyStatus;
	}
	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public Date getPurchaseTime() {
		return purchaseTime;
	}
	public void setPurchaseTime(Date purchaseTime) {
		this.purchaseTime = purchaseTime;
	}
	@Override
	public String toString() {
		return "Purchase [purchaseId=" + purchaseId + ", purchaseName=" + purchaseName + ", purchaseStaff="
				+ purchaseStaff + ", purchaseSort=" + purchaseSort + ", purchaseAmount=" + purchaseAmount
				+ ", purchasePrice=" + purchasePrice + ", purchaseTotal=" + purchaseTotal + ", applyStatus="
				+ applyStatus + ", applyTime=" + applyTime + ", purchaseTime=" + purchaseTime + "]";
	}
	
	

}
