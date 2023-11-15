package com.javkhlan.pharmacymanagementsystem.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.javkhlan.pharmacymanagementsystem.dao.UserDao;
import com.javkhlan.pharmacymanagementsystem.dao.UserDaoImpl;
import com.javkhlan.pharmacymanagementsystem.model.UserModel;
import com.javkhlan.pharmacymanagementsystem.util.Constants;
import com.javkhlan.pharmacymanagementsystem.util.DBConnection;
import com.javkhlan.pharmacymanagementsystem.util.UserSession;

public class LoginFrame extends JFrame implements ActionListener, FocusListener {

	private static final long serialVersionUID = 1L;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private Box vBox;
	private JLabel iconLabel;
	private JLabel titleLabel;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JPanel bannerPanel;
	private JLabel bannerLabel;
	private JButton loginButton;

	private UserDao userDao;

	public LoginFrame() {
		super("Pharmacy Management System");
		this.userDao = new UserDaoImpl();
		initialize();
		setVisible(true);
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent componentEvent) {
				revalidate();
			}
		});
	}

	private void initialize() {
		setBounds(Constants.winX, Constants.winY, Constants.minWidth, Constants.minHeight);
		setMinimumSize(new Dimension(Constants.minWidth, Constants.minHeight));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		vBox = Box.createVerticalBox();
		vBox.setPreferredSize(new Dimension(450, 650));
		vBox.setAlignmentY(Component.TOP_ALIGNMENT);
		vBox.setBorder(new EmptyBorder(40, 40, 40, 40));
		getContentPane().add(vBox, BorderLayout.WEST);

		iconLabel = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("../resource/images/icon.png"))
				.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

		titleLabel = new JLabel("<html>Log into Pharmacy Management System</html>");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
		titleLabel.setBorder(new EmptyBorder(50, 0, 50, 0));

		usernameLabel = new JLabel("Username:");
		usernameLabel.setFont(Constants.labelFont);
		usernameLabel.setBorder(new EmptyBorder(0, 0, 10, 0));

		usernameField = new JTextField("Enter your username");
		usernameField.setForeground(Color.lightGray);
		usernameField.setAlignmentY(0.0f);
		usernameField.setAlignmentX(0.0f);
		usernameField.setToolTipText("Enter your username");
		usernameField.setBorder(new EmptyBorder(5, 10, 5, 10));
		usernameField.setMaximumSize(new Dimension(300, 40));
		usernameField.addFocusListener(this);
		usernameField.addKeyListener(keyAdapter);

		passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(Constants.labelFont);
		passwordLabel.setBorder(new EmptyBorder(20, 0, 10, 0));

		passwordField = new JPasswordField("Enter your password");
		passwordField.setForeground(Color.lightGray);
		passwordField.setAlignmentX(0.0f);
		passwordField.setAlignmentY(0.0f);
		passwordField.setToolTipText("Enter your password");
		passwordField.setBorder(new EmptyBorder(5, 10, 5, 10));
		passwordField.setMaximumSize(new Dimension(300, 40));
		passwordField.addFocusListener(this);
		passwordField.addKeyListener(keyAdapter);

		loginButton = new JButton("LOG IN");
		loginButton.setAlignmentY(0.0f);
		loginButton.setMaximumSize(new Dimension(300, 40));
		loginButton.setBackground(Constants.primaryColor);
		loginButton.setForeground(Color.white);
		loginButton.setBorderPainted(false);
		loginButton.setFocusable(false);
		loginButton.setOpaque(true);
		loginButton.addActionListener(this);
		loginButton.addMouseListener(mouseAdapter);

		vBox.add(iconLabel);
		vBox.add(titleLabel);
		vBox.add(usernameLabel);
		vBox.add(usernameField);
		vBox.add(passwordLabel);
		vBox.add(passwordField);
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(loginButton);

		bannerPanel = new JPanel();
		getContentPane().add(bannerPanel, BorderLayout.EAST);
		bannerPanel.setPreferredSize(new Dimension(getBounds().width / 2, getBounds().height));
		bannerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		bannerLabel = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("../resource/images/pharmacy.jpg"))
				.getImage().getScaledInstance(getBounds().width / 2, getBounds().height, Image.SCALE_DEFAULT)));
		bannerPanel.add(bannerLabel);
	}

	public boolean userLogin(String username, String password) {

		ResultSet res = null;
		UserModel user = null;
		try {
			res = DBConnection.getInstance()
					.dbExecuteQuery("select * from Users where " + "username" + " = '" + username + "';");

			if (res.next()) {
				user = new UserModel(res.getInt("id"), res.getString("staffId"), res.getString("role"),
						res.getString("firstName"), res.getString("lastName"), res.getString("address"),
						res.getString("phone"), res.getString("email"), res.getString("username"),
						res.getString("password"));
				return user.getPassword().equals(password);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public void loginAuthorization() {
		String username = usernameField.getText();
		String password = String.valueOf(passwordField.getPassword());

		if ((username == null || username.isEmpty()) || (password == null || password.isEmpty())) {
			JOptionPane.showMessageDialog(null, "Please provide inputs for all field", "Missing fields",
					JOptionPane.WARNING_MESSAGE);
			usernameField.setText("");
			usernameField.requestFocusInWindow();
			passwordField.setText("");
		} else {
			UserModel user = userDao.getUserByUsername(username);

			if (user != null && user.getPassword().equals(password)) {
				UserSession.setUserId(user.getId());
				UserSession.setUserFullName(user.getFirstName() + " " + user.getLastName());
				UserSession.setUserName(user.getUsername());
				UserSession.setUserEmail(user.getEmail());
				UserSession.setUserRole(user.getRole());

				String userRole = UserSession.getUserRole();

				if (userRole != null) {
					FrameFactory.getInstance(userRole);
					this.dispose();
				}

			} else {
				JOptionPane.showMessageDialog(null, "Incorrect username or password", "Login Error",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(loginButton)) {
			loginAuthorization();
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource().equals(usernameField)) {
			if (usernameField.getText().equals("Enter your username")) {
				usernameField.setText(null);
				usernameField.setForeground(Color.black);
			}
		} else if (e.getSource().equals(passwordField)) {
			if (String.valueOf(passwordField.getPassword()).equals("Enter your password")) {
				passwordField.setText(null);
				passwordField.setForeground(Color.black);
			}
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource().equals(usernameField)) {
			if (usernameField.getText().isBlank()) {
				usernameField.setText("Enter your username");
				usernameField.setForeground(Color.lightGray);
			}
		} else if (e.getSource().equals(passwordField)) {
			if (String.valueOf(passwordField.getPassword()).isBlank()) {
				passwordField.setText("Enter your password");
				passwordField.setForeground(Color.lightGray);
			}
		}
	}

	MouseAdapter mouseAdapter = new MouseAdapter() {
		@Override
		public void mouseEntered(MouseEvent event) {
			if (event.getSource().equals(loginButton)) {
				loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				loginButton.setBackground(Constants.hoverPrimaryColor);
			}
		}

		@Override
		public void mouseExited(MouseEvent event) {
			if (event.getSource().equals(loginButton)) {
				loginButton.setCursor(Cursor.getDefaultCursor());
				loginButton.setBackground(Constants.primaryColor);
			}
		}
	};

	KeyAdapter keyAdapter = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent event) {
			if (event.getKeyCode() == 10) {
				loginAuthorization();
			}
		}
	};

}
