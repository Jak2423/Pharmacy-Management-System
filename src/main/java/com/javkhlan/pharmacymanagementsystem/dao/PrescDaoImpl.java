package com.javkhlan.pharmacymanagementsystem.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.javkhlan.pharmacymanagementsystem.model.Prescription;
import com.javkhlan.pharmacymanagementsystem.util.DBConnection;

public class PrescDaoImpl implements PrescDao {

	@Override
	public ArrayList<Prescription> getPrescriptions() {
		ArrayList<Prescription> presList = new ArrayList<>();

		ResultSet res = null;
		try {
			res = DBConnection.getInstance().dbExecuteQuery("select * from " + "Prescriptions");

			while (res.next()) {
				Prescription prescription = new Prescription(res.getInt("PresNo"), res.getString("InvoiceNo"),
						res.getString("CustomerId"), res.getString("customerName"), res.getInt("age"),
						res.getString("gender"), res.getString("address"), String.valueOf(res.getTimestamp("date")),
						res.getString("drug"), res.getString("phone"), res.getString("strength"), res.getString("dose"),
						res.getInt("quantity"));
				presList.add(prescription);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return presList;
	}

	@Override
	public void insertPrescription(Prescription pres) {
		String query = "INSERT INTO Prescriptions(InvoiceNo, " + "CustomerId," + " " + "customerName, age, "
				+ "gender, address, drug, phone, " + "strength, dose, quantity, date)" + "VALUES ('"
				+ pres.getInvoiceNo() + "', '" + pres.getCustomerID() + "', '" + pres.getCustomerName() + "', "
				+ pres.getAge() + ", '" + pres.getGender() + "', '" + pres.getAddress() + "', '" + pres.getDrug()
				+ "', '" + pres.getPhone() + "', '" + pres.getStrength() + "', '" + pres.getDose() + "', "
				+ pres.getQuantity() + ", NOW())";

		try {
			DBConnection.getInstance().dbExecuteUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deletePrescription(int id) {
		String query = "Delete From Prescriptions Where PresNo = " + id;

		try {
			DBConnection.getInstance().dbExecuteUpdate(query);
		} catch (SQLException err) {
			err.printStackTrace();
		}
	}
}
