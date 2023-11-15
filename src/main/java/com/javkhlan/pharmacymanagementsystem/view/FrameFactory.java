package com.javkhlan.pharmacymanagementsystem.view;

import javax.swing.JFrame;

public class FrameFactory {
	public static JFrame getInstance(String role) {
		switch (role) {
		case "admin":
			return new AdminFrame();
		case "manager":
			return new ManagerFrame();
		case "pharmacist":
			return new PharmacistFrame();
		case "cashier":
			return new CashierFrame();
		}
		return null;
	}
}
