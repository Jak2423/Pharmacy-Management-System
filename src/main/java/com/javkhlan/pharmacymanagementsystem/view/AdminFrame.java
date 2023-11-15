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

import com.javkhlan.pharmacymanagementsystem.component.AddUserPanel;
import com.javkhlan.pharmacymanagementsystem.component.HomePanel;
import com.javkhlan.pharmacymanagementsystem.component.ViewFilterStockPanel;
import com.javkhlan.pharmacymanagementsystem.component.ViewUsersPanel;
import com.javkhlan.pharmacymanagementsystem.util.Constants;
import com.javkhlan.pharmacymanagementsystem.util.Observer;
import com.javkhlan.pharmacymanagementsystem.util.Observable;
import com.javkhlan.pharmacymanagementsystem.util.UserSession;

public class AdminFrame extends JFrame implements Observable, ActionListener {

	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;

	private JMenu pharmaMenu, managerMenu, cashierMenu, stockDataMenu;
	private JMenuItem addPharmaMenuItem, addManagerMenuItem, addCashierMenuItem, viewPharmaItem, viewManagerItem,
			viewCashierItem, viewStockItem, homeMenu, logoutMenu;

	private List<Observer> components;
	private byte activePage = 0;

	public AdminFrame() {
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

		pharmaMenu = new JMenu("Pharmacist");
		managerMenu = new JMenu("Manager");
		cashierMenu = new JMenu("Cashier");

		viewPharmaItem = new JMenuItem("View Pharmacists");
		viewPharmaItem.setActionCommand("1");
		viewPharmaItem.addActionListener(this);
		viewManagerItem = new JMenuItem("View Managers");
		viewManagerItem.setActionCommand("2");
		viewManagerItem.addActionListener(this);
		viewCashierItem = new JMenuItem("View Cashiers");
		viewCashierItem.setActionCommand("3");
		viewCashierItem.addActionListener(this);

		addPharmaMenuItem = new JMenuItem("Add Pharmacists");
		addPharmaMenuItem.setActionCommand("4");
		addPharmaMenuItem.addActionListener(this);
		addManagerMenuItem = new JMenuItem("Add Managers");
		addManagerMenuItem.setActionCommand("5");
		addManagerMenuItem.addActionListener(this);
		addCashierMenuItem = new JMenuItem("Add Cashier");
		addCashierMenuItem.setActionCommand("6");
		addCashierMenuItem.addActionListener(this);

		stockDataMenu = new JMenu("Stock Data");
		viewStockItem = new JMenuItem("View Stocks");
		viewStockItem.setActionCommand("7");
		viewStockItem.addActionListener(this);

		pharmaMenu.add(viewPharmaItem);
		managerMenu.add(viewManagerItem);
		cashierMenu.add(viewCashierItem);
		pharmaMenu.add(addPharmaMenuItem);
		managerMenu.add(addManagerMenuItem);
		cashierMenu.add(addCashierMenuItem);
		stockDataMenu.add(viewStockItem);

		menuBar.add(pharmaMenu);
		menuBar.add(managerMenu);
		menuBar.add(cashierMenu);
		menuBar.add(stockDataMenu);

		menuBar.add(logoutMenu);
		this.setJMenuBar(menuBar);
	}

	private void createComponents() {
		HomePanel homePanel = new HomePanel();
		ViewUsersPanel viewPharmaPanel = new ViewUsersPanel("pharmacist");
		ViewUsersPanel viewManagerPanel = new ViewUsersPanel("manager");
		ViewUsersPanel viewCashierPanel = new ViewUsersPanel("cashier");
		AddUserPanel addPharmaPanel = new AddUserPanel("pharmacist");
		AddUserPanel addManagerPanel = new AddUserPanel("manager");
		AddUserPanel addCashierPanel = new AddUserPanel("cashier");
		ViewFilterStockPanel stockDataPanel = new ViewFilterStockPanel();

		getContentPane().add(homePanel, BorderLayout.CENTER);
		register(homePanel);
		register(viewPharmaPanel);
		register(viewManagerPanel);
		register(viewCashierPanel);
		register(addPharmaPanel);
		register(addManagerPanel);
		register(addCashierPanel);
		register(stockDataPanel);
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
