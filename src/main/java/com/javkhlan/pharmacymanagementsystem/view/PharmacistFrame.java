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

import com.javkhlan.pharmacymanagementsystem.component.AddPrescPanel;
import com.javkhlan.pharmacymanagementsystem.component.AddStockPanel;
import com.javkhlan.pharmacymanagementsystem.component.HomePanel;
import com.javkhlan.pharmacymanagementsystem.component.ViewPrescPanel;
import com.javkhlan.pharmacymanagementsystem.component.ViewStockPanel;
import com.javkhlan.pharmacymanagementsystem.util.Constants;
import com.javkhlan.pharmacymanagementsystem.util.Observer;
import com.javkhlan.pharmacymanagementsystem.util.Observable;
import com.javkhlan.pharmacymanagementsystem.util.UserSession;

public class PharmacistFrame extends JFrame implements Observable, ActionListener {

	private static final long serialVersionUID = -8410365424464846064L;

	private JMenuBar menuBar;

	private JMenu stockDataMenu, prescMenu;
	private JMenuItem viewStockItem, addStockItem, homeMenu, logoutMenu, viewPrescItem, addPrescItem;

	private List<Observer> components;
	private byte activePage = 0;

	public PharmacistFrame() {
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

		stockDataMenu = new JMenu("Stocks");
		viewStockItem = new JMenuItem("View Stocks");
		viewStockItem.setActionCommand("1");
		viewStockItem.addActionListener(this);
		addStockItem = new JMenuItem("Add Medicine");
		addStockItem.setActionCommand("2");
		addStockItem.addActionListener(this);

		stockDataMenu.add(viewStockItem);
		stockDataMenu.add(addStockItem);

		prescMenu = new JMenu("Prescriptions");

		viewPrescItem = new JMenuItem("View Prescription");
		viewPrescItem.setActionCommand("3");
		viewPrescItem.addActionListener(this);

		addPrescItem = new JMenuItem("New Prescription");
		addPrescItem.setActionCommand("4");
		addPrescItem.addActionListener(this);

		prescMenu.add(viewPrescItem);
		prescMenu.add(addPrescItem);

		menuBar.add(stockDataMenu);
		menuBar.add(prescMenu);
		menuBar.add(logoutMenu);

		this.setJMenuBar(menuBar);
	}

	private void createComponents() {
		HomePanel homePanel = new HomePanel();
		getContentPane().add(homePanel, BorderLayout.CENTER);

		ViewStockPanel stockDataPanel = new ViewStockPanel();
		AddStockPanel addStockPanel = new AddStockPanel();
		ViewPrescPanel viewPrescPanel = new ViewPrescPanel();
		AddPrescPanel addPrescPanel = new AddPrescPanel();

		register(homePanel);
		register(stockDataPanel);
		register(addStockPanel);
		register(viewPrescPanel);
		register(addPrescPanel);
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
