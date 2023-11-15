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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.javkhlan.pharmacymanagementsystem.dao.PaymentDao;
import com.javkhlan.pharmacymanagementsystem.dao.PaymentDaoImpl;
import com.javkhlan.pharmacymanagementsystem.model.PaymentModel;
import com.javkhlan.pharmacymanagementsystem.util.Constants;
import com.javkhlan.pharmacymanagementsystem.util.Observer;

public class AddPaymentPanel extends JPanel implements Observer, ActionListener {
	private static final long serialVersionUID = -936856466447062351L;
	private JTextField invoiceField, amountField, serialField, paymentField;
	private JLabel invoiceLabel, amountLabel, serialLabel, paymentLabel;
	private JButton submitButton;
	private PaymentDao paymentDao;

	public AddPaymentPanel() {
		super(new BorderLayout());
		this.paymentDao = new PaymentDaoImpl();
		setPreferredSize(new Dimension(900, 600));

		GridLayout gridLayout = new GridLayout(6, 2);
		gridLayout.setVgap(20);
		gridLayout.setHgap(20);
		JPanel centerPanel = new JPanel(gridLayout);
		centerPanel.setPreferredSize(new Dimension(900, 600));
		centerPanel.setBorder(BorderFactory.createEmptyBorder(150, 50, 10, 200));

		invoiceLabel = new JLabel("Invoice No:");
		invoiceLabel.setFont(Constants.labelFont);
		invoiceLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		amountLabel = new JLabel("Amount:");
		amountLabel.setFont(Constants.labelFont);
		amountLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		serialLabel = new JLabel("Serial No:");
		serialLabel.setFont(Constants.labelFont);
		serialLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		paymentLabel = new JLabel("Payment method:");
		paymentLabel.setFont(Constants.labelFont);
		paymentLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		invoiceField = new JTextField("");
		invoiceField.setAlignmentY(0.0f);
		invoiceField.setAlignmentX(0.0f);
		invoiceField.setBorder(new EmptyBorder(5, 10, 5, 10));
		invoiceField.setMaximumSize(new Dimension(300, 40));

		amountField = new JTextField("");
		amountField.setAlignmentY(0.0f);
		amountField.setAlignmentX(0.0f);
		amountField.setBorder(new EmptyBorder(5, 10, 5, 10));
		amountField.setMaximumSize(new Dimension(300, 40));

		paymentField = new JTextField("");
		paymentField.setAlignmentY(0.0f);
		paymentField.setAlignmentX(0.0f);
		paymentField.setBorder(new EmptyBorder(5, 10, 5, 10));
		paymentField.setMaximumSize(new Dimension(300, 40));

		serialField = new JTextField("");
		serialField.setAlignmentY(0.0f);
		serialField.setAlignmentX(0.0f);
		serialField.setBorder(new EmptyBorder(5, 10, 5, 10));
		serialField.setPreferredSize(new Dimension(300, 40));

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

		centerPanel.add(invoiceLabel);
		centerPanel.add(invoiceField);
		centerPanel.add(amountLabel);
		centerPanel.add(amountField);
		centerPanel.add(paymentLabel);
		centerPanel.add(paymentField);
		centerPanel.add(serialLabel);
		centerPanel.add(serialField);

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

			if (invoiceField.getText().isEmpty() || amountField.getText().isEmpty() || paymentField.getText().isEmpty()
					|| serialField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please provide inputs for all field", "Missing fields",
						JOptionPane.WARNING_MESSAGE);
			} else {
				PaymentModel newPayment = new PaymentModel(invoiceField.getText(),
						Double.parseDouble(amountField.getText()), paymentField.getText(), serialField.getText());
				paymentDao.insertPayment(newPayment);
				clearFields();
			}

		}

	}

	public void clearFields() {
		invoiceField.setText("");
		amountField.setText("");
		paymentField.setText("");
		serialField.setText("");
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
