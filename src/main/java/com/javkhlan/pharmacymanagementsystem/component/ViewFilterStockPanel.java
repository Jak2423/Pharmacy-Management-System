package com.javkhlan.pharmacymanagementsystem.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.javkhlan.pharmacymanagementsystem.dao.StockDao;
import com.javkhlan.pharmacymanagementsystem.dao.StockDaoImpl;
import com.javkhlan.pharmacymanagementsystem.model.StockModel;
import com.javkhlan.pharmacymanagementsystem.util.Observer;

public class ViewFilterStockPanel extends JPanel implements Observer, ActionListener {

	private static final long serialVersionUID = 1L;
	private StockDao stockDao;
	private ArrayList<StockModel> stockList;
	private ViewStockPanel stockDataPanel;
	private JPanel searchPanel;
	private JTextField searchField;
	private JButton searchButton;

	public ViewFilterStockPanel() {
		super(new BorderLayout());
		setPreferredSize(new Dimension(900, 600));
		stockDao = new StockDaoImpl();
		stockList = stockDao.getStocks();
		stockDataPanel = new ViewStockPanel();

		searchButton = new JButton("Search");
		searchButton.setAlignmentY(0.0f);
		searchButton.setPreferredSize(new Dimension(100, 40));
		searchButton.setBackground(Color.lightGray);
		searchButton.setBorderPainted(false);
		searchButton.setFocusable(false);
		searchButton.setOpaque(true);
		searchButton.addActionListener(this);
		searchButton.addMouseListener(mouseAdapter);

		searchField = new JTextField();
		searchField.setAlignmentY(0.0f);
		searchField.setAlignmentX(0.0f);
		Border lineBorder = new LineBorder(Color.BLACK);
		Border paddingBorder = new EmptyBorder(5, 5, 5, 5);
		Border compoundBorder = BorderFactory.createCompoundBorder(lineBorder, paddingBorder);

		searchField.setPreferredSize(new Dimension(300, 40));
		searchField.setBorder(compoundBorder);
		searchPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) searchPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		searchPanel.add(searchField);
		searchPanel.add(searchButton);

		add(searchPanel, BorderLayout.NORTH);
		add(stockDataPanel, BorderLayout.CENTER);
	}

	MouseAdapter mouseAdapter = new MouseAdapter() {
		@Override
		public void mouseEntered(MouseEvent event) {
			if (event.getSource().equals(searchButton)) {
				searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				searchButton.setBackground(Color.gray);
			}
		}

		@Override
		public void mouseExited(MouseEvent event) {
			if (event.getSource().equals(searchButton)) {
				searchButton.setCursor(Cursor.getDefaultCursor());
				searchButton.setBackground(Color.lightGray);
			}
		}
	};

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(searchButton)) {
			String drugName = searchField.getText();
			if (!drugName.isEmpty()) {
				stockList = stockDao.getStockByName(drugName);
				stockDataPanel.refresh(stockList);
			} else {
				stockList = stockDao.getStocks();
				stockDataPanel.refresh(stockList);
			}
		}

	}

	@Override
	public void update() {

	}

}
