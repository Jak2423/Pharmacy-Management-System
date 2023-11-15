package com.javkhlan.pharmacymanagementsystem.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Prescription {
	private static final AtomicInteger invoiceCount = new AtomicInteger(1000);
	private int presNo;
	private String invoiceNo;
	private String customerID;
	private String customerName;
	private int age;
	private String gender;
	private String address;
	private String date;
	private String drug;
	private String phone;
	private String strength;
	private String dose;
	private int quantity;

	public Prescription(int presNo, String invoiceNo, String customerID, String customerName, int age,
			String gender, String address, String date, String drug, String phone, String strength, String dose,
			int quantity) {
		this.presNo = presNo;
		this.invoiceNo = invoiceNo;
		this.customerID = customerID;
		this.customerName = customerName;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.date = date;
		this.drug = drug;
		this.phone = phone;
		this.strength = strength;
		this.dose = dose;
		this.quantity = quantity;
	}

	public Prescription(String customerID, String customerName, int age, String gender, String address,
			String drug, String phone, String strength, String dose, int quantity) {
		this.invoiceNo = "i" + invoiceCount.incrementAndGet();
		this.customerID = customerID;
		this.customerName = customerName;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.drug = drug;
		this.phone = phone;
		this.strength = strength;
		this.dose = dose;
		this.quantity = quantity;
	}

	public int getPresNo() {
		return presNo;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setPresNo(int presNo) {
		this.presNo = presNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDrug() {
		return drug;
	}

	public void setDrug(String drug) {
		this.drug = drug;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStrength() {
		return strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "PrescriptionModel{" + "presNo=" + presNo + ", invoiceNo=" + invoiceNo + ", customerID='" + customerID
				+ '\'' + ", customerName='" + customerName + '\'' + ", age=" + age + ", gender='" + gender + '\''
				+ ", address='" + address + '\'' + ", date='" + date + '\'' + ", drug='" + drug + '\'' + ", phone='"
				+ phone + '\'' + ", strength='" + strength + '\'' + ", dose='" + dose + '\'' + ", quantity=" + quantity
				+ '}';
	}
}
