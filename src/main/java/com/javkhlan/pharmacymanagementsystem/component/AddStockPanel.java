package com.javkhlan.pharmacymanagementsystem.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.javkhlan.pharmacymanagementsystem.dao.CompanyDao;
import com.javkhlan.pharmacymanagementsystem.dao.CompanyDaoImpl;
import com.javkhlan.pharmacymanagementsystem.dao.StockDao;
import com.javkhlan.pharmacymanagementsystem.dao.StockDaoImpl;
import com.javkhlan.pharmacymanagementsystem.model.CompanyModel;
import com.javkhlan.pharmacymanagementsystem.model.StockModel;
import com.javkhlan.pharmacymanagementsystem.util.Constants;
import com.javkhlan.pharmacymanagementsystem.util.Observer;

public class AddStockPanel extends JPanel implements Observer, ActionListener {
	private static final long serialVersionUID = -62222933784093813L;

	private JTextField drugNameField, categField, descField, suppField, quanField, costFeild;
	private JLabel drugNameLabel, categLabel, descLabel, compLabel, suppLabel, quanLabel, costLabel;
	private JButton submitButton, addCompBtn;
	private JPanel comboBoxPanel;
	private JComboBox<String> comboBox;
	private StockDao stockDao;
	private CompanyDao companyDao;
	private ArrayList<CompanyModel> companyList;
	private ArrayList<String> companyNames;

	public AddStockPanel() {
		super(new BorderLayout());
		this.stockDao = new StockDaoImpl();
		this.companyDao = new CompanyDaoImpl();
		this.getCompanies();
		setPreferredSize(new Dimension(900, 600));

		GridLayout gridLayout = new GridLayout(7, 2);
		gridLayout.setVgap(20);
		gridLayout.setHgap(20);
		JPanel centerPanel = new JPanel(gridLayout);
		centerPanel.setPreferredSize(new Dimension(900, 600));
		centerPanel.setBorder(BorderFactory.createEmptyBorder(60, 50, 10, 200));

		drugNameLabel = new JLabel("Drug name:");
		drugNameLabel.setFont(Constants.labelFont);
		drugNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		categLabel = new JLabel("Category:");
		categLabel.setFont(Constants.labelFont);
		categLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		descLabel = new JLabel("Description:");
		descLabel.setFont(Constants.labelFont);
		descLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		compLabel = new JLabel("Company:");
		compLabel.setFont(Constants.labelFont);
		compLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		suppLabel = new JLabel("Supplier:");
		suppLabel.setFont(Constants.labelFont);
		suppLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		quanLabel = new JLabel("Quantity:");
		quanLabel.setFont(Constants.labelFont);
		quanLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		costLabel = new JLabel("Unit cost:");
		costLabel.setFont(Constants.labelFont);
		costLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		drugNameField = new JTextField("");
		drugNameField.setAlignmentY(0.0f);
		drugNameField.setAlignmentX(0.0f);
		drugNameField.setToolTipText("Enter drug name");
		drugNameField.setBorder(new EmptyBorder(5, 10, 5, 10));
		drugNameField.setMaximumSize(new Dimension(300, 40));

		categField = new JTextField("");
		categField.setAlignmentY(0.0f);
		categField.setAlignmentX(0.0f);
		categField.setToolTipText("Enter category");
		categField.setBorder(new EmptyBorder(5, 10, 5, 10));
		categField.setMaximumSize(new Dimension(300, 40));

		descField = new JTextField("");
		descField.setAlignmentY(0.0f);
		descField.setAlignmentX(0.0f);
		descField.setToolTipText("Enter description");
		descField.setBorder(new EmptyBorder(5, 10, 5, 10));
		descField.setMaximumSize(new Dimension(300, 40));

		comboBox = new JComboBox<>(companyNames.toArray(new String[0]));
		comboBox.setAlignmentY(0.0f);
		comboBox.setAlignmentX(0.0f);
		comboBox.setToolTipText("Companies");
		comboBox.setBorder(new EmptyBorder(5, 10, 5, 10));
		comboBox.setPreferredSize(new Dimension(260, 40));

		addCompBtn = new JButton("+");
		addCompBtn.setAlignmentY(0.0f);
		addCompBtn.setPreferredSize(new Dimension(40, 40));
		addCompBtn.setBackground(Color.lightGray);
		addCompBtn.setBorderPainted(false);
		addCompBtn.setFocusable(false);
		addCompBtn.setOpaque(true);
		addCompBtn.addActionListener(this);
		addCompBtn.addMouseListener(mouseAdapter);

		comboBoxPanel = new JPanel();
		comboBoxPanel.setPreferredSize(new Dimension(300, 40));
		comboBoxPanel.add(comboBox);
		comboBoxPanel.add(addCompBtn);

		suppField = new JTextField("");
		suppField.setAlignmentY(0.0f);
		suppField.setAlignmentX(0.0f);
		suppField.setToolTipText("Enter drug supplier");
		suppField.setBorder(new EmptyBorder(5, 10, 5, 10));
		suppField.setMaximumSize(new Dimension(300, 40));

		quanField = new JTextField("");
		quanField.setAlignmentY(0.0f);
		quanField.setAlignmentX(0.0f);
		quanField.setToolTipText("Enter quantity");
		quanField.setBorder(new EmptyBorder(5, 10, 5, 10));
		quanField.setMaximumSize(new Dimension(300, 40));
		quanField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
					quanField.setEditable(true);
				} else {
					quanField.setEditable(false);
				}
			}
		});

		costFeild = new JTextField("");
		costFeild.setAlignmentY(0.0f);
		costFeild.setAlignmentX(0.0f);
		costFeild.setToolTipText("Enter cost of unit");
		costFeild.setBorder(new EmptyBorder(5, 10, 5, 10));
		costFeild.setMaximumSize(new Dimension(300, 40));
		costFeild.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() <= '.') {
					costFeild.setEditable(true);
				} else {
					costFeild.setEditable(false);
				}
			}
		});

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

		centerPanel.add(drugNameLabel);
		centerPanel.add(drugNameField);

		centerPanel.add(categLabel);
		centerPanel.add(categField);

		centerPanel.add(descLabel);
		centerPanel.add(descField);

		centerPanel.add(compLabel);
		centerPanel.add(comboBoxPanel);

		centerPanel.add(suppLabel);
		centerPanel.add(suppField);

		centerPanel.add(quanLabel);
		centerPanel.add(quanField);

		centerPanel.add(costLabel);
		centerPanel.add(costFeild);

		add(centerPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

	}

	public void getCompanies() {
		this.companyList = companyDao.getCompanies();

		companyNames = new ArrayList<>();
		for (CompanyModel company : companyList) {
			companyNames.add(company.getName());
		}
	}

	@Override
	public void update() {
		clearFields();
		comboBox.removeAllItems();
		getCompanies();
		for (String name : companyNames) {
			comboBox.addItem(name);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(submitButton)) {

			String drugName = drugNameField.getText();
			String categ = categField.getText();
			String desc = descField.getText();
			String company = (String) comboBox.getSelectedItem();
			String supplier = suppField.getText();

			if (drugName.isEmpty() || categ.isEmpty() || desc.isEmpty() || company.isEmpty() || supplier.isEmpty()
					|| quanField.getText().isEmpty() || costFeild.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please provide inputs for all field", "Missing fields",
						JOptionPane.WARNING_MESSAGE);
			} else {
				StockModel newStock = new StockModel(drugName, categ, desc, company, supplier,
						Integer.parseInt(quanField.getText()), Double.parseDouble(costFeild.getText()));
				stockDao.insertStock(newStock);
				clearFields();
			}

		}

		if (e.getSource().equals(addCompBtn)) {
			new AddCompanyFrame(AddStockPanel.this);
		}

	}

	public void clearFields() {
		drugNameField.setText("");
		categField.setText("");
		descField.setText("");
		comboBox.setSelectedIndex(0);
		suppField.setText("");
		quanField.setText("");
		costFeild.setText("");
	}

	MouseAdapter mouseAdapter = new MouseAdapter() {
		@Override
		public void mouseEntered(MouseEvent event) {
			if (event.getSource().equals(submitButton)) {
				submitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				submitButton.setBackground(Color.gray);
			}
			if (event.getSource().equals(addCompBtn)) {
				addCompBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				addCompBtn.setBackground(Color.gray);
			}
		}

		@Override
		public void mouseExited(MouseEvent event) {
			if (event.getSource().equals(submitButton)) {
				submitButton.setCursor(Cursor.getDefaultCursor());
				submitButton.setBackground(Color.lightGray);
			}
			if (event.getSource().equals(addCompBtn)) {
				addCompBtn.setCursor(Cursor.getDefaultCursor());
				addCompBtn.setBackground(Color.lightGray);
			}
		}
	};
}
