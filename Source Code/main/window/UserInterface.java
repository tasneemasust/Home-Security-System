package main.window;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import dataObjects.Constants;

/**
 * Initial class, the system starts from here.
 * I has two parts loing and admin panel. If 
 * login is successful it redirects user to 
 * admin panel.
 *
 */
public class UserInterface {

	private JFrame frame;
	private CardLayout cards;
	private JPanel cardPanel;
	private static UserInterface window;

	private AdminPanel adminPanel;
	private SystemLogin stemLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					window = new UserInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		frame.setSize(4 * width / 5, height);
		frame.setLocationRelativeTo(null);

		frame.setTitle("SoSafe Home Security System");
		frame.getContentPane().setLayout(new BorderLayout());

		// createSidePanel();
		createCardLayout();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Creates the card layout containing login and admin panel
	 */
	private void createCardLayout() {
		cards = new CardLayout();
		cardPanel = new JPanel();

		// set the card layout
		cardPanel.setLayout(cards);

		createAndAddSystemLogin();
		createAndAddAdminPanel();
		frame.getContentPane().add(cardPanel, BorderLayout.CENTER);

	}

	/**
	 * Creates and adds the system login card
	 */
	public void createAndAddSystemLogin() {
		stemLogin = new SystemLogin();
		stemLogin.setParentCard(cardPanel, cards);
		cardPanel.add(stemLogin, Constants.SYSTEM_LOGIN);

	}

	/**
	 * Creates and adds the admin panel
	 */
	public void createAndAddAdminPanel() {
		adminPanel = new AdminPanel();
		adminPanel.setParentCard(cardPanel, cards);
		cardPanel.add(adminPanel, Constants.ADMIN_PANEL);

	}

	public static UserInterface getWindow() {
		return window;
	}

	/**
	 * Shows the login panel
	 */
	public void showCardPanel() {
		cards.show(cardPanel, Constants.SYSTEM_LOGIN);
	}

}
