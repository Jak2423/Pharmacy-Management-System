package com.javkhlan.pharmacymanagementsystem.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.javkhlan.pharmacymanagementsystem.model.UserModel;
import com.javkhlan.pharmacymanagementsystem.util.DBConnection;

public class UserDaoImpl implements UserDao {

	@Override
	public ArrayList<UserModel> getUsers() {
		ArrayList<UserModel> userList = new ArrayList<>();

		ResultSet res = null;
		try {
			res = DBConnection.getInstance().dbExecuteQuery("select * from Users");

			while (res.next()) {
				UserModel user = new UserModel(res.getInt("id"), res.getString("staffId"), res.getString("role"),
						res.getString("firstName"), res.getString("lastName"), res.getString("address"),
						res.getString("phone"), res.getString("email"), res.getString("username"),
						res.getString("password"));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public ArrayList<UserModel> getUserByRole(String role) {
		ArrayList<UserModel> userList = new ArrayList<>();
		ResultSet res = null;
		try {
			res = DBConnection.getInstance().dbExecuteQuery("select * from Users " + "where role = '" + role + "';");

			while (res.next()) {
				UserModel user = new UserModel(res.getInt("id"), res.getString("staffId"), res.getString("role"),
						res.getString("firstName"), res.getString("lastName"), res.getString("address"),
						res.getString("phone"), res.getString("email"), res.getString("username"),
						res.getString("password"));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public ArrayList<UserModel> getUserByAddress(String addr) {
		ArrayList<UserModel> userList = new ArrayList<>();
		ResultSet res = null;
		try {
			res = DBConnection.getInstance().dbExecuteQuery("select * from Users Where address = " + "'" + addr + "';");

			while (res.next()) {
				UserModel user = new UserModel(res.getInt("id"), res.getString("staffId"), res.getString("role"),
						res.getString("firstName"), res.getString("lastName"), res.getString("address"),
						res.getString("phone"), res.getString("email"), res.getString("username"),
						res.getString("password"));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public UserModel getUserByUsername(String username) {
		ResultSet res = null;
		UserModel user = null;
		try {
			res = DBConnection.getInstance()
					.dbExecuteQuery("select * from Users where " + "username = '" + username + "';");
			if (res.next()) {
				user = new UserModel(res.getInt("id"), res.getString("staffId"), res.getString("role"),
						res.getString("firstName"), res.getString("lastName"), res.getString("address"),
						res.getString("phone"), res.getString("email"), res.getString("username"),
						res.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public void updateUser(UserModel user, int updateUserId) {
		String query = "UPDATE Users SET firstName = '" + user.getFirstName() + "', lastName = '" + user.getLastName()
				+ "', " + "staffId" + " = '" + user.getStaffId() + "', address = '" + user.getAddress() + "', phone = '"
				+ user.getPhone() + "', email = '" + user.getEmail() + "', username = '" + user.getUsername()
				+ "', password = '" + user.getPassword() + "' Where id = " + updateUserId;

		try {
			DBConnection.getInstance().dbExecuteUpdate(query);
		} catch (SQLException err) {
			err.printStackTrace();
		}
	}

	@Override
	public void insertUser(UserModel user, String currentRole) {
		String query = "INSERT INTO Users(firstName, lastName, staffId, "
				+ "phone, email, address, role, username, password) VALUES " + "('" + user.getFirstName() + "', '"
				+ user.getLastName() + "', '" + user.getStaffId() + "', '" + user.getPhone() + "', '" + user.getEmail()
				+ "', " + "'" + user.getAddress() + "', '" + currentRole + "', '" + user.getUsername() + "', '"
				+ user.getPassword() + "')";

		try {
			DBConnection.getInstance().dbExecuteUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(int id) {
		String query = "Delete From Users Where id = " + id;

		try {
			DBConnection.getInstance().dbExecuteUpdate(query);
		} catch (SQLException err) {
			err.printStackTrace();
		}
	}
}
