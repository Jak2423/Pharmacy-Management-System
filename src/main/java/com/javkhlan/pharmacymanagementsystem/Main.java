package com.javkhlan.pharmacymanagementsystem;

import java.awt.EventQueue;

import javax.swing.UIManager;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.javkhlan.pharmacymanagementsystem.view.LoginFrame;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new FlatMacLightLaf());
					new LoginFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
