package com.javkhlan.pharmacymanagementsystem.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.javkhlan.pharmacymanagementsystem.component.AddPaymentPanel;
import com.javkhlan.pharmacymanagementsystem.component.HomePanel;
import com.javkhlan.pharmacymanagementsystem.component.PurchasePanel;
import com.javkhlan.pharmacymanagementsystem.util.Constants;
import com.javkhlan.pharmacymanagementsystem.util.Observable;
import com.javkhlan.pharmacymanagementsystem.util.Observer;
import com.javkhlan.pharmacymanagementsystem.util.UserSession;

public class CashierFrame extends JFrame implements Observable, ActionListener {

	private static final long serialVersionUID = 9121065537319074904L;

	private JMenuBar menuBar;
	private JMenu paymentMenu;
	private JMenuItem processItem, purchaseItem, logoutMenu, homeMenu;
	private List<Observer> components;
	private byte activePage = 0;

	public CashierFrame() {
		super("Pharmacy Management System");
		this.components = new ArrayList<Observer>();

		initialize();
		setVisible(true);
	}

	private void initialize() {
		setBounds(Constants.winX, Constants.winY, Constants.minWidth, Constants.minHeight);
		setMinimumSize(new Dimension(Constants.minWidth, Constants.minHeight));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		createMenuBar();
		createComponents();
	}

	private void createMenuBar() {
		menuBar = new JMenuBar();
		homeMenu = new JMenuItem("Home");
		homeMenu.setActionCommand("0");
		homeMenu.addActionListener(this);
		homeMenu.setMaximumSize(new Dimension(80, homeMenu.getPreferredSize().height));
		menuBar.add(homeMenu);

		logoutMenu = new JMenuItem("Log Out");
		logoutMenu.setActionCommand("8");
		logoutMenu.addActionListener(this);
		logoutMenu.setMaximumSize(new Dimension(100, logoutMenu.getPreferredSize().height));

		paymentMenu = new JMenu("Payment");

		purchaseItem = new JMenuItem("Make purchase");
		purchaseItem.setActionCommand("1");
		purchaseItem.addActionListener(this);

		processItem = new JMenuItem("Process Payment");
		processItem.setActionCommand("2");
		processItem.addActionListener(this);

		paymentMenu.add(purchaseItem);
		paymentMenu.add(processItem);

		menuBar.add(homeMenu);
		menuBar.add(paymentMenu);
		menuBar.add(logoutMenu);

		this.setJMenuBar(menuBar);
	}

	private void createComponents() {
		HomePanel homePanel = new HomePanel();
		AddPaymentPanel addPaymentPanel = new AddPaymentPanel();
		PurchasePanel purchasePanel = new PurchasePanel();
		getContentPane().add(homePanel, BorderLayout.CENTER);
		register(homePanel);
		register(purchasePanel);
		register(addPaymentPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (Integer.parseInt(e.getActionCommand()) >= 0 && Integer.parseInt(e.getActionCommand()) < components.size()) {
			if (Integer.parseInt(e.getActionCommand()) != activePage) {
				activePage = (byte) Integer.parseInt(e.getActionCommand());
			}
			notifyObservers();
			setContentPane((Container) components.get(activePage));
			revalidate();
		} else if (Integer.parseInt(e.getActionCommand()) == 8) {
			new LoginFrame();
			UserSession.cleanUserSession();
			this.dispose();
		}
	}

	@Override
	public void register(Observer obj) {
		if (obj == null)
			throw new NullPointerException("Null Observer");

		if (!components.contains(obj))
			components.add(obj);

	}

	@Override
	public void unregister(Observer obj) {
		components.remove(obj);
	}

	@Override
	public void notifyObservers() {
		for (Observer component : components) {
			component.update();
		}
	}

}
