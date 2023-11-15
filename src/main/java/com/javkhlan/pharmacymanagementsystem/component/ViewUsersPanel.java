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

import com.javkhlan.pharmacymanagementsystem.dao.UserDao;
import com.javkhlan.pharmacymanagementsystem.dao.UserDaoImpl;
import com.javkhlan.pharmacymanagementsystem.model.UserModel;
import com.javkhlan.pharmacymanagementsystem.util.Constants;
import com.javkhlan.pharmacymanagementsystem.util.Observer;

public class ViewUsersPanel extends JScrollPane implements Observer {

	private static final long serialVersionUID = 7291289594458417600L;

	private ArrayList<UserModel> userList;
	private DefaultTableModel tableModel;
	private JTable userTable;
	private String role;
	private UserDao userDao;

	public ViewUsersPanel(String role) {
		this.userDao = new UserDaoImpl();
		this.role = role;
		this.userList = new ArrayList<UserModel>();

		tableModel = new DefaultTableModel();
		tableModel.addColumn("ID");
		tableModel.addColumn("Staff ID");
		tableModel.addColumn("First Name");
		tableModel.addColumn("Last Name");
		tableModel.addColumn("Address");
		tableModel.addColumn("Phone");
		tableModel.addColumn("Email");
		tableModel.addColumn("Username");
		tableModel.addColumn("Edit");
		tableModel.addColumn("Delete");
		getDataToTable();
	}

	public void getDataToTable() {
		this.userList = userDao.getUserByRole(role);
		setTable();
	}

	public void setTable() {
		tableModel.setRowCount(0);
		for (UserModel user : this.userList) {
			JButton editBtn = new JButton("Edit");
			editBtn.setOpaque(true);
			editBtn.setBackground(Constants.blueColor);
			editBtn.setForeground(Color.white);
			editBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new EditUserFrame(userList.get(userTable.getSelectedRow()), (Observer) ViewUsersPanel.this);

				}
			});

			JButton deleteBtn = new JButton("Delete");
			deleteBtn.setOpaque(true);
			deleteBtn.setBackground(Constants.redColor);
			deleteBtn.setForeground(Color.white);

			deleteBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this user?",
							"Warning", dialogButton);

					if (dialogResult == JOptionPane.YES_OPTION) {
						UserModel item = userList.get(userTable.getSelectedRow());
						userDao.deleteUser(item.getId());
						tableModel.setRowCount(0);
						getDataToTable();
					}
				}
			});

			tableModel.addRow(new Object[] { user.getId(), user.getStaffId(), user.getFirstName(), user.getLastName(),
					user.getAddress(), user.getPhone(), user.getEmail(), user.getUsername(), editBtn, deleteBtn });
		}

		userTable = new JTable(tableModel) {
			private static final long serialVersionUID = -2972724600210803125L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		userTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
		userTable.setFillsViewportHeight(false);
		userTable.addMouseListener(new JTableButtonMouseListener(userTable));
		TableCellRenderer buttonRenderer = new JTableButtonRenderer();
		userTable.getColumn("Edit").setCellRenderer(buttonRenderer);
		userTable.getColumn("Delete").setCellRenderer(buttonRenderer);
		setViewportView(userTable);
	}

	@Override
	public void update() {
		tableModel.setRowCount(0);
		getDataToTable();
		tableModel.fireTableDataChanged();
	}

}
