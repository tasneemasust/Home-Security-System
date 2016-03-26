package main.window;

import house.map.Data;
import house.map.MainStructure;
import house.map.Room;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import simulation.ScheduleAndConfiguration;
import DBConnection.SensorInstallation;
import dataObjects.Constants;

/**
 * This class provide graphical interface to
 * configure the security system. User can activate
 * or deactivate sensors in all or selected area
 * of the house map.
 *
 */
public class ConfigureCard extends JPanel {

	private JPanel mapPanel;
	private JPanel initializeCard;
	private JPanel sidePanel;
	private JPanel selectPanel;

	private JCheckBox selectAll;
	private JRadioButton sensor;
	private JRadioButton fireAlarm;
	private JButton save;
	private JButton install;
	private JButton deactivateButton;

	private MainStructure houseMap;

	private ArrayList<Room> sensorRooms = new ArrayList<Room>();

	/**
	 * creates the Configure Page
	 */
	public ConfigureCard() {

		setLayout(new BorderLayout());
		createInitialization();
		showConfigurationInLabel();
		this.add(initializeCard);
	}

	/**
	 * creates the card where user can activate and deactivate sensors
	 */
	private void createInitialization() {
		initializeCard = new JPanel();
		initializeCard.setLayout(new BorderLayout());
		initializeCard.setBorder(new EmptyBorder(10, 10, 10, 10));
		initializeCard.setBackground(Color.orange);

		createMapPanel();
		createSelectPanel();
		initializeCard.add(sidePanel, BorderLayout.EAST);
		initializeCard.add(mapPanel, BorderLayout.CENTER);

	}

	/**
	 * creates the side panel that contains a panel to select 
	 * motion/temperature radio buttons and a save button
	 */
	private void createSelectPanel() {
		sidePanel = new JPanel();
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));

		selectPanel = new JPanel();
		selectPanel.setLayout(new GridBagLayout());
		sidePanel.add(selectPanel);

		selectPanel.setBackground(Color.ORANGE);
		selectPanel.setBorder(BorderFactory.createCompoundBorder(
				new EmptyBorder(10, 10, 10, 10),
				BorderFactory.createLineBorder(Color.BLACK)));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;

		JLabel userListLabel = new JLabel("Select User:");
		userListLabel.setFont(new Font(null, Font.BOLD, 16));

		JLabel installLabel = new JLabel("Select:");
		installLabel.setFont(new Font(null, Font.BOLD, 16));

		sensor = new JRadioButton("Motion Sensor");
		sensor.setSelected(true);
		sensor.setFont(new Font(null, Font.PLAIN, 16));
		fireAlarm = new JRadioButton("Temperature Sensor");
		fireAlarm.setFont(new Font(null, Font.PLAIN, 16));
		ButtonGroup group = new ButtonGroup();
		group.add(sensor);
		group.add(fireAlarm);

		save = new JButton("Save");
		save.setPreferredSize(new Dimension(140, 30));
		save.setFont(new Font(null, Font.PLAIN, 16));
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Data.getSelectedRoom().size() != 0) {
					install.setEnabled(true);
					deactivateButton.setEnabled(true);

					for (Room r : Data.getSelectedRoom()) {

						if (sensor.isSelected()) {
							r.setSensor(sensor.isSelected());
						} else if (fireAlarm.isSelected()) {
							r.setFireAlerm(fireAlarm.isSelected());
						}
						if (!sensorRooms.contains(r)) {
							sensorRooms.add(r);
						}
					}
				}
			}
		});

		gbc.gridx = 0;
		gbc.gridy = 0;

		selectPanel.add(installLabel, gbc);

		gbc.gridy++;
		selectPanel.add(sensor, gbc);

		gbc.gridy++;
		selectPanel.add(fireAlarm, gbc);

		gbc.gridy++;
		selectPanel.add(save, gbc);

		setupInstallButton();
		setupDeactivateButton();
	}

	/**
	 * creates button to activate sensors
	 */
	public void setupInstallButton() {
		JPanel installPanel = new JPanel();

		install = new JButton("Activate Sensors");
		install.setEnabled(false);
		install.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog.setDefaultLookAndFeelDecorated(true);
				if (!installSensors(sensorRooms)) {
					JOptionPane.showMessageDialog(null,
							"Sensors not activated correctly.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null,
							"Sensors are activated correctly.", "Installed",
							JOptionPane.INFORMATION_MESSAGE);
				}
				refreshHouseMap();
			}
		});

		install.setPreferredSize(new Dimension(180, 40));
		install.setFont(new Font(null, Font.BOLD, 16));
		installPanel.add(install);
		installPanel.setBackground(Color.ORANGE);
		sidePanel.add(installPanel);
	}

	/**
	 * creates button to deactivate sensors
	 */
	public void setupDeactivateButton() {
		JPanel deActivatePanel = new JPanel();

		deactivateButton = new JButton("Deactivate Sensors");
		deactivateButton.setEnabled(false);
		deactivateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog.setDefaultLookAndFeelDecorated(true);
				if (!uninstallSensors(sensorRooms)) {
					JOptionPane
							.showMessageDialog(
									null,
									"You are trying to Deactivate a sensor that is not installed.",
									"Error", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null,
							"Sensors are Deactivated correctly.", "Installed",
							JOptionPane.INFORMATION_MESSAGE);
				}
				refreshHouseMap();
			}
		});

		deactivateButton.setPreferredSize(new Dimension(180, 40));
		deactivateButton.setFont(new Font(null, Font.BOLD, 16));
		deActivatePanel.add(deactivateButton);
		deActivatePanel.setBackground(Color.ORANGE);
		sidePanel.add(deActivatePanel);
	}

	/**
	 * gathers configuration information to insert into database
	 * @param sensorRooms
	 * @return boolean
	 */
	public boolean installSensors(ArrayList<Room> sensorRooms) {
		boolean returnFlag = true;
		String areaId = "";
		String roomType = "";
		for (Room r : sensorRooms) {
			areaId = r.getAreaId();
			roomType = r.getRoomType();
			if (r.isSensor()) {
				if (!SensorInstallation.insertConfig(Constants.SENSOR, areaId,
						roomType, Constants.adminUserId)) {
					returnFlag = false;
				}
			}
			if (r.isFireAlerm()) {
				if (!SensorInstallation.insertConfig(Constants.FIRE_ALARM,
						areaId, roomType, Constants.adminUserId)) {
					returnFlag = false;
				}
			}
		}
		return returnFlag;
	}

	/**
	 * gathers configuration information to delete from database
	 * @param sensorRooms
	 * @return boolean
	 */
	public boolean uninstallSensors(ArrayList<Room> sensorRooms) {
		boolean returnFlag = true;
		String areaId = "";
		String roomType = "";
		for (Room r : sensorRooms) {
			areaId = r.getAreaId();
			roomType = r.getRoomType();
			if (r.isSensor()) {
				if (!SensorInstallation.deleteFromConfig(Constants.SENSOR,
						areaId, roomType, Constants.adminUserId)) {
					returnFlag = false;
				}
			}
			if (r.isFireAlerm()) {
				if (!SensorInstallation.deleteFromConfig(Constants.FIRE_ALARM,
						areaId, roomType, Constants.adminUserId)) {
					returnFlag = false;
				}
			}
		}
		return returnFlag;
	}

	public void setRoomSelected() {
		selectPanel.setVisible(true);
	}

	public void resetRoomSelected() {
		selectPanel.setVisible(false);
	}

	/**
	 * refreshes house map on de-select all
	 */
	public void refreshHouseMap() {
		selectAll.setSelected(false);
		sensorRooms.clear();
		houseMap.deselectAllRooms();
		showConfigurationInLabel();
	}

	/**
	 * creates panel that contains map
	 */
	private void createMapPanel() {
		mapPanel = new JPanel();
		mapPanel.setLayout(new BorderLayout());

		selectAll = new JCheckBox("Select All Rooms");
		selectAll.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (selectAll.isSelected()) {
					houseMap.selectAllRooms();
				} else {
					for (JButton button2 : houseMap.getButtonArray()) {
						button2.doClick();
					}
				}
			}
		});
		selectAll.setFont(new Font(null, Font.BOLD, 16));
		mapPanel.add(selectAll, BorderLayout.NORTH);
		houseMap = new MainStructure();

		mapPanel.add(houseMap, BorderLayout.CENTER);
		mapPanel.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	public void getConfigurationData() {
		ScheduleAndConfiguration sc = new ScheduleAndConfiguration();
		sc.setCofiguration();
	}

	/**
	 * sets color when sensor is activated
	 */
	public void showConfigurationInLabel() {

		getConfigurationData();
		for (int i = 0; i < houseMap.getButtonArray().size(); i++) {

			Room room = (Room) houseMap.getButtonArray().get(i);

			if (ScheduleAndConfiguration.curConfiguration.get(room.getAreaId()
					+ Constants.FIREALARM) != null) {
				room.setfireAlarmIndicatorColor(Color.YELLOW);
			} else {
				room.setfireAlarmIndicatorColor(Color.WHITE);
			}

			if (ScheduleAndConfiguration.curConfiguration.containsKey(room
					.getAreaId() + Constants.SENSOR)) {
				room.setSensorIndicatorColor(Color.GREEN);
			} else {
				room.setSensorIndicatorColor(Color.WHITE);
			}
		}
	}
}
