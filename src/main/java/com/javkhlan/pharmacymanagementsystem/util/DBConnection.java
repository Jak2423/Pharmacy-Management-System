package com.javkhlan.pharmacymanagementsystem.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/Pharmacy";
	private static Connection conn;
	private static final DBConnection instance = new DBConnection();

	private DBConnection() {
		try {
			conn = DriverManager.getConnection(URL, "root", "");

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console" + e);
		}
	}

	public static DBConnection getInstance() throws SQLException {
		return instance;
	}

	public Connection getConnection() {
		return conn;
	}

	public ResultSet dbExecuteQuery(String query) throws SQLException {
		Statement stmt = null;
		ResultSet resultSet = null;
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(query);

		} catch (SQLException e) {
			System.out.println("Problem occurred at executeQuery operation : " + e);
			throw e;
		}

		return resultSet;
	}

	public void dbExecuteUpdate(String query) throws SQLException {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Problem occurred at executeUpdate operation : " + e);
			throw e;
		}
	}
}
