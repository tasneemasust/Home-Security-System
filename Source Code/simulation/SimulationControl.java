package simulation;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import dataObjects.Configuration;
import dataObjects.Constants;

/**
 * This class controls the simulation.It has two parts.
 * One is command panel to start simulation, other is
 * the house map to show animation.
 * 
 * @author Rahmi
 *
 */
public class SimulationControl extends JPanel implements ActionListener {

	
	private static final long serialVersionUID = 1L;
	private static final int NORMAL = 0;
	private int randomRoom = 0;
	private int randomRoom2 = 0;

	private JButton simulateButton;
	private JButton stopButton;

	private JRadioButton fireButton;
	private JRadioButton breakInButton;

	private JPanel commandPanel;

	private ShowHouseMap houseMap;
	private FireSimulationThread fireThread;
	private BreakInSimulationThread breakInTread;
	private Configuration con1;
	private Configuration con2;
	private ScheduleAndConfiguration sc;

	public SimulationControl() {

		// set up and add command panel
		setUpCommandPanel();
		add(commandPanel);
		showHouse();
	}

	private void setUpCommandPanel() {

		// create panel for controlling simulation
		commandPanel = new JPanel();
		commandPanel.setPreferredSize(new Dimension(500, 120));
		commandPanel.setBorder(BorderFactory.createBevelBorder(NORMAL));

		// Create the radio buttons.
		fireButton = new JRadioButton(Constants.fireString);
		fireButton.setMnemonic(KeyEvent.VK_A);
		fireButton.setActionCommand(Constants.fireString);
		fireButton.setSelected(true);

		breakInButton = new JRadioButton(Constants.breakInString);
		breakInButton.setMnemonic(KeyEvent.VK_B);
		breakInButton.setActionCommand(Constants.breakInString);

		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(fireButton);
		group.add(breakInButton);

		// Register a listener for the radio buttons.
		fireButton.addActionListener(this);
		breakInButton.addActionListener(this);

		// Put the radio buttons in a column in a panel.
		JPanel radioPanel = new JPanel(new GridLayout(0, 1));
		radioPanel.add(fireButton);
		radioPanel.add(breakInButton);

		// add radio buttons to the window
		commandPanel.add(radioPanel);

		simulateButton = new JButton(Constants.simulateString);
		simulateButton.addActionListener(this);
		simulateButton.setActionCommand(Constants.simulateString);

		stopButton = new JButton(Constants.stopString);
		stopButton.addActionListener(this);
		stopButton.setActionCommand(Constants.stopString);
		stopButton.setVisible(false);

		// add simulate button to the window
		commandPanel.add(simulateButton);

		// add the invisible stop button
		commandPanel.add(stopButton);

	}

	public void showHouse() {
		houseMap = new ShowHouseMap();
		add(houseMap);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == Constants.simulateString) {

			stopButton.setVisible(true);
			simulateButton.setVisible(false);
			radioButtonDisable();
			if (fireButton.isSelected()) {

				// set simulationId to Constants.fireString
				Constants.setsimulationId(Constants.fireString);

				randomRoom = ((++randomRoom) % 3);
				// start fire simulation in randomRoom
				houseMap.addFire(randomRoom);

				// get schedule information from database
				getFireAlarmSchedule();

				// check if Fire Alarm is installed in the area
				if (isFireAlarmConfigured(getRoom(randomRoom))) {

					System.out.println("Alarm is set for this room.");

					// Fire alarm installed in the room.
					// start fire simulation thread
					fireThread = new FireSimulationThread();
					fireThread.start();
				}

			}

			if (breakInButton.isSelected()) {
				
				Constants.setsimulationId(Constants.breakInString);

				randomRoom2 = ((++randomRoom2) % 3) ;
				houseMap.addBreakIn(randomRoom2);

				getSensorSchedule();
				
				// check if sensor is installed in the area
				if (isSensorConfigured(getRoom(randomRoom2))) {

					System.out.println("sensor is set for this room.");

					// Fire alarm installed in the room.
					// start fire simulation thread
					breakInTread = new BreakInSimulationThread();
					breakInTread.start();
				}
				else{
					System.out.println("sensor is NOT set for this room.");

				}
			}

		}

		else if (e.getActionCommand() == Constants.stopString) {
			
			stopButton.setVisible(false);
			simulateButton.setVisible(true);
			radioButtonEnable();

			Constants.setsimulationId("");

			if (fireButton.isSelected()) {
				houseMap.stopFire();
				fireThread.stopThread();
				fireThread.stopAlarm();
				
			}

			if (breakInButton.isSelected()) {
				houseMap.stopBreakIn();
				breakInTread.stopThread();
			}
		}
	}

	public boolean isFireAlarmConfigured(String area_id) {
		con1 = ScheduleAndConfiguration.curConfiguration.get(area_id + Constants.FIREALARM);
		if (con1 != null) {
			return true;
		}
		return false;
	}
	
	public boolean isSensorConfigured(String area_id) {
		con2 = ScheduleAndConfiguration.curConfiguration.get(area_id + Constants.SENSOR);
		if (con2 != null) {
			return true;
		}
		return false;
	}

	public String getRoom(int n) {
		if (n == 1)
			return "room1";
		else if (n == 2)
			return "room3";
		else
			return "room4";

	}

	public void getFireAlarmSchedule() {
		System.out.println("getting fire alarm schedule.");

		sc = new ScheduleAndConfiguration();
		sc.getFireAlarmSchedule();
	}

	public void getSensorSchedule() {
		sc = new ScheduleAndConfiguration();
		sc.getSensorSchedule();
	}
	
	public void radioButtonDisable(){
		fireButton.setEnabled(false);
		breakInButton.setEnabled(false);
	}
	
	public void radioButtonEnable(){
		fireButton.setEnabled(true);
		breakInButton.setEnabled(true);
	}
}

