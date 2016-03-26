package main.window;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import DBConnection.LoginRegisteration;
import dataObjects.Constants;

/**
 * A panel for registering new user.
 *
 */
public class RegistrationPanel extends JPanel {

	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField addressTextField;
	private JTextField emailTextField;
	private JFormattedTextField phoneText;
	private JTextField userTextField;
	private JTextField passwordTextField;
	private JButton cancel;
	private Pattern pattern;
	private Matcher matcher;
	private JComboBox<String> userLevel;
	private JButton registerSave;
	private JLabel regErrorLabel;
	private JLabel loginErrorLabel;
	private JLabel adminErrorLabel;
	private JTextField loginUserId;
	private JPasswordField loginPwd;

	private JPanel registrationCard;

	private JLabel firstName;
	private JLabel lastName;
	private JLabel address;
	private JLabel email;
	private JLabel phone;
	private Component user;
	private JLabel password;
	private JButton goBack;
	private JButton resetButton;
	private JPanel parentCardPanel;
	private CardLayout parentCardLayout;

	public RegistrationPanel() {
		this.setLayout(new BorderLayout());

	}

	/**
	 * creates registration panel to add user info
	 */
	public void createRegistrationCard() {
		registrationCard = new JPanel();

		registrationCard.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;

		gbc.anchor = GridBagConstraints.WEST;

		JLabel register = new JLabel("Please fill up form to register: ");
		register.setFont(new Font(null, Font.BOLD, 14));
		registrationCard.add(register, gbc);

		gbc.gridy++;
		firstName = new JLabel("First Name: ");
		registrationCard.add(firstName, gbc);

		gbc.gridx++;
		firstNameField = new JTextField(20);
		registrationCard.add(firstNameField, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		lastName = new JLabel("Last Name: ");
		registrationCard.add(lastName, gbc);

		gbc.gridx++;
		lastNameField = new JTextField(20);
		registrationCard.add(lastNameField, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		address = new JLabel("Address: ");
		registrationCard.add(address, gbc);

		gbc.gridx++;
		addressTextField = new JTextField(20);
		registrationCard.add(addressTextField, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		email = new JLabel("Email: ");
		registrationCard.add(email, gbc);

		gbc.gridx++;
		emailTextField = new JTextField(20);
		registrationCard.add(emailTextField, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		phone = new JLabel("Phone: ");
		registrationCard.add(phone, gbc);

		gbc.gridx++;
		MaskFormatter formatter = null;

		try {
			formatter = new MaskFormatter("**********");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		formatter.setValidCharacters("0123456789");
		phoneText = new JFormattedTextField(formatter);
		phoneText.setColumns(10);
		registrationCard.add(phoneText, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		user = new JLabel("User Id: ");
		registrationCard.add(user, gbc);

		gbc.gridx++;
		userTextField = new JTextField(15);
		registrationCard.add(userTextField, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		password = new JLabel("Password: ");
		registrationCard.add(password, gbc);

		gbc.gridx++;
		passwordTextField = new JTextField(10);
		registrationCard.add(passwordTextField, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		JLabel levelLabel = new JLabel("Level");
		registrationCard.add(levelLabel, gbc);

		gbc.gridx++;
		String[] combo = { "primary", "admin", "other" };
		userLevel = new JComboBox<String>(combo);
		registrationCard.add(userLevel, gbc);

		gbc.gridx = 0;
		gbc.gridy++;

		goBack = new JButton("Go Back");
		goBack.setPreferredSize(new Dimension(120, 30));
		goBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parentCardLayout.show(parentCardPanel, Constants.ADMIN_LOGIN);
			}
		});
		registrationCard.add(goBack, gbc);

		gbc.gridx++;
		registerSave = new JButton("Save");
		registerSave.setPreferredSize(new Dimension(120, 30));
		registerSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (validateForm()) {
					if (LoginRegisteration.saveUserInfo(
							firstNameField.getText(), lastNameField.getText(),
							addressTextField.getText(), phoneText.getText(),
							emailTextField.getText(), userTextField.getText(),
							passwordTextField.getText(),
							(String) userLevel.getSelectedItem())) {
						// cards.show(cardPanel, Constants.LOGIN_CARD);
						// JOptionPane.showMessageDialog(this,
						// "Your information is successfully entered.",
						// "Message",
						// JOptionPane.PLAIN_MESSAGE);
						// JOptionPane.showMessageDialog(this,
						// "Start time should be less than End time.");

					} else {
						regErrorLabel.setText("Could not add user.");
					}
				}
			}
		});
		registrationCard.add(registerSave, gbc);

		gbc.gridx++;
		resetButton = new JButton("Reset");
		resetButton.setPreferredSize(new Dimension(120, 30));
		resetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				firstNameField.setText("");
				lastNameField.setText("");
				addressTextField.setText("");
				phoneText.setText("");
				emailTextField.setText("");
				userTextField.setText("");
				passwordTextField.setText("");
			}
		});
		registrationCard.add(resetButton, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		regErrorLabel = new JLabel();
		regErrorLabel.setForeground(Color.RED);
		registrationCard.add(regErrorLabel, gbc);
		add(registrationCard, BorderLayout.CENTER);
	}

	/**
	 * validates form data by checking if any field is empty
	 * and if phone and email are in proper format 
	 * else sets the error label
	 * @return boolean
	 */
	private boolean validateForm() {
		String text = "";

		if (firstNameField.getText().equals("")) {
			text = "First name cannot be empty";
		} else if (lastNameField.getText().equals("")) {
			text = "Last name cannot be empty";
		} else if (addressTextField.getText().equals("")) {
			text = "Address cannot be empty";
		} else if (phoneText.getText().equals("")) {
			text = "Phone number cannot be empty";
		} else if (emailTextField.getText().equals("")) {
			text = "Email cannot be empty";
		} else if (userTextField.getText().equals("")) {
			text = "User ID cannot be empty";
		} else if (passwordTextField.getText().equals("")) {
			text = "Password cannot be empty";
		} else if (!validateEmail(emailTextField.getText().trim())) {
			text = "Email is not in correct format";
		} else if (LoginRegisteration.exists(userTextField.getText())) {
			text = "User Id exists. Pick another one.";
		} else {
			regErrorLabel.setText(text);
			return true;
		}
		regErrorLabel.setText(text);
		return false;
	}
	
	/**
	 * checks if email is in correct format
	 * @param hex
	 * @return boolean
	 */
	public boolean validateEmail(final String hex) {
		pattern = Pattern.compile(Constants.EMAIL_PATTERN);
		matcher = pattern.matcher(hex);
		System.out.println("Pattern:: " + matcher.matches());
		return matcher.matches();

	}

	/**
	 * sets parent card layout and card panel
	 * @param cardPanel
	 * @param cards2
	 */
	public void setParentCard(JPanel cardPanel, CardLayout cards2) {
		parentCardPanel = cardPanel;
		parentCardLayout = cards2;
	}

}
