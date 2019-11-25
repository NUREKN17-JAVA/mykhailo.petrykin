package ua.nure.cs.pertykin.usermanagement.gui;

import java.awt.Component;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.mockobjects.dynamic.Mock;

import javax.swing.Box.Filler;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.TestHelper;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.eventdata.StringEventData;
import junit.extensions.jfcunit.finder.NamedComponentFinder;
import ua.nure.cs.petrykin.usermanagement.db.DaoFactory;
import ua.nure.cs.petrykin.usermanagement.db.DaoFactoryImpl;
import ua.nure.cs.petrykin.usermanagement.db.MockDaoFactory;
import ua.nure.cs.petrykin.usermanagement.db.MockUserDao;
import ua.nure.cs.petrykin.usermanagement.domain.User;
import ua.nure.cs.petrykin.usermanagement.gui.MainFrame;
import ua.nure.cs.petrykin.usermanagement.util.Messages;

public class MainFrameTest extends JFCTestCase {
	private static final String USER_TABLE_MODEL_LAST_NAME = "UserTableModel.last_name";
	private static final String USER_TABLE_MODEL_FIRST_NAME = "UserTableModel.first_name";
	private static final String USER_TABLE_MODEL_ID = "UserTableModel.id";
	
	private static final String DATE_OF_BIRTH_FIELD_COMPONENT_NAME = "dateOfBirthField";
	private static final String LAST_NAME_FIELD_COMPONENT_NAME = "lastNameField";
	private static final String FIRST_NAME_FIELD_COMPONENT_NAME = "firstNameField";
	
	private static final String LAST_NAME = "Doe";
	private static final String FIRST_NAME = "John";
	private static final Date DATE_OF_BIRTH = new Date();
	
	private static final String BROWSE_PANEL_COMPONENT_NAME = "browsePanel";
	private static final String USER_TABLE_COMPONENT_NAME = "userTable";
	private static final String ADD_PANEL_COMPONENT_NAME = "addPanel";
	
	private static final String OK_BUTTON_COMPONENT_NAME = "okButton";
	private static final String DELETE_BUTTON_COMPONENT_NAME = "deleteButton";
	private static final String DETAILS_BUTTON_COMPONENT_NAME = "detailsButton";
	private static final String CANCEL_BUTTON_COMPONENT_NAME = "cancelButton";
	private static final String EDIT_BUTTON_COMPONENT_NAME = "editButton";
	private static final String ADD_BUTTON_COMPONENT_NAME = "addButton";
	private static final int NUMBER_OF_COLUMNS_IN_USER_TABLE = 3;
	private static final int NUMBER_OF_ROWS_ADD_TEST = 2;
	
	private MainFrame mainFrame;
	private Mock mockUserDao;
	private ArrayList<User> users;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		try {
		Properties properties = new Properties();

		properties.setProperty("dao.Factory", MockDaoFactory.class.getName());
		DaoFactory.init(properties);
		mockUserDao =((MockDaoFactory) DaoFactory.getInstance()).getMockUserDao();
		
		User expectedUser = new User(new Long(1003),"Bill","Gates",new Date());
		
		users = new ArrayList<User>();
		users.add(expectedUser);
		
		mockUserDao.expectAndReturn("findAll", users);
		setHelper(new JFCTestHelper());
		mainFrame = new MainFrame();
		}catch(Exception e) {
			e.printStackTrace();
		}
		mainFrame.setVisible(true);
	}

	@Override
	protected void tearDown() throws Exception {
		try {
				super.tearDown();
				mockUserDao.verify();
//				mainFrame.setVisible(false);
				getHelper();
				TestHelper.cleanUp(this);
		} catch (Exception e){
			e.printStackTrace();
			}
	}//done
	
	private Component find(Class<?> componentClass, String name) {
		NamedComponentFinder finder;
		finder = new NamedComponentFinder(componentClass, name);
		finder.setWait(0);
		Component component = finder.find(mainFrame, 0);
		assertNotNull("Could not find component '" + name + "'", component);
		return component;
	}//done
	
	public void testBrowseTablePanel() {
		JTable table = (JTable) find(JTable.class, USER_TABLE_COMPONENT_NAME);
		find(JPanel.class, BROWSE_PANEL_COMPONENT_NAME);
		assertEquals(NUMBER_OF_COLUMNS_IN_USER_TABLE, table.getColumnCount());
		assertEquals(Messages.getString(USER_TABLE_MODEL_ID), table.getColumnName(0));
		assertEquals(Messages.getString(USER_TABLE_MODEL_FIRST_NAME), table.getColumnName(1));
		assertEquals(Messages.getString(USER_TABLE_MODEL_LAST_NAME), table.getColumnName(2));
		find(JButton.class, ADD_BUTTON_COMPONENT_NAME);
		find(JButton.class, EDIT_BUTTON_COMPONENT_NAME);
		find(JButton.class, DETAILS_BUTTON_COMPONENT_NAME);
		find(JButton.class, DELETE_BUTTON_COMPONENT_NAME);
		find(JTable.class,USER_TABLE_COMPONENT_NAME);
	}
	
	public void testAddUser() {
		User user = new User(FIRST_NAME,LAST_NAME,DATE_OF_BIRTH);
		User expectedUser = new User(new Long(1),FIRST_NAME,LAST_NAME,DATE_OF_BIRTH);
		mockUserDao.expectAndReturn("create", user, expectedUser);
		ArrayList<User> users = new ArrayList<User>(this.users);
		users.add(expectedUser);
		mockUserDao.expectAndReturn("findAll",users);
		JTable table = (JTable) find(JTable.class, USER_TABLE_COMPONENT_NAME);
		JButton addButton = (JButton) find(JButton.class, ADD_BUTTON_COMPONENT_NAME);
		getHelper().enterClickAndLeave(new MouseEventData(this,addButton));
		find(JPanel.class, ADD_PANEL_COMPONENT_NAME);
		fillFields(FIRST_NAME, LAST_NAME, DATE_OF_BIRTH);
		JButton okButton = (JButton) find(JButton.class,OK_BUTTON_COMPONENT_NAME);
		getHelper().enterClickAndLeave(new MouseEventData(this,okButton));
		find(JPanel.class,BROWSE_PANEL_COMPONENT_NAME);
		table = (JTable) find(JTable.class,USER_TABLE_COMPONENT_NAME);
		assertEquals(NUMBER_OF_ROWS_ADD_TEST,table.getRowCount());
	}
	public void testEditUser() {
		
	}
	public void testDeleteUser() {
		
	}
	private void fillFields(String firstName, String lastName, Date dateOfBirth) {
		JTextField firtNameField = (JTextField) find(JTextField.class, FIRST_NAME_FIELD_COMPONENT_NAME);
		JTextField lastNameField = (JTextField) find(JTextField.class, LAST_NAME_FIELD_COMPONENT_NAME);
		JTextField dateOfBirthField = (JTextField) find(JTextField.class, DATE_OF_BIRTH_FIELD_COMPONENT_NAME);
		
		getHelper().sendString(new StringEventData(this, firtNameField, firstName));
		getHelper().sendString(new StringEventData(this, lastNameField, lastName));
		DateFormat formatter = DateFormat.getDateInstance();
		String date = formatter.format(dateOfBirth);
		getHelper().sendString(new StringEventData(this, dateOfBirthField, date));
	}
	
}