package com.javkhlan.pharmacymanagementsystem.dao;

import java.util.ArrayList;

import com.javkhlan.pharmacymanagementsystem.model.Company;

public interface CompanyDao {
	public ArrayList<Company> getCompanies();

	public void insertCompany(String companyName);

}
