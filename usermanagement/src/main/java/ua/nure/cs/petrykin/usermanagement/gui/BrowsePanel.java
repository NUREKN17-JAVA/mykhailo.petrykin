package ua.nure.cs.petrykin.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

import ua.nure.cs.petrykin.usermanagement.db.DatabaseException;
import ua.nure.cs.petrykin.usermanagement.util.Messages;

public class BrowsePanel extends JPanel implements ActionListener {

	private static final String ADD_COMMAND = "add"; //$NON-NLS-1$
	private static final String EDIT_COMMAND = "edit"; //$NON-NLS-1$
	private static final String DELETE_COMMAND = "delete"; //$NON-NLS-1$
	private MainFrame parent;
	private JScrollPane tablePanel;
	private JTable userTable;
	private JPanel buttonPanel;
	private JButton addButton;
	private JButton editButton;
	private JButton deleteButton;
	private JButton detailsButton;
	
	private static final String DELETE_BUTTON_COMPONENT_NAME = "deleteButton"; //$NON-NLS-1$
	private static final String DETAILS_BUTTON_COMPONENT_NAME = "detailsButton"; //$NON-NLS-1$
	private static final String EDIT_BUTTON_COMPONENT_NAME = "editButton"; //$NON-NLS-1$
	private static final String ADD_BUTTON_COMPONENT_NAME = "addButton"; //$NON-NLS-1$

	public BrowsePanel(MainFrame mainFrame) {
		parent = mainFrame;
		initialize();
	}//done

	private void initialize() {
		this.setName("browsePanel");// non-localized(No need to do localization) //$NON-NLS-1$
		this.setLayout(new BorderLayout());
		this.add(getTablePanel(), BorderLayout.CENTER);
		this.add(getButtonsPanel(), BorderLayout.SOUTH);
	}//done

	private JPanel getButtonsPanel() {
		if(buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.add(getAddButton(),null);//WE will implement one in class, others I have to do at home
			buttonPanel.add(getEditButton(),null);
			buttonPanel.add(getDeleteButton(),null);
			buttonPanel.add(getDetailsButton(),null);

		}
		return buttonPanel;
	} //done

	private JButton getAddButton() {
		if(addButton == null) {
			addButton = new JButton();
			addButton.setText(Messages.getString("BrowsePanel.add"));//has to be localized //$NON-NLS-1$
			addButton.setName(ADD_BUTTON_COMPONENT_NAME);
			addButton.setActionCommand(ADD_COMMAND);//non-localize
			addButton.addActionListener(this);
		}
		return addButton;
	}//done

	
	private JButton getEditButton() {
		if(editButton == null) {
			editButton = new JButton();
			editButton.setText(Messages.getString("BrowsePanel.edit"));//has to be localized //$NON-NLS-1$
			editButton.setName(EDIT_BUTTON_COMPONENT_NAME);
			editButton.setActionCommand(EDIT_COMMAND);//non-localize
			editButton.addActionListener(this);
		}
		return editButton;
	}//done
	
	
	private JButton getDeleteButton() {
		if(deleteButton == null) {
			deleteButton = new JButton();
			deleteButton.setText(Messages.getString("BrowsePanel.delete"));//has to be localized //$NON-NLS-1$
			deleteButton.setName(DELETE_BUTTON_COMPONENT_NAME);
			deleteButton.setActionCommand(EDIT_COMMAND);//non-localize
			deleteButton.addActionListener(this);
		}
		return deleteButton;
	}//done
	
	
	private JButton getDetailsButton() {
		if(detailsButton == null) {
			detailsButton = new JButton();
			detailsButton.setText(Messages.getString("BrowsePanel.details"));//has to be localized //$NON-NLS-1$
			detailsButton.setName(DETAILS_BUTTON_COMPONENT_NAME);
			detailsButton.setActionCommand("details");//non-localize //$NON-NLS-1$
			detailsButton.addActionListener(this);
		}
		return detailsButton;
	}//done

	
	
	private JScrollPane getTablePanel() {
		if(tablePanel == null) {
			tablePanel = new JScrollPane(getUserTable());
			//tablePanel.setName("userTable"); //non-localize
		}
		return tablePanel;
	}//done

	private JTable getUserTable() {
		if(userTable == null) {
			userTable = new JTable();
			userTable.setName("userTable"); //non-localize //$NON-NLS-1$
		}
		initTable();
		return userTable;
	}//done

	public void initTable() {
		UserTableModel model;
		try {
			model = new UserTableModel(parent.getDao().findAll());
		} catch (DatabaseException e) {
			model = new UserTableModel(new ArrayList());
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", 
					JOptionPane.ERROR_MESSAGE);
		}
		userTable.setModel(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String acctionCommand = e.getActionCommand();
		if(ADD_COMMAND.equalsIgnoreCase(acctionCommand)) {
			this.setVisible(false);
			parent.showAddPanel();
		} 
//		else if ("edit".equalsIgnoreCase(actionCommand)) { //$NON-NLS-1$
//            int selectedRow = userTable.getSelectedRow();
//            if (selectedRow == -1) {
//                JOptionPane.showMessageDialog(this, "Select a user, please",
//                        "Edit user", JOptionPane.INFORMATION_MESSAGE);
//                return;
//            }
//            User user = ((UserTableModel) userTable.getModel())
//                    .getUser(selectedRow);
//            this.setVisible(false);
//            parent.showEditPanel(user);
//        }
//		else if (DELETE_COMMAND.equalsIgnoreCase(actionCommand)) { //$NON-NLS-1$
//            int selectedRow = userTable.getSelectedRow();
//            if (selectedRow == -1) {
//                JOptionPane.showMessageDialog(this, "Select a user, please",
//                        "Edit user", JOptionPane.INFORMATION_MESSAGE);
//                return;
//            }
//            try {
//                parent.getUserDao().delete(
//                        ((UserTableModel) userTable.getModel())
//                                .getUser(selectedRow));
//            } catch (DatabaseException e1) {
//                JOptionPane.showMessageDialog(this, e1.getMessage(), "Error",
//                        JOptionPane.ERROR_MESSAGE);
//            }
//            initTable();
//            return;
//        }
		
	}
}