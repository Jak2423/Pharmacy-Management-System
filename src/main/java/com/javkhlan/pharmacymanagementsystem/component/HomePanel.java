package com.javkhlan.pharmacymanagementsystem.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.javkhlan.pharmacymanagementsystem.util.Constants;
import com.javkhlan.pharmacymanagementsystem.util.Observer;
import com.javkhlan.pharmacymanagementsystem.util.UserSession;

public class HomePanel extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	private JLabel welcomeLabel, nameLabel, roleLabel;
	private JLabel iconLabel;
	private JLabel bannerLabel;
	private JPanel bannerPanel;
	private Box vBox;

	public HomePanel() {
		super(new BorderLayout());
		setBounds(0, 0, 900, 650);

		vBox = Box.createVerticalBox();
		vBox.setPreferredSize(new Dimension(450, 600));
		vBox.setAlignmentY(Component.TOP_ALIGNMENT);
		vBox.setBorder(new EmptyBorder(20, 20, 20, 20));
		vBox.setOpaque(true);
		vBox.setBackground(new Color(0xffececec));

		iconLabel = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("../resource/images/icon.png"))
				.getImage().getScaledInstance(45, 45, Image.SCALE_DEFAULT)));

		welcomeLabel = new JLabel("<html>Welcome to the Pharmacy Management System</html>");
		welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
		welcomeLabel.setBorder(new EmptyBorder(10, 0, 0, 20));
		welcomeLabel.setForeground(Constants.primaryColor);
		welcomeLabel.setMaximumSize(new Dimension(450, 80));

		nameLabel = new JLabel(UserSession.getUserFullName());
		nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
		nameLabel.setBorder(new EmptyBorder(20, 0, 0, 0));

		roleLabel = new JLabel(UserSession.getUserRole());
		roleLabel.setFont(new Font("Arial", Font.BOLD, 14));

		roleLabel.setForeground(Color.gray);

		vBox.add(iconLabel);
		vBox.add(welcomeLabel);
		vBox.add(nameLabel);
		vBox.add(roleLabel);

		bannerPanel = new JPanel();
		bannerPanel.setPreferredSize(new Dimension(450, 600));
		bannerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		bannerLabel = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("../resource/images/pharmacy.jpg"))
				.getImage().getScaledInstance(getBounds().width / 2, getBounds().height, Image.SCALE_DEFAULT)));
		bannerPanel.add(bannerLabel);

		add(vBox, BorderLayout.WEST);
		add(bannerPanel, BorderLayout.EAST);
	}

	@Override
	public void update() {

	}
}
