package com.javkhlan.pharmacymanagementsystem.dao;

import java.sql.SQLException;

import com.javkhlan.pharmacymanagementsystem.model.PaymentModel;
import com.javkhlan.pharmacymanagementsystem.util.DBConnection;

public class PaymentDaoImpl implements PaymentDao {
	@Override
	public void insertPayment(PaymentModel payment) {
		String query = "INSERT INTO Payments\n" + "(InvoiceNo, Amount, PaymentMethod, " + "SerialNo)\n" + "VALUES ('"
				+ payment.getInvoiceNo() + "', " + payment.getAmount() + ",'" + payment.getPaymentMethod() + "', '"
				+ payment.getSerialNo() + "');";
		try {
			DBConnection.getInstance().dbExecuteUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
