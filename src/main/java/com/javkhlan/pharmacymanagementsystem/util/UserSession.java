package com.javkhlan.pharmacymanagementsystem.util;

public class UserSession {

	private static int userId;
	private static String userFullName;
	private static String userName;
	private static String userEmail;
	private static String userRole;

	private static final UserSession instance = new UserSession();

	private UserSession() {
	}

	public static UserSession getInstance() {
		return instance;
	}

	public static int getUserId() {
		return userId;
	}

	public static void setUserId(int userId) {
		UserSession.userId = userId;
	}

	public static String getUserFullName() {
		return userFullName;
	}

	public static void setUserFullName(String userFullName) {
		UserSession.userFullName = userFullName;
	}

	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		UserSession.userName = userName;
	}

	public static String getUserEmail() {
		return userEmail;
	}

	public static void setUserEmail(String userEmail) {
		UserSession.userEmail = userEmail;
	}

	public static String getUserRole() {
		return userRole;
	}

	public static void setUserRole(String userRole) {
		UserSession.userRole = userRole;
	}

	public static void cleanUserSession() {
		userId = 0;
		userFullName = null;
		userName = null;
		userEmail = null;
		userRole = null;
	}

}