package main.window;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dataObjects.Constants;
import simulation.Simulation;

/**
 * This class provide admin activities for the user,
 * It has three different panel to provide configure
 * the system, set schedule and generate bill.
 * It also has button to go to simulation
 *
 */
public class AdminPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private CardLayout cards;
	private JPanel parentCardPanel;
	private CardLayout parentCardLayout;

	private ConfigureCard configurationPanel;
	private BillPanel billPanel;
	private JPanel buttonPanel;
	private JPanel mainPanel;
	private SchedulePanel schedulePanel;

	private JButton logoutButton;
	private JButton configureButton;
	private JButton SimulationButton;
	private JButton billButton;
	private JButton scheduleButton;

	private Dimension buttonSize;
	private Simulation simulation;

	/**
	 * creates an admin panel
	 */
	public AdminPanel() {
		this.setLayout(new BorderLayout());

		buttonSize = new Dimension(140, 40);

		setupAndAddMainPanel();
		setupAndAddButtonPanel();
	}

	/**
	 * sets up and adds main panel
	 */

	private void setupAndAddMainPanel() {

		cards = new CardLayout();

		mainPanel = new JPanel(cards);
		mainPanel.setBackground(Color.blue);

		configurationPanel = new ConfigureCard();
		billPanel = new BillPanel();
		schedulePanel = new SchedulePanel();

		mainPanel.add(configurationPanel, Constants.CONFIG);
		mainPanel.add(billPanel, Constants.BILL);
		mainPanel.add(schedulePanel, Constants.SCHEDULE_CARD);

		// add this to the window
		this.add(mainPanel, BorderLayout.CENTER);

	}

	/**
	 * creates a button panel and adds it to the main panel 
	 */
	private void setupAndAddButtonPanel() {
		buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.ORANGE);
		buttonPanel.setLayout(new GridBagLayout());
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 10));

		setupAddConfigurationButton();
		setupAddSimulationButton();
		setupAddBillButton();
		setupAddLogoutButton();
		setupAddScheduleButton();

		// add this to the window
		this.add(buttonPanel, BorderLayout.WEST);

	}

	/**
	 * creates a button that redirects to Configuration panel
	 */
	private void setupAddConfigurationButton() {
		configureButton = new JButton("Configuration");
		configureButton.setPreferredSize(buttonSize);

		configureButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cards.show(mainPanel, Constants.CONFIG);
			}
		});

		GridBagConstraints coord = new GridBagConstraints();
		coord.gridx = 0;
		coord.gridy = 0;
		buttonPanel.add(configureButton, coord);
	}

	/**
	 * creates a button that redirects to the Schedule panel
	 */
	private void setupAddScheduleButton() {
		scheduleButton = new JButton("Schedule");
		scheduleButton.setPreferredSize(buttonSize);

		scheduleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cards.show(mainPanel, Constants.SCHEDULE_CARD);
			}
		});

		GridBagConstraints coord = new GridBagConstraints();
		coord.gridx = 0;
		coord.gridy = 1;
		buttonPanel.add(scheduleButton, coord);

	}

	/**
	 * creates a button that redirects to the Simulation panel
	 */
	private void setupAddSimulationButton() {
		SimulationButton = new JButton("Simulation");
		SimulationButton.setPreferredSize(buttonSize);

		SimulationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showSimulation();
			}
		});

		GridBagConstraints coord = new GridBagConstraints();
		coord.gridx = 0;
		coord.gridy = 2;
		buttonPanel.add(SimulationButton, coord);

	}
		
	/**
	 * creates a button that redirects to the Bill Panel
	 */
	private void setupAddBillButton() {
		billButton = new JButton("Generate Bill");
		billButton.setPreferredSize(buttonSize);

		billButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cards.show(mainPanel, Constants.BILL);

			}
		});
		GridBagConstraints coord = new GridBagConstraints();
		coord.gridx = 0;
		coord.gridy = 3;
		buttonPanel.add(billButton, coord);

	}
	
	/**
	 * creates the Logout button
	 */
	private void setupAddLogoutButton() {
		logoutButton = new JButton("Log Out");
		logoutButton.setPreferredSize(buttonSize);

		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logOutAction();

			}
		});
		GridBagConstraints coord = new GridBagConstraints();
		coord.gridx = 0;
		coord.gridy = 4;
		buttonPanel.add(logoutButton, coord);

	}

	/**
	 * sets the parent card panel and card layout
	 * @param cardPanel
	 * @param cards2
	 */
	public void setParentCard(JPanel cardPanel, CardLayout cards2) {
		parentCardPanel = cardPanel;
		parentCardLayout = cards2;
	}

	/**
	 * redirects to login page
	 */
	private void logOutAction() {
		parentCardLayout.show(parentCardPanel, Constants.SYSTEM_LOGIN);
	}

	/**
	 * redirects to simulation page
	 */
	private void showSimulation() {

		simulation = new Simulation();
		simulation.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		simulation.setVisible(true);
	}
}
