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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.javkhlan.pharmacymanagementsystem.dao.PrescDao;
import com.javkhlan.pharmacymanagementsystem.dao.PrescDaoImpl;
import com.javkhlan.pharmacymanagementsystem.model.Prescription;
import com.javkhlan.pharmacymanagementsystem.util.Constants;
import com.javkhlan.pharmacymanagementsystem.util.Observer;

public class AddPrescPanel extends JPanel implements Observer, ActionListener {

	private static final long serialVersionUID = 6541234144366217368L;
	private JTextField cusIdField, cusNameField, ageField, genderField, addrField, drugField, phoneField, strengthField,
			doseField, medQuantityField;
	private JLabel cusIdLabel, cusNameLabel, ageLabel, genderLabel, addrLabel, drugLabel, phoneLabel, strengthLabel,
			doseLabel, medQuantityLabel;
	private JButton submitButton;
	private PrescDao prescDao;

	public AddPrescPanel() {
		super(new BorderLayout());
		this.prescDao = new PrescDaoImpl();
		setPreferredSize(new Dimension(900, 600));

		GridLayout gridLayout = new GridLayout(10, 2);
		gridLayout.setVgap(10);
		gridLayout.setHgap(20);
		JPanel centerPanel = new JPanel(gridLayout);
		centerPanel.setPreferredSize(new Dimension(900, 600));
		centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 0, 200));

		cusIdLabel = new JLabel("Customer ID:");
		cusIdLabel.setFont(Constants.labelFont);
		cusIdLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		cusNameLabel = new JLabel("Customer name:");
		cusNameLabel.setFont(Constants.labelFont);
		cusNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		ageLabel = new JLabel("Age:");
		ageLabel.setFont(Constants.labelFont);
		ageLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		genderLabel = new JLabel("Gender:");
		genderLabel.setFont(Constants.labelFont);
		genderLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		addrLabel = new JLabel("Address:");
		addrLabel.setFont(Constants.labelFont);
		addrLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		drugLabel = new JLabel("Drug:");
		drugLabel.setFont(Constants.labelFont);
		drugLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		phoneLabel = new JLabel("Phonet:");
		phoneLabel.setFont(Constants.labelFont);
		phoneLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		strengthLabel = new JLabel("Strength:");
		strengthLabel.setFont(Constants.labelFont);
		strengthLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		doseLabel = new JLabel("Dose:");
		doseLabel.setFont(Constants.labelFont);
		doseLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		medQuantityLabel = new JLabel("Quantity:");
		medQuantityLabel.setFont(Constants.labelFont);
		medQuantityLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		cusIdField = new JTextField("");
		cusIdField.setAlignmentY(0.0f);
		cusIdField.setAlignmentX(0.0f);
		cusIdField.setToolTipText("Enter drug name");
		cusIdField.setBorder(new EmptyBorder(5, 10, 5, 10));
		cusIdField.setMaximumSize(new Dimension(300, 40));

		cusNameField = new JTextField("");
		cusNameField.setAlignmentY(0.0f);
		cusNameField.setAlignmentX(0.0f);
		cusNameField.setToolTipText("Enter category");
		cusNameField.setBorder(new EmptyBorder(5, 10, 5, 10));
		cusNameField.setMaximumSize(new Dimension(300, 40));

		ageField = new JTextField("");
		ageField.setAlignmentY(0.0f);
		ageField.setAlignmentX(0.0f);
		ageField.setToolTipText("Enter description");
		ageField.setBorder(new EmptyBorder(5, 10, 5, 10));
		ageField.setMaximumSize(new Dimension(300, 40));
		ageField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
					ageField.setEditable(true);
				} else {
					ageField.setEditable(false);
				}
			}
		});

		genderField = new JTextField("");
		genderField.setAlignmentY(0.0f);
		genderField.setAlignmentX(0.0f);
		genderField.setToolTipText("Enter company name");
		genderField.setBorder(new EmptyBorder(5, 10, 5, 10));
		genderField.setMaximumSize(new Dimension(300, 40));

		addrField = new JTextField("");
		addrField.setAlignmentY(0.0f);
		addrField.setAlignmentX(0.0f);
		addrField.setToolTipText("Enter drug supplier");
		addrField.setBorder(new EmptyBorder(5, 10, 5, 10));
		addrField.setMaximumSize(new Dimension(300, 40));

		drugField = new JTextField("");
		drugField.setAlignmentY(0.0f);
		drugField.setAlignmentX(0.0f);
		drugField.setToolTipText("Enter quantity");
		drugField.setBorder(new EmptyBorder(5, 10, 5, 10));
		drugField.setMaximumSize(new Dimension(300, 40));

		phoneField = new JTextField("");
		phoneField.setAlignmentY(0.0f);
		phoneField.setAlignmentX(0.0f);
		phoneField.setToolTipText("Enter cost of unit");
		phoneField.setBorder(new EmptyBorder(5, 10, 5, 10));
		phoneField.setMaximumSize(new Dimension(300, 40));

		strengthField = new JTextField("");
		strengthField.setAlignmentY(0.0f);
		strengthField.setAlignmentX(0.0f);
		strengthField.setToolTipText("Enter cost of unit");
		strengthField.setBorder(new EmptyBorder(5, 10, 5, 10));
		strengthField.setMaximumSize(new Dimension(300, 40));

		doseField = new JTextField("");
		doseField.setAlignmentY(0.0f);
		doseField.setAlignmentX(0.0f);
		doseField.setToolTipText("Enter cost of unit");
		doseField.setBorder(new EmptyBorder(5, 10, 5, 10));
		doseField.setMaximumSize(new Dimension(300, 40));

		medQuantityField = new JTextField("");
		medQuantityField.setAlignmentY(0.0f);
		medQuantityField.setAlignmentX(0.0f);
		medQuantityField.setToolTipText("Enter cost of unit");
		medQuantityField.setBorder(new EmptyBorder(5, 10, 5, 10));
		medQuantityField.setMaximumSize(new Dimension(300, 40));
		medQuantityField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
					medQuantityField.setEditable(true);
				} else {
					medQuantityField.setEditable(false);
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

		centerPanel.add(cusIdLabel);
		centerPanel.add(cusIdField);
		centerPanel.add(cusNameLabel);
		centerPanel.add(cusNameField);
		centerPanel.add(ageLabel);
		centerPanel.add(ageField);
		centerPanel.add(genderLabel);
		centerPanel.add(genderField);
		centerPanel.add(addrLabel);
		centerPanel.add(addrField);
		centerPanel.add(drugLabel);
		centerPanel.add(drugField);
		centerPanel.add(phoneLabel);
		centerPanel.add(phoneField);
		centerPanel.add(strengthLabel);
		centerPanel.add(strengthField);
		centerPanel.add(doseLabel);
		centerPanel.add(doseField);
		centerPanel.add(medQuantityLabel);
		centerPanel.add(medQuantityField);

		add(centerPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	@Override
	public void update() {
		clearFields();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(submitButton)) {

			if (cusIdField.getText().isEmpty() || cusNameField.getText().isEmpty() || ageField.getText().isEmpty()
					|| genderField.getText().isEmpty() || addrField.getText().isEmpty() || drugField.getText().isEmpty()
					|| phoneField.getText().isEmpty() || strengthField.getText().isEmpty()
					|| doseField.getText().isEmpty() || medQuantityField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please provide inputs for all field", "Missing fields",
						JOptionPane.WARNING_MESSAGE);
			} else {
				Prescription newPresc = new Prescription(cusIdField.getText(), cusNameField.getText(),
						Integer.parseInt(ageField.getText()), genderField.getText(), addrField.getText(),
						drugField.getText(), phoneField.getText(), strengthField.getText(), doseField.getText(),
						Integer.parseInt(medQuantityField.getText()));
				prescDao.insertPrescription(newPresc);
				clearFields();

			}

		}

	}

	public void clearFields() {
		cusIdField.setText("");
		cusNameField.setText("");
		ageField.setText("");
		genderField.setText("");
		addrField.setText("");
		drugField.setText("");
		phoneField.setText("");
		strengthField.setText("");
		doseField.setText("");
		medQuantityField.setText("");
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

}
