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
	private BrowsePanel browsePanel;
	private AddPanel addPanel;
	
	public MainFrame() {
		super();
		initialize();
	}

	private void initialize() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setTitle(Messages.getString("MainFrame.user_management")); //$NON-NLS-1$
		this.setContentPane(getContentPanel());
	}
	
	
	public void showAddPanel() {
		showPanel(getAddPanel());
	}//done
	

	private void showPanel(JPanel panel) {
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setVisible(true);
		panel.repaint();
	}//done

	private AddPanel getAddPanel() {
		if(addPanel == null) {
			addPanel = new AddPanel(this);
		}
		return addPanel;
	}//done

	private JPanel getContentPanel() {
		if(contentPanel == null) {
			contentPanel = new JPanel();
			contentPanel.setLayout(new BorderLayout());
			contentPanel.add(getBrowsePanel(), BorderLayout.CENTER);
		}
		return contentPanel;
	}
	

	private BrowsePanel getBrowsePanel() {
		if(browsePanel == null) {
			browsePanel = new BrowsePanel(this);
		}
		return browsePanel;
	}
	public static void main(String[]args) {
		MainFrame frame = new MainFrame();
		frame.setVisible(true);
	}
}