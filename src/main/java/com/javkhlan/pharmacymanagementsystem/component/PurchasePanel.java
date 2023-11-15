package com.javkhlan.pharmacymanagementsystem.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.javkhlan.pharmacymanagementsystem.dao.StockDao;
import com.javkhlan.pharmacymanagementsystem.dao.StockDaoImpl;
import com.javkhlan.pharmacymanagementsystem.model.StockModel;
import com.javkhlan.pharmacymanagementsystem.util.Constants;
import com.javkhlan.pharmacymanagementsystem.util.Observer;

public class PurchasePanel extends JScrollPane implements Observer {
	private static final long serialVersionUID = -9084533462784097783L;
	private ArrayList<StockModel> stockList;
	private DefaultTableModel tableModel;
	private JTable stockTable;
	private StockDao stockDao;

	public PurchasePanel() {
		stockDao = new StockDaoImpl();
		this.stockList = new ArrayList<StockModel>();

		tableModel = new DefaultTableModel();
		tableModel.addColumn("ID");
		tableModel.addColumn("Name");
		tableModel.addColumn("Category");
		tableModel.addColumn("Description");
		tableModel.addColumn("Company");
		tableModel.addColumn("Supplier");
		tableModel.addColumn("Quantity");
		tableModel.addColumn("Cost");
		tableModel.addColumn("Status");
		tableModel.addColumn("Date");
		tableModel.addColumn("Buy");
		getDataToTable();
	}

	public void getDataToTable() {
		stockList = stockDao.getStocks();
		setTable();
	}

	public void setTable() {
		tableModel.setRowCount(0);
		for (StockModel stock : this.stockList) {
			JButton deleteBtn = new JButton("Buy");
			deleteBtn.setOpaque(true);
			deleteBtn.setBackground(Constants.primaryColor);
			deleteBtn.setForeground(Color.white);
			deleteBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (stock.getQuantity() > 0) {
						int dialogButton = JOptionPane.YES_NO_OPTION;
						int dialogResult = JOptionPane.showConfirmDialog(null,
								"Are you sure you want to buy this medicine?", "Warning", dialogButton);

						if (dialogResult == JOptionPane.YES_OPTION) {
							StockModel item = stockList.get(stockTable.getSelectedRow());
							stockDao.purchaseFromStock(item.getId());
							getDataToTable();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Out of Stock", "Buy Error", JOptionPane.WARNING_MESSAGE);
					}
				}
			});

			tableModel.addRow(new Object[] { stock.getId(), stock.getName(), stock.getCategory(),
					stock.getDescription(), stock.getCompany(), stock.getSupplier(), stock.getQuantity(),
					stock.getUnitCost(), stock.getStatus(), stock.getDate(), deleteBtn });
		}

		stockTable = new JTable(tableModel) {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		stockTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
		stockTable.addMouseListener(new JTableButtonMouseListener(stockTable));
		TableCellRenderer buttonRenderer = new JTableButtonRenderer();
		stockTable.getColumn("Buy").setCellRenderer(buttonRenderer);
		setViewportView(stockTable);
	}

	@Override
	public void update() {
		tableModel.setRowCount(0);
		getDataToTable();
		tableModel.fireTableDataChanged();
	}
}
