package simulation;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;

import dataObjects.Constants;

/**
 * This is a CardLayout that contains login and device 
 * window. default card is login, if login is correct 
 * it forwards the user in the device window.
 * 
 * @author Rahmi
 *
 */
public class LoginWindow extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static ActiveLog activeLog;
	private CardLayout cards;
	private JPanel cardPanel;
	private LoginCard loginPanel;
	private DeviceWindow deviceWindow;

	public LoginWindow() {

		this.setLayout(new BorderLayout());

		createCardPanel();
		createLoginPanel();
		addDeviceWindowPanel();
	}

	/**
	 * create CardLayout with login
	 */
	private void createCardPanel() {
		cardPanel = new JPanel();
		cards = new CardLayout();
		cardPanel.setLayout(cards);
		cards.show(cardPanel, Constants.LOGIN_CARD);
		this.add(cardPanel, BorderLayout.CENTER);
	}

	/**
	 * create and add login card
	 */
	private void createLoginPanel() {
		loginPanel = new LoginCard();
		loginPanel.setOpaque(false);
		loginPanel.onLogin(cardPanel, cards);
		cardPanel.add(loginPanel, Constants.LOGIN_CARD);
	}

	/**
	 * create and add device window
	 */
	private void addDeviceWindowPanel() {
		deviceWindow = new DeviceWindow();
		deviceWindow.setParentCard(cardPanel, cards);
		deviceWindow.setBackground(Color.ORANGE);

		cardPanel.add(deviceWindow, Constants.DEVICE);
	}

}
