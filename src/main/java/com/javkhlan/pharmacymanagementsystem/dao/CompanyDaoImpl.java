package com.javkhlan.pharmacymanagementsystem.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.javkhlan.pharmacymanagementsystem.model.CompanyModel;
import com.javkhlan.pharmacymanagementsystem.util.DBConnection;

public class CompanyDaoImpl implements CompanyDao {
	@Override
	public ArrayList<CompanyModel> getCompanies() {
		ArrayList<CompanyModel> companyList = new ArrayList<>();

		ResultSet res = null;
		try {
			res = DBConnection.getInstance().dbExecuteQuery("select * from " + "Companies");

			while (res.next()) {
				CompanyModel company = new CompanyModel(res.getString("id"), res.getString("name"));
				companyList.add(company);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return companyList;
	}

	@Override
	public void insertCompany(String companyName) {
		String query = "INSERT INTO Companies(name)" + "VALUES ('" + companyName + "')";

		try {
			DBConnection.getInstance().dbExecuteUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
