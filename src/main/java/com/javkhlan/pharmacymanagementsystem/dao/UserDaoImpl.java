package com.javkhlan.pharmacymanagementsystem.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.javkhlan.pharmacymanagementsystem.model.User;
import com.javkhlan.pharmacymanagementsystem.util.DBConnection;

public class UserDaoImpl implements UserDao {

	@Override
	public ArrayList<User> getUsers() {
		ArrayList<User> userList = new ArrayList<>();

		ResultSet res = null;
		try {
			res = DBConnection.getInstance().dbExecuteQuery("select * from Users");

			while (res.next()) {
				User user = new User(res.getInt("id"), res.getString("staffId"), res.getString("role"),
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
	public ArrayList<User> getUserByRole(String role) {
		ArrayList<User> userList = new ArrayList<>();
		ResultSet res = null;
		try {
			res = DBConnection.getInstance().dbExecuteQuery("select * from Users " + "where role = '" + role + "';");

			while (res.next()) {
				User user = new User(res.getInt("id"), res.getString("staffId"), res.getString("role"),
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
	public ArrayList<User> getUserByAddress(String addr) {
		ArrayList<User> userList = new ArrayList<>();
		ResultSet res = null;
		try {
			res = DBConnection.getInstance().dbExecuteQuery("select * from Users Where address = " + "'" + addr + "';");

			while (res.next()) {
				User user = new User(res.getInt("id"), res.getString("staffId"), res.getString("role"),
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
	public User getUserByUsername(String username) {
		ResultSet res = null;
		User user = null;
		try {
			res = DBConnection.getInstance()
					.dbExecuteQuery("select * from Users where " + "username = '" + username + "';");
			if (res.next()) {
				user = new User(res.getInt("id"), res.getString("staffId"), res.getString("role"),
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
	public void updateUser(User user, int updateUserId) {
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
	public void insertUser(User user, String currentRole) {
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
