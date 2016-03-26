package simulation;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DBConnection.LoginRegisteration;
import dataObjects.Constants;
import main.window.RegistrationPanel;

/**
 * Manage log in action in the device window.
 *
 */
public class LoginCard extends JPanel {

	private static final long serialVersionUID = 1L;
	private CardLayout cards;
	private JPanel cardPanel;
	private JPanel loginCard;
	private JLabel user;
	private JLabel password;
	private JButton login;
	private JButton forgot;
	private JButton register;

	
	private JButton cancel;
	private Pattern pattern;
	private Matcher matcher;
	private JPanel adminLogin;
	private JLabel loginErrorLabel;
	private JLabel adminErrorLabel;
	private JTextField loginUserId;
	private JPasswordField loginPwd;
	private JTextField adminUserId;
	private JPasswordField adminPwd;
	private JPanel parentCardPanel;
	private CardLayout parentCardLayout;
	private RegistrationPanel registrationPanel;

	/**
	 * constructor, set up the total login window
	 */
	public LoginCard() {
		
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		
		cards = new CardLayout();
		cardPanel = new JPanel();
		
		cardPanel.setOpaque(false);
		// set the card layout
		cardPanel.setLayout(cards);
		cards.show(cardPanel, Constants.LOGIN_CARD);

		createLoginCard();
		registrationPanel = new RegistrationPanel();
		registrationPanel.setParentCard(cardPanel, cards);
		registrationPanel.createRegistrationCard();
		cardPanel.add(registrationPanel, Constants.REGISTER_CARD);
		createAdminLogin();
		cardPanel.add(adminLogin, Constants.ADMIN_LOGIN);
		
		this.add(cardPanel, BorderLayout.CENTER);
	}
	
	/**
	 * set up parent card.
	 * @param cardPanel
	 * @param cards2
	 */
	public void onLogin(JPanel cardPanel, CardLayout cards2){
		parentCardPanel = cardPanel;
		parentCardLayout = cards2;
	}

	/**
	 * create and add text-field and buttons for login 
	 * and registration.
	 */
	private void createLoginCard() {
		loginCard = new JPanel();
		loginCard.setLayout(new GridBagLayout());
		
		loginCard.setBackground(Color.ORANGE);
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		user = new JLabel("User ID: ");
		loginCard.add(user, gbc);

		gbc.gridx++;
		loginUserId = new JTextField(20);
		loginCard.add(loginUserId, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		password = new JLabel("Password: ");
		loginCard.add(password, gbc);

		gbc.gridx++;
		loginPwd = new JPasswordField(20);
		loginCard.add(loginPwd, gbc);

		gbc.gridy++;
		login = new JButton("Login");
		login.setPreferredSize(new Dimension(120,30));
		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// check if login correct
				String error = validateLogin(loginUserId.getText(), new String(
						loginPwd.getPassword()));
				if (error.equals("")) {
					if (LoginRegisteration.checkLogin(loginUserId.getText(),
							new String(loginPwd.getPassword()))) {
						loginErrorLabel.setText("");
						loginUserId.setText("");
						loginPwd.setText("");
						Constants.machineUserId = loginUserId.getText();
						parentCardLayout.show(parentCardPanel, Constants.DEVICE);
					} else {
						loginErrorLabel.setText("Login failed.");
					}
				} else {
					loginErrorLabel.setText(error);
				}
			}
		});
		loginCard.add(login, gbc);

		gbc.gridy++;
		register = new JButton("Register");
		register.setPreferredSize(new Dimension(120,30));
		loginCard.add(register, gbc);
		register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loginUserId.setText("");
				loginPwd.setText("");
				cards.show(cardPanel, Constants.ADMIN_LOGIN);
			}
		});

		gbc.gridy++;
		forgot = new JButton("Forgot ID/Password");
		forgot.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		//loginCard.add(forgot, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		loginErrorLabel = new JLabel();
		loginErrorLabel.setForeground(Color.RED);
		loginCard.add(loginErrorLabel, gbc);

		cardPanel.add(loginCard, Constants.LOGIN_CARD);
	}

	/**
	 * Validate the values entered in the text-fields for
	 * login. 
	 * @param userId
	 * @param password
	 * @return error if any of the field is empty
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
	 * create and add a option for admin login from 
	 * the device window
	 */
	private void createAdminLogin() {
		adminLogin = new JPanel();
		adminLogin.setLayout(new GridBagLayout());

		adminLogin.setBorder(BorderFactory.createCompoundBorder(
				new EmptyBorder(20, 20, 20, 20),
				BorderFactory.createLineBorder(Color.GRAY)));

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

		gbc.gridx = 0;
		gbc.gridy++;
		cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cards.show(cardPanel, Constants.LOGIN_CARD);
				adminUserId.setText("");
				adminPwd.setText("");
				adminErrorLabel.setText("");
			}
		});
		adminLogin.add(cancel, gbc);

		gbc.gridx++;
		login = new JButton("Login");
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
						cards.show(cardPanel, Constants.REGISTER_CARD);
					} else {
						adminErrorLabel.setText("Invalid admin login");
					}
				} else {
					adminErrorLabel.setText(error);
				}
			}
		});
		adminLogin.add(login, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		adminErrorLabel = new JLabel();
		adminErrorLabel.setForeground(Color.RED);
		adminLogin.add(adminErrorLabel, gbc);
	}

	
	/**
	 * valiates if the entered email id is valid or not.
	 * @param hex
	 * @return
	 */
	public boolean validateEmail(final String hex) {
		pattern = Pattern.compile(Constants.EMAIL_PATTERN);
		matcher = pattern.matcher(hex);
		System.out.println("Pattern:: " + matcher.matches());
		return matcher.matches();

	}

}
