package ua.nure.cs.petrykin.usermanagement.gui;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import ua.nure.cs.petrykin.usermanagement.domain.User;

public class UserTableModel extends AbstractTableModel {
	private static final String[] COLUMN_NAMES = {"ID","Имя","Фамилия"};
	private static final Class[] COLUMN_CLASSES= {Long.class,String.class,String.class};
	private List users = null;
	
	public UserTableModel(Collection users) {
		this.users = new ArrayList(users);
	}
	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	@Override
	public int getRowCount() {
		return users.size();
	}
	public Class getColumnClass(int column) {
		return COLUMN_CLASSES[column];
	}
	
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		User user = (User) users.get(rowIndex);
		switch(columnIndex) {
		case 0:
			return user.getId();
		case 1:
			return user.getFirstName();
		case 2:
			return user.getLastName();
		}
		return null;
	}

}
