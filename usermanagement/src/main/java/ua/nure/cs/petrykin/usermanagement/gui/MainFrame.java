package ua.nure.cs.petrykin.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ua.nure.cs.petrykin.usermanagement.db.DaoFactory;
import ua.nure.cs.petrykin.usermanagement.domain.User;
import ua.nure.cs.petrykin.usermanagement.util.Messages;
import ua.nure.cs.petrykin.usermanagement.db.Dao;

public class MainFrame extends JFrame {
	
	private static final int FRAME_HEIGHT = 600;
	private static final int FRAME_WIDTH = 800;
	private JPanel contentPanel;
//	private BrowsePanel browsePanel;
	private JPanel browsePanel;
	private AddPanel addPanel;
	private Dao<User> dao;
	
	public MainFrame() {
		super();
		dao = DaoFactory.getInstance().getUserDao();
		initialize();
	}
	
	public Dao<User> getDao() {
		return dao;
	}
	
	private void initialize() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setTitle(Messages.getString("MainFrame.user_management")); //$NON-NLS-1$
		this.setContentPane(getContentPanel());
	}
	
	private JPanel getContentPanel() {
		if(contentPanel == null) {
			contentPanel = new JPanel();
			contentPanel.setLayout(new BorderLayout());
			contentPanel.add(getBrowsePanel(), BorderLayout.CENTER);
		}
		return contentPanel;
	}
	private JPanel getBrowsePanel() {
		if(browsePanel == null) {
			browsePanel = new BrowsePanel(this);
		}
		((BrowsePanel)browsePanel).initTable();
		return browsePanel;
	}
	
	public void showAddPanel() {
		showPanel(getAddPanel());
	}//done
	
	private AddPanel getAddPanel() {
		if(addPanel == null) {
			addPanel = new AddPanel(this);
		}
		return addPanel;
	}//done
	
	public void showBrowsePanel() {
        showPanel(getBrowsePanel());
    }

	private void showPanel(JPanel panel) {
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setVisible(true);
		panel.repaint();
	}//done

	
	public static void main(String[]args) {
		MainFrame frame = new MainFrame();
		frame.setVisible(true);
	}
}