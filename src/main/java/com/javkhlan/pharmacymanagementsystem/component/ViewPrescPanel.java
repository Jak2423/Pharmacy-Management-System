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

import com.javkhlan.pharmacymanagementsystem.dao.PrescDao;
import com.javkhlan.pharmacymanagementsystem.dao.PrescDaoImpl;
import com.javkhlan.pharmacymanagementsystem.model.Prescription;
import com.javkhlan.pharmacymanagementsystem.util.Constants;
import com.javkhlan.pharmacymanagementsystem.util.Observer;

public class ViewPrescPanel extends JScrollPane implements Observer {

	private static final long serialVersionUID = 2375418368290428012L;

	private ArrayList<Prescription> prescList;
	private DefaultTableModel tableModel;
	private JTable prescTable;
	private PrescDao prescDao;

	public ViewPrescPanel() {
		this.prescDao = new PrescDaoImpl();
		this.prescList = new ArrayList<Prescription>();

		this.tableModel = new DefaultTableModel();
		tableModel.addColumn("Customer");
		tableModel.addColumn("Prescription Nº");
		tableModel.addColumn("Invoice Nº");
		tableModel.addColumn("Date");
		tableModel.addColumn("Delete");
		getDataToTable();
	}

	public void getDataToTable() {
		prescList = prescDao.getPrescriptions();
		setTable();
	}

	public void setTable() {
		tableModel.setRowCount(0);
		for (Prescription presc : this.prescList) {
			JButton deleteBtn = new JButton("Delete");
			deleteBtn.setOpaque(true);
			deleteBtn.setBackground(Constants.redColor);
			deleteBtn.setForeground(Color.white);
			deleteBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog(null,
							"Are you sure you want to delete this prescription?", "Warning", dialogButton);

					if (dialogResult == JOptionPane.YES_OPTION) {
						Prescription item = prescList.get(prescTable.getSelectedRow());
						prescDao.deletePrescription(item.getPresNo());
						tableModel.setRowCount(0);
						getDataToTable();
					}
				}
			});

			tableModel.addRow(new Object[] { presc.getCustomerName(), presc.getPresNo(), presc.getInvoiceNo(),
					presc.getDate(), deleteBtn });
		}

		prescTable = new JTable(tableModel) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		prescTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
		prescTable.addMouseListener(new JTableButtonMouseListener(prescTable));
		TableCellRenderer buttonRenderer = new JTableButtonRenderer();
		prescTable.getColumn("Delete").setCellRenderer(buttonRenderer);
		setViewportView(prescTable);
	}

	@Override
	public void update() {
		tableModel.setRowCount(0);
		getDataToTable();
		tableModel.fireTableDataChanged();
	}

}
