package com.javkhlan.pharmacymanagementsystem.dao;

import java.util.ArrayList;

import com.javkhlan.pharmacymanagementsystem.model.User;

public interface UserDao {

	public ArrayList<User> getUsers();

	public ArrayList<User> getUserByRole(String role);

	public ArrayList<User> getUserByAddress(String addr);

	public User getUserByUsername(String username);

	public void updateUser(User user, int updateUserId);

	public void insertUser(User user, String currentRole);

	public void deleteUser(int id);
}
