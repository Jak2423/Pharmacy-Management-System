package com.javkhlan.pharmacymanagementsystem.dao;

import java.util.ArrayList;

import com.javkhlan.pharmacymanagementsystem.model.UserModel;

public interface UserDao {

	public ArrayList<UserModel> getUsers();

	public ArrayList<UserModel> getUserByRole(String role);

	public ArrayList<UserModel> getUserByAddress(String addr);

	public UserModel getUserByUsername(String username);

	public void updateUser(UserModel user, int updateUserId);

	public void insertUser(UserModel user, String currentRole);

	public void deleteUser(int id);
}
