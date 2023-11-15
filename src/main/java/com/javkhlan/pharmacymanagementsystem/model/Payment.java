package com.javkhlan.pharmacymanagementsystem.model;

public class Payment {
	private int paymentID;
	private String invoiceNo;
	private double amount;
	private String paymentMethod;
	private String serialNo;

	public Payment(int paymentID, String invoiceNo, double amount, String paymentMethod, String serialNo) {
		this.paymentID = paymentID;
		this.invoiceNo = invoiceNo;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.serialNo = serialNo;
	}

	public Payment(String invoiceNo, double amount, String paymentMethod, String serialNo) {
		this.invoiceNo = invoiceNo;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.serialNo = serialNo;
	}

	public int getPaymentID() {
		return paymentID;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	@Override
	public String toString() {
		return "PaymentModel{" + "paymentID=" + paymentID + ", invoiceNo=" + invoiceNo + ", amount=" + amount
				+ ", paymentMethod='" + paymentMethod + '\'' + ", serialNo='" + serialNo + '\'' + '}';
	}
}
