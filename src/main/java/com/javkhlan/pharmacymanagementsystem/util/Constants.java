package com.javkhlan.pharmacymanagementsystem.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

public class Constants {
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static int minWidth = 900;
	public static int minHeight = 650;
	public static int winX = (int) screenSize.getWidth() / 2 - minWidth / 2;
	public static int winY = (int) screenSize.getHeight() / 2 - minHeight / 2;
	public static Color primaryColor = new Color(0xff43b9ac);
	public static Color secondaryColor = new Color(0xff162033);
	public static Color hoverPrimaryColor = new Color(0xff009c8c);
	public static Color redColor = new Color(0xffd9534f);
	public static Color blueColor = new Color(0xff337ab7);
	public static Font labelFont = new Font("Arial", Font.PLAIN, 14);
}
