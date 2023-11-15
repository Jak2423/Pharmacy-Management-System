package com.javkhlan.pharmacymanagementsystem.component;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.javkhlan.pharmacymanagementsystem.dao.UserDao;
import com.javkhlan.pharmacymanagementsystem.dao.UserDaoImpl;
import com.javkhlan.pharmacymanagementsystem.model.UserModel;
import com.javkhlan.pharmacymanagementsystem.util.Observer;

public class ViewAllUsersPanel extends JScrollPane implements Observer {
	private static final long serialVersionUID = -5122069323335228887L;
	private ArrayList<UserModel> userList;
	private DefaultTableModel tableModel;
	private JTable userTable;
	private UserDao userDao;

	public ViewAllUsersPanel() {
		this.userDao = new UserDaoImpl();
		this.userList = new ArrayList<UserModel>();

		tableModel = new DefaultTableModel();
		tableModel.addColumn("ID");
		tableModel.addColumn("Staff ID");
		tableModel.addColumn("Role");
		tableModel.addColumn("First Name");
		tableModel.addColumn("Last Name");
		tableModel.addColumn("Address");
		tableModel.addColumn("Phone");
		tableModel.addColumn("Email");
		tableModel.addColumn("Username");
		getDataToTable();
	}

	public void getDataToTable() {
		this.userList = userDao.getUsers();
		setTable();
	}

	public void setTable() {
		tableModel.setRowCount(0);
		for (UserModel user : this.userList) {

			tableModel.addRow(new Object[] { user.getId(), user.getStaffId(), user.getRole(), user.getFirstName(),
					user.getLastName(), user.getAddress(), user.getPhone(), user.getEmail(), user.getUsername() });
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
		setViewportView(userTable);
	}

	@Override
	public void update() {
		tableModel.setRowCount(0);
		getDataToTable();
		tableModel.fireTableDataChanged();
	}
}
