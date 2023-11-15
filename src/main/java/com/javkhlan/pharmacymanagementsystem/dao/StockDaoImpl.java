package com.javkhlan.pharmacymanagementsystem.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.javkhlan.pharmacymanagementsystem.model.Stock;
import com.javkhlan.pharmacymanagementsystem.util.DBConnection;

public class StockDaoImpl implements StockDao {
	@Override
	public ArrayList<Stock> getStocks() {
		ArrayList<Stock> stockList = new ArrayList<>();
		ResultSet res = null;
		try {
			res = DBConnection.getInstance().dbExecuteQuery("select * from Stocks");

			while (res.next()) {
				Stock stock = new Stock(res.getInt("id"), res.getString("name"), res.getString("category"),
						res.getString("description"), res.getString("company"), res.getString("supplier"),
						res.getString("status"), String.valueOf(res.getTimestamp("date")), res.getInt("quantity"),
						res.getDouble("unitCost"));
				stockList.add(stock);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stockList;
	}

	@Override
	public ArrayList<Stock> getMinCostStock() {
		ArrayList<Stock> stockList = new ArrayList<>();
		ResultSet res = null;
		try {
			res = DBConnection.getInstance()
					.dbExecuteQuery("SELECT * FROM Stocks\n" + "WHERE unitCost = (SELECT MIN(unitCost) FROM Stocks)");

			while (res.next()) {
				Stock stock = new Stock(res.getInt("id"), res.getString("name"), res.getString("category"),
						res.getString("description"), res.getString("company"), res.getString("supplier"),
						res.getString("status"), String.valueOf(res.getTimestamp("date")), res.getInt("quantity"),
						res.getDouble("unitCost"));
				stockList.add(stock);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stockList;
	}

	@Override
	public ArrayList<Stock> getMaxCostStock() {
		ArrayList<Stock> stockList = new ArrayList<>();
		ResultSet res = null;
		try {
			res = DBConnection.getInstance()
					.dbExecuteQuery("SELECT * FROM Stocks\n" + "WHERE unitCost = (SELECT MAX(unitCost) FROM Stocks)");

			while (res.next()) {
				Stock stock = new Stock(res.getInt("id"), res.getString("name"), res.getString("category"),
						res.getString("description"), res.getString("company"), res.getString("supplier"),
						res.getString("status"), String.valueOf(res.getTimestamp("date")), res.getInt("quantity"),
						res.getDouble("unitCost"));
				stockList.add(stock);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stockList;
	}

	@Override
	public ArrayList<Stock> getStockByName(String name) {
		ArrayList<Stock> stockList = new ArrayList<>();
		ResultSet res = null;
		try {
			res = DBConnection.getInstance()
					.dbExecuteQuery("SELECT * FROM Stocks\n" + "WHERE name Like '%" + name + "%';");

			while (res.next()) {
				Stock stock = new Stock(res.getInt("id"), res.getString("name"), res.getString("category"),
						res.getString("description"), res.getString("company"), res.getString("supplier"),
						res.getString("status"), String.valueOf(res.getTimestamp("date")), res.getInt("quantity"),
						res.getDouble("unitCost"));
				stockList.add(stock);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stockList;
	}

	@Override
	public void insertStock(Stock stock) {
		String query = "INSERT INTO Stocks(name, category, description, "
				+ "company, supplier, quantity, unitCost, status, date) VALUES " + "('" + stock.getName() + "', '"
				+ stock.getCategory() + "', '" + stock.getDescription() + "', '" + stock.getCompany() + "', " + "'"
				+ stock.getSupplier() + "', " + stock.getQuantity() + ", " + stock.getUnitCost() + ", '"
				+ stock.getStatus() + "', NOW())";

		try {
			DBConnection.getInstance().dbExecuteUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteStock(int id) {
		String query = "Delete From Stocks Where id = " + id;

		try {
			DBConnection.getInstance().dbExecuteUpdate(query);
		} catch (SQLException err) {
			err.printStackTrace();
		}
	}

	@Override
	public void purchaseFromStock(int id) {
		String query = "Update Stocks SET quantity = quantity - 1 Where id = " + id;

		try {
			DBConnection.getInstance().dbExecuteUpdate(query);
		} catch (SQLException err) {
			err.printStackTrace();
		}
	}

}
