package simulation;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.jdesktop.swingx.JXDatePicker;

import DBConnection.DatabaseOperation;
import dataObjects.Constants;
import dataObjects.DateUtil;

/**
 * Control panel is the upper part of the device window.
 * @author Rahmi
 *
 */
public class ControlPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JXDatePicker fromDate;
	private JXDatePicker toDate;
	private SpinnerDateModel model1;
	private SpinnerDateModel model2;
	private JSpinner timeSpinner;
	private JSpinner toTimeSpinner;

	private JPanel schedulePanel;
	private JPanel dateTimeSchedulePanel;
	private JPanel datePanel;
	private JPanel timePanel;
	private JButton saveButton;
	private JPanel radioPanel;
	private JRadioButton fireRadioButton;
	private JRadioButton breaRadiokInButton;

	private GridBagConstraints coord;
	private DatabaseOperation dp;

	public static boolean systemActive;
	static String saveString = "Save";

	public ControlPanel() {

		setUpSchedulePanel();
		createCalendar();
		setUpSaveButton();

	}

	/**
	 * Create and add radio buttons for temperature sensor and 
	 * motion sensor.
	 */
	private void setUpRadioButtons() {

		// Create the radio buttons.
		fireRadioButton = new JRadioButton(Constants.fireAlarmString);
		fireRadioButton.setMnemonic(KeyEvent.VK_A);
		fireRadioButton.setActionCommand(Constants.FIREALARM);
		fireRadioButton.setSelected(true);

		breaRadiokInButton = new JRadioButton(Constants.SensorString);
		breaRadiokInButton.setMnemonic(KeyEvent.VK_B);
		breaRadiokInButton.setActionCommand(Constants.SENSOR);

		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(fireRadioButton);
		group.add(breaRadiokInButton);

		// Register a listener for the radio buttons.
		fireRadioButton.addActionListener(this);
		breaRadiokInButton.addActionListener(this);

		// Put the radio buttons in a column in a panel.
		radioPanel = new JPanel(new GridLayout(1, 2));
		radioPanel.add(fireRadioButton);
		radioPanel.add(breaRadiokInButton);

	}

	/**
	 * Create a panel for set up new schedule
	 */
	private void setUpSchedulePanel() {

		schedulePanel = new JPanel();
		schedulePanel.setLayout(new GridBagLayout());

		TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
				"Set New Schedule");
		title.setTitleJustification(TitledBorder.LEFT);
		schedulePanel.setBorder(title);

		setUpRadioButtons();
		setUpDateTimePanel();

		coord = new GridBagConstraints();
		coord.gridx = 1;
		coord.gridy = 1;

		// add radio buttons to the window
		schedulePanel.add(radioPanel, coord);

		coord.gridx = 1;
		coord.gridy = 2;
		schedulePanel.add(dateTimeSchedulePanel, coord);
		add(schedulePanel);
	}

	/**
	 * set up date-time schedule Panel
	 */
	private void setUpDateTimePanel() {
		dateTimeSchedulePanel = new JPanel();
		dateTimeSchedulePanel.setLayout(new GridBagLayout());
	}

	/**
	 * create and save button to save schedule.
	 */
	private void setUpSaveButton() {
		saveButton = new JButton("Save schedule");
		saveButton.addActionListener(this);
		saveButton.setActionCommand(saveString);

		coord = new GridBagConstraints();

		coord.gridx = 1;
		coord.gridy = 2;

		dateTimeSchedulePanel.add(saveButton, coord);
	}

	/**
	 * create and add JXDatePicker to pick date.
	 */
	private void createCalendar() {

		fromDate = new JXDatePicker();
		fromDate.setPreferredSize(new Dimension(150, 30));

		toDate = new JXDatePicker();
		toDate.setPreferredSize(new Dimension(150, 30));

		fromDate.addPropertyChangeListener("date", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				Date selectedDate = fromDate.getDate();
				toDate.getMonthView().setLowerBound(selectedDate);
			}
		});

		java.util.Date utilDate = new Date();
		fromDate.setDate(utilDate);
		fromDate.getMonthView().setLowerBound(utilDate);

		model1 = new SpinnerDateModel();
		timeSpinner = new JSpinner(model1);
		timeSpinner.setPreferredSize(new Dimension(150, 30));

		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
		timeSpinner.setEditor(timeEditor);
		timeSpinner.setValue(new Date());

		model2 = new SpinnerDateModel();
		toTimeSpinner = new JSpinner(model2);
		toTimeSpinner.setPreferredSize(new Dimension(150, 30));

		JSpinner.DateEditor timeEditor2 = new JSpinner.DateEditor(toTimeSpinner, "HH:mm:ss");
		toTimeSpinner.setEditor(timeEditor2);
		toTimeSpinner.setValue(new Date());

		fromDateTime();
		toDateTime();
	}

	/**
	 * create and add a panel to show JXDatePicker
	 */
	private void fromDateTime() {

		datePanel = new JPanel(new GridBagLayout());
		datePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		coord = new GridBagConstraints();
		JLabel fromTitle = new JLabel("From Date");

		coord.gridx = 0;
		coord.gridy = 0;

		datePanel.add(fromTitle, coord);

		coord.gridx = 1;
		coord.gridy = 0;
		datePanel.add(fromDate, coord);

		JLabel lblToDate = new JLabel("To Date");

		coord.gridx = 0;
		coord.gridy = 1;
		datePanel.add(lblToDate, coord);

		coord.gridx = 1;
		coord.gridy = 1;
		datePanel.add(toDate, coord);

		dateTimeSchedulePanel.add(datePanel);

	}

	/**
	 * create and add a panel to show timeSpinner
	 */
	private void toDateTime() {

		timePanel = new JPanel(new GridBagLayout());
		timePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		coord = new GridBagConstraints();

		JLabel fromTime = new JLabel("Start Time");
		coord.gridx = 0;
		coord.gridy = 0;

		timePanel.add(fromTime, coord);

		coord.gridx = 1;
		coord.gridy = 0;
		timePanel.add(timeSpinner, coord);

		JLabel toTime = new JLabel("End Time");

		coord.gridx = 0;
		coord.gridy = 1;
		timePanel.add(toTime, coord);

		coord.gridx = 1;
		coord.gridy = 1;
		timePanel.add(toTimeSpinner, coord);

		dateTimeSchedulePanel.add(timePanel);
	}

	/**
	 * Action listener for save button
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		// boolean authentication = authenticateUser();

		if (e.getActionCommand() == saveString) {
			// save button pressed
			// get the schedule information from calendar
			// and store in to database
			System.out.println("saving schedule..");

			getCalendarInfoAndStore();
		}
	}


	/**
	 * After clicking save this method verifies if all the 
	 * information are valid. If the enter date-time is valid 
	 * it saves the information in the database.
	 */
	private void getCalendarInfoAndStore() {

		if (fromDate.getDate() == null || toDate.getDate() == null || model1.getValue() == null
				|| model2.getValue() == null) {
			JOptionPane.showMessageDialog(this, "Enter valid Date.");
		}

		
		else {
			List<Date> daysList = DateUtil.getDaysBetweenDates(fromDate.getDate(), toDate.getDate());
			List<String> days = new ArrayList<String>();

			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat formater2 = new SimpleDateFormat("HH:mm:ss");

			System.out.println(daysList.size());

			for (int i = 0; i < daysList.size(); i++) {

				String fdate = formater.format(daysList.get(i));
				// String tdate = formater.format(toDate.getDate());

				days.add(fdate);

			}
			String ftime = formater2.format(model1.getValue());
			String ttime = formater2.format(model2.getValue());

			if (DateUtil.timeComparison(ftime, ttime) == false) {
				System.out.println("time is: " + ftime + ", " + ttime);
				JOptionPane.showMessageDialog(this, "Start time should be less than End time.");
			} else {
				String securityOption = "";
				if (fireRadioButton.isSelected()) {
					securityOption = Constants.FIREALARM;
				} else {
					securityOption = Constants.SENSOR;
				}

				dp = new DatabaseOperation();
				dp.insertIntoSchedule(days, ftime, ttime, securityOption);
				JOptionPane.showMessageDialog(this, "Your schedule is successfully saved.");
			}

		}
	}

}
