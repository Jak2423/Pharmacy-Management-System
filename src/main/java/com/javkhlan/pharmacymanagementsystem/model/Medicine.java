package com.javkhlan.pharmacymanagementsystem.model;

public class Medicine {
	private int id;
	private String name;
	private String category;
	private String description;
	private String company;
	private String supplier;
	private double unitCost;

	public Medicine(int id, String name, String category, String description, String company, String supplier,
			double unitCost) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.description = description;
		this.company = company;
		this.supplier = supplier;
		this.unitCost = unitCost;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public double getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}

}
