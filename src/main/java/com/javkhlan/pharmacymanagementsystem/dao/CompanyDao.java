package com.javkhlan.pharmacymanagementsystem.dao;

import java.util.ArrayList;

import com.javkhlan.pharmacymanagementsystem.model.CompanyModel;

public interface CompanyDao {
	public ArrayList<CompanyModel> getCompanies();

	public void insertCompany(String companyName);

}
