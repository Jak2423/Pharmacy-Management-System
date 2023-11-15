package com.javkhlan.pharmacymanagementsystem.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.javkhlan.pharmacymanagementsystem.dao.CompanyDao;
import com.javkhlan.pharmacymanagementsystem.dao.CompanyDaoImpl;
import com.javkhlan.pharmacymanagementsystem.util.Constants;
import com.javkhlan.pharmacymanagementsystem.util.Observer;

public class AddCompanyFrame extends JFrame implements Observer, ActionListener {
	private JPanel panel;
	private JTextField nameField;
	private JLabel nameLabel;
	private JButton submitButton;
	private CompanyDao companyDao;
	private Observer addStockView;

	public AddCompanyFrame(Observer addStockView) {
		super("Add new company");

		this.addStockView = addStockView;
		initialize();
		setVisible(true);
	}

	private void initialize() {
		setBounds(Constants.winX + 100, Constants.winY + 100, Constants.minWidth / 2, 200);
		setMinimumSize(new Dimension(Constants.minWidth / 2, 200));
		getContentPane().setLayout(new BorderLayout());
		createPanel();

	}

	private void createPanel() {
		panel = new JPanel(new BorderLayout());
		this.companyDao = new CompanyDaoImpl();
		panel.setPreferredSize(new Dimension(450, 200));

		GridLayout gridLayout = new GridLayout(1, 2);
		gridLayout.setVgap(20);
		gridLayout.setHgap(20);
		JPanel centerPanel = new JPanel(gridLayout);
		centerPanel.setBorder(BorderFactory.createEmptyBorder(20, -100, 10, 50));

		nameLabel = new JLabel("Company name:");
		nameLabel.setFont(Constants.labelFont);
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		nameField = new JTextField();
		nameField.setAlignmentY(0.0f);
		nameField.setAlignmentX(0.0f);
		nameField.setToolTipText("Enter company name");
		nameField.setBorder(new EmptyBorder(5, 10, 5, 10));
		nameField.setPreferredSize(new Dimension(300, 40));

		submitButton = new JButton("Submit");
		submitButton.setAlignmentY(0.0f);
		submitButton.setPreferredSize(new Dimension(200, 40));
		submitButton.setBackground(Color.lightGray);
		submitButton.setBorderPainted(false);
		submitButton.setFocusable(false);
		submitButton.setOpaque(true);
		submitButton.addActionListener(this);
		submitButton.addMouseListener(mouseAdapter);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		buttonPanel.add(submitButton);

		centerPanel.add(nameLabel);
		centerPanel.add(nameField);

		panel.add(centerPanel, BorderLayout.CENTER);
		panel.add(buttonPanel, BorderLayout.SOUTH);
		getContentPane().add(panel, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(submitButton)) {
			String name = nameField.getText();

			if (name.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please provide inputs for all field", "Missing fields",
						JOptionPane.WARNING_MESSAGE);
			} else {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to submit this company?",
						"Warning", dialogButton);

				if (dialogResult == JOptionPane.YES_OPTION) {

					companyDao.insertCompany(name);

					nameField.setText("");
					if (addStockView != null) {
						addStockView.update();
					}
					this.dispose();
				}

			}
		}
	}

	MouseAdapter mouseAdapter = new MouseAdapter() {
		@Override
		public void mouseEntered(MouseEvent event) {
			if (event.getSource().equals(submitButton)) {
				submitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				submitButton.setBackground(Color.gray);
			}
		}

		@Override
		public void mouseExited(MouseEvent event) {
			if (event.getSource().equals(submitButton)) {
				submitButton.setCursor(Cursor.getDefaultCursor());
				submitButton.setBackground(Color.lightGray);
			}
		}
	};

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
}
