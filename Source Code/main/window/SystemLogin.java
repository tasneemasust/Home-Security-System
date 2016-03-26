package main.window;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DBConnection.LoginRegisteration;
import dataObjects.Constants;

/**
 * Log in window for admin login.
 *
 */
public class SystemLogin extends JPanel {

	private CardLayout cards;
	private JPanel cardPanel;
	private JLabel user;
	private JLabel password;
	private JButton login;
	private JButton forgot;
	private JButton register;
	private JButton resetButton;
	private JButton goBack;

	private JTextField adminUserId;
	private JPasswordField adminPwd;
	private JPanel adminLogin;

	private JPanel registrationCard;
	private RegistrationPanel registrationPanel;
	private JButton mainButton;
	private JPanel parentCardPanel;
	private CardLayout parentCardLayout;
	private JLabel adminErrorLabel;

	/**
	 * Creates a panel for logging into the system
	 */
	public SystemLogin() {

		this.setLayout(new BorderLayout());

		cards = new CardLayout();
		cardPanel = new JPanel();
		cardPanel.setLayout(cards);

		createAdminLogin();
		registrationPanel = new RegistrationPanel();
		registrationPanel.setParentCard(cardPanel, cards);
		registrationPanel.createRegistrationCard();

		// Component adminLogin = null;
		cardPanel.add(adminLogin, Constants.ADMIN_LOGIN);
		cardPanel.add(registrationPanel, Constants.REGISTER_CARD);

		add(cardPanel, BorderLayout.CENTER);
	}

	/**
	 * Validates login information by checking if username or password is empty, 
	 * else returns error information
	 * @param userId
	 * @param password
	 * @return String
	 */
	private String validateLogin(String userId, String password) {
		String error = "";
		System.out.println("In here::" + userId);
		if (userId.equals("")) {
			System.out.println("In if");
			error = "User name cannot be empty";
		} else if (password.equals("") || password == null) {
			error = "Password cannot be empty";
		}
		return error;
	}

	/**
	 * Creates the admin login panel
	 */
	private void createAdminLogin() {
		adminLogin = new JPanel();
		adminLogin.setBackground(Color.ORANGE);

		adminLogin.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;

		JLabel adminLabel = new JLabel("Admin Login");
		adminLabel.setFont(new Font(null, Font.BOLD, 14));
		adminLogin.add(adminLabel, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		user = new JLabel("User ID: ");
		adminLogin.add(user, gbc);

		gbc.gridx++;
		adminUserId = new JTextField(20);
		adminLogin.add(adminUserId, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		password = new JLabel("Password: ");
		adminLogin.add(password, gbc);

		gbc.gridx++;
		adminPwd = new JPasswordField(20);
		adminLogin.add(adminPwd, gbc);

		gbc.gridy++;

		login = new JButton("Login");
		login.setPreferredSize(new Dimension(120, 30));
		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// check if login correct
				String error = validateLogin(adminUserId.getText(), new String(
						adminPwd.getPassword()));
				if (error.equals("")) {
					if (LoginRegisteration.checkAdminLogin(
							adminUserId.getText(),
							new String(adminPwd.getPassword()))) {
						adminErrorLabel.setText("");
						// login successful
						// go to admin panel
						LoginAction();
					} else {
						adminErrorLabel.setText("Invalid admin login");
					}
				} else {
					adminErrorLabel.setText(error);
				}
			}
		});
		adminLogin.add(login, gbc);

		gbc.gridy++;

		register = new JButton("Register");
		register.setPreferredSize(new Dimension(120, 30));
		register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				cards.show(cardPanel, Constants.REGISTER_CARD);
			}
		});
		adminLogin.add(register, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		adminErrorLabel = new JLabel();
		adminErrorLabel.setForeground(Color.RED);
		adminLogin.add(adminErrorLabel, gbc);
	}
	
	/**
	 * Sets the parent card layout and parent card panel
	 * @param cardPanel
	 * @param cards2
	 */
	public void setParentCard(JPanel cardPanel, CardLayout cards2) {
		parentCardPanel = cardPanel;
		parentCardLayout = cards2;
	}

	/**
	 * Shows the admin panel on successful login
	 */
	private void LoginAction() {
		Constants.adminUserId = adminUserId.getText();
		parentCardLayout.show(parentCardPanel, Constants.ADMIN_PANEL);
	}

}
