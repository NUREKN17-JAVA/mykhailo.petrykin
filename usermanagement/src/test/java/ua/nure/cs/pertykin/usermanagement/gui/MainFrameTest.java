package ua.nure.cs.pertykin.usermanagement.gui;

import java.awt.Component;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Box.Filler;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.TestHelper;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.eventdata.StringEventData;
import junit.extensions.jfcunit.finder.NamedComponentFinder;
import ua.nure.cs.petrykin.usermanagement.gui.MainFrame;

public class MainFrameTest extends JFCTestCase {
	private static final String DATE_OF_BIRTH_FIELD_COMPONENT_NAME = "dateOfBirthField";
	private static final String LAST_NAME_FIELD_COMPONENT_NAME = "lastNameField";
	private static final String FIRST_NAME_FIELD_COMPONENT_NAME = "firstNameField";
	private static final String BROWSE_PANEL_COMPONENT_NAME = "browsePanel";
	private static final String OK_BUTTON_COMPONENT_NAME = "okButton";
	private static final Date DATE_OF_BIRTH = new Date();
	private static final String LAST_NAME = "Doe";
	private static final String FIRST_NAME = "John";
	private static final String ADD_PANEL_COMPONENT_NAME = "addPanel";
	private static final String DELETE_BUTTON_COMPONENT_NAME = "deleteButton";
	private static final String DETAILS_BUTTON_COMPONENT_NAME = "detailsButton";
	private static final String EDIT_BUTTON_COMPONENT_NAME = "editButton";
	private static final String ADD_BUTTON_COMPONENT_NAME = "addButton";
	private static final int NUMBER_OF_COLUMNS_IN_USER_TABLE = 3;
	private MainFrame mainFrame;

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		setHelper(new JFCTestHelper());
		mainFrame = new MainFrame();
		mainFrame.setVisible(true);
	}

	@Override
	protected void tearDown() throws Exception {
		mainFrame.setVisible(false);
		getHelper();
		TestHelper.cleanUp(this);
		super.tearDown();
		
	}
	
	private Component find(Class<?> componentClass, String name) {
		NamedComponentFinder finder;
		finder = new NamedComponentFinder(componentClass, name);
		finder.setWait(0);
		Component component = finder.find(mainFrame, 0);
		assertNotNull("Could not find component '" + name + "'", component);
		return component;
	}
	
	public void testBrowsePanel() {
		JTable table = (JTable) find(JTable.class, "userTable");
		find(JPanel.class, BROWSE_PANEL_COMPONENT_NAME);
		//assertEquals(NUMBER_OF_COLUMNS_IN_USER_TABLE, table.getColumnCount());
		//assertEquals("ID", table.getColumnName(0));
		//assertEquals("First Name", table.getColumnName(1));
		//assertEquals("Last Name", table.getColumnName(2));
		find(JButton.class, ADD_BUTTON_COMPONENT_NAME);
		find(JButton.class, EDIT_BUTTON_COMPONENT_NAME);
		find(JButton.class, DETAILS_BUTTON_COMPONENT_NAME);
		find(JButton.class, DELETE_BUTTON_COMPONENT_NAME);

	}
	
	public void testAddUser() {
		JButton addButton = (JButton) find(JButton.class, ADD_BUTTON_COMPONENT_NAME);
		getHelper().enterClickAndLeave(new MouseEventData(this, addButton));
		
		find(JPanel.class, ADD_PANEL_COMPONENT_NAME);
		fillFields(FIRST_NAME, LAST_NAME, DATE_OF_BIRTH);
		
		JButton okButton =(JButton) find(JButton.class, OK_BUTTON_COMPONENT_NAME);
		getHelper().enterClickAndLeave(new MouseEventData(this, okButton));
		
		find(JPanel.class, BROWSE_PANEL_COMPONENT_NAME);
		
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