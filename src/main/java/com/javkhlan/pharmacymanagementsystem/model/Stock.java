package com.javkhlan.pharmacymanagementsystem.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.javkhlan.pharmacymanagementsystem.state.AvailableState;
import com.javkhlan.pharmacymanagementsystem.state.StockState;
import com.javkhlan.pharmacymanagementsystem.state.UnavailableState;

public class Stock {
	private int id;
	private String name;
	private String category;
	private String description;
	private String company;
	private String supplier;
	private int quantity;
	private double unitCost;
	private String status;
	private String date;

	private StockState state;

	public Stock(int id, String name, String category, String description, String company, String supplier,
			String status, String date, int quantity, double unitCost) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.description = description;
		this.company = company;
		this.supplier = supplier;
		this.quantity = quantity;
		this.unitCost = unitCost;
		this.status = status;
		this.date = date;
	}

	public Stock(String name, String category, String description, String company, String supplier, int quantity,
			double unitCost) {
		this.name = name;
		this.category = category;
		this.description = description;
		this.company = company;
		this.supplier = supplier;
		this.unitCost = unitCost;
		this.quantity = quantity;

		checkStatus();

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
		this.date = formatter.format(now);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
		checkStatus();
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public String getDate() {
		return date;
	}

	public double getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}

	public void setState(StockState state) {
		this.state = state;
	}

	public StockState getState() {
		return state;
	}

	public void checkStatus() {
		if (this.quantity > 0) {
			setState(new AvailableState(this));
		} else {
			setState(new UnavailableState(this));
		}
		this.state.processStock();
	}

}
