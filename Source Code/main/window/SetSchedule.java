package main.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import javax.swing.border.TitledBorder;

import org.jdesktop.swingx.JXDatePicker;

import DBConnection.DatabaseOperation;
import dataObjects.Constants;
import dataObjects.DateUtil;

/**
 * User can set up schedule of the sensors.
 * There is a start date and end to select, 
 * user can set schedule for all the days 
 * between start and end date or just the 
 * weekdays, or just weekends
 *
 */
public class SetSchedule extends JPanel implements ActionListener {

	private JXDatePicker fromDate;
	private JXDatePicker toDate;
	private SpinnerDateModel model1;
	private SpinnerDateModel model2;
	private JSpinner timeSpinner;
	private JSpinner toTimeSpinner;

	private JPanel topPanel;
	private JPanel centerPanel;
	private JPanel radioPanel;
	private JPanel selectDatePanel;
	private JPanel selectTimePanel;
	private JPanel selectDayPanel;
	private JPanel bottomPanel;
	private JPanel sidePanel;
	private JLabel headingLabel;

	private JButton saveButton;

	private JRadioButton alarmRadioButton;
	private JRadioButton sensorRadioButton;
	private JRadioButton allDayRadioButton;
	private JRadioButton weekdayRadioButton;
	private JRadioButton weekendRadioButton;

	private String allDays = "allday";
	private String weekdays = "weekdays";
	private String weekends = "weekends";
	private String saveString = "Save";

	/**
	 * Creates a 'Set Schedule' panel
	 */
	public SetSchedule() {
		this.setLayout(new BorderLayout());

		setUpTopPanel();
		setUpCenterPanel();
		setUpBottomPanel();
		// setUpSidePanel();
	}

	/**
	 * Sets up the top panel
	 */
	public void setUpTopPanel() {

		topPanel = new JPanel();
		topPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		topPanel.setBackground(Color.ORANGE);

		setUpHeadingLabel();
		topPanel.add(headingLabel);

		this.add(topPanel, BorderLayout.NORTH);
	}

	/**
	 * Sets up heading label
	 */
	public void setUpHeadingLabel() {
		headingLabel = new JLabel("Set Schedule");
		headingLabel.setFont(new Font("Courier New", Font.BOLD, 16));

	}

	/**
	 * Sets up center panel
	 */
	public void setUpCenterPanel() {

		centerPanel = new JPanel(new BorderLayout());// new GridLayout(1,2));
		centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 80));
		createCalendar();
		// centerPanel.setBackground(Color.CYAN);

		this.add(centerPanel, BorderLayout.CENTER);
	}

	/**
	 * Sets up bottom panel
	 */
	public void setUpBottomPanel() {
		bottomPanel = new JPanel();
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

		setUpSaveButton();
		bottomPanel.add(saveButton);

		this.add(bottomPanel, BorderLayout.SOUTH);
	}

	/**
	 * Set up the save button
	 */
	public void setUpSaveButton() {
		saveButton = new JButton("Save Schedule");
		saveButton.setActionCommand(saveString);
		saveButton.addActionListener(this);
	}

	/**
	 * Sets up side panel
	 */
	private void setUpSidePanel() {
		sidePanel = new JPanel();
		sidePanel.setBorder(BorderFactory.createRaisedBevelBorder());
		sidePanel.setBackground(Color.ORANGE);
		JLabel tempLabel = new JLabel("        ");
		sidePanel.add(tempLabel);
		this.add(sidePanel, BorderLayout.EAST);
	}

	/**
	 * Creates a calendar for picking dates and times
	 */
	private void createCalendar() {

		setUpDatePicker();
		setUpTimeSpinner();

		setUpSensorRadioButtons();
		setupDatePickerPanel();
		setUpTimePickerPanel();
		setUpDayPickerPanel();
	}

	/**
	 * Sets up date picker
	 */
	public void setUpDatePicker() {
		fromDate = new JXDatePicker();
		fromDate.setPreferredSize(new Dimension(150, 30));

		toDate = new JXDatePicker();
		toDate.setPreferredSize(new Dimension(150, 30));

		fromDate.addPropertyChangeListener("date",
				new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						Date selectedDate = fromDate.getDate();
						toDate.getMonthView().setLowerBound(selectedDate);
					}
				});

		java.util.Date utilDate = new Date();
		fromDate.setDate(utilDate);
		fromDate.getMonthView().setLowerBound(utilDate);

	}

	/**
	 * Sets up time picker
	 */
	public void setUpTimeSpinner() {

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 24);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		SpinnerDateModel model = new SpinnerDateModel();
		model.setValue(calendar.getTime());

		model1 = new SpinnerDateModel();
		model1.setValue(calendar.getTime());
		timeSpinner = new JSpinner(model1);

		timeSpinner.setPreferredSize(new Dimension(150, 30));

		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner,
				"HH:mm:ss");
		timeSpinner.setEditor(timeEditor);

		model2 = new SpinnerDateModel();
		model2.setValue(calendar.getTime());
		toTimeSpinner = new JSpinner(model2);
		toTimeSpinner.setPreferredSize(new Dimension(150, 30));

		JSpinner.DateEditor timeEditor2 = new JSpinner.DateEditor(
				toTimeSpinner, "HH:mm:ss");
		toTimeSpinner.setEditor(timeEditor2);
	}

	/**
	 * Sets up time picker panel
	 */
	public void setUpTimePickerPanel() {
		selectTimePanel = new JPanel(new GridBagLayout());

		TitledBorder title = BorderFactory.createTitledBorder(
				BorderFactory.createLoweredBevelBorder(), "Select Times");
		title.setTitlePosition(TitledBorder.ABOVE_TOP);
		selectTimePanel.setBorder(title);

		// selectTimePanel.setBorder(BorderFactory.createEmptyBorder( 10, 10,
		// 10, 10));

		GridBagConstraints coord = new GridBagConstraints();

		JLabel fromTime = new JLabel("Start Time");
		coord.gridx = 0;
		coord.gridy = 0;

		selectTimePanel.add(fromTime, coord);

		coord.gridx = 1;
		coord.gridy = 0;
		selectTimePanel.add(timeSpinner, coord);

		JLabel toTime = new JLabel("End Time");

		coord.gridx = 0;
		coord.gridy = 1;
		selectTimePanel.add(toTime, coord);

		coord.gridx = 1;
		coord.gridy = 1;
		selectTimePanel.add(toTimeSpinner, coord);
		// selectTimePanel.setBackground(Color.BLUE);

		centerPanel.add(selectTimePanel, BorderLayout.CENTER);
	}

	/**
	 * Sets up date picker panel
	 */
	public void setupDatePickerPanel() {

		selectDatePanel = new JPanel(new GridBagLayout());
		TitledBorder title = BorderFactory.createTitledBorder(
				BorderFactory.createLoweredBevelBorder(), "Select Date");
		title.setTitlePosition(TitledBorder.ABOVE_TOP);
		selectDatePanel.setBorder(title);

		GridBagConstraints coord = new GridBagConstraints();
		JLabel fromTitle = new JLabel("From Date");

		coord.gridx = 0;
		coord.gridy = 0;

		selectDatePanel.add(fromTitle, coord);

		coord.gridx = 1;
		coord.gridy = 0;
		selectDatePanel.add(fromDate, coord);

		JLabel lblToDate = new JLabel("To Date");

		coord.gridx = 0;
		coord.gridy = 1;
		selectDatePanel.add(lblToDate, coord);

		coord.gridx = 1;
		coord.gridy = 1;
		selectDatePanel.add(toDate, coord);
		// selectDatePanel.setBackground(Color.green);

		centerPanel.add(selectDatePanel, BorderLayout.WEST);

	}

	/**
	 * Sets up radio buttons for selecting sensor type 
	 */
	private void setUpSensorRadioButtons() {

		// Create the radio buttons.
		alarmRadioButton = new JRadioButton(Constants.fireAlarmString);
		alarmRadioButton.setMnemonic(KeyEvent.VK_A);
		alarmRadioButton.setActionCommand(Constants.FIREALARM);
		alarmRadioButton.setSelected(true);

		sensorRadioButton = new JRadioButton(Constants.SensorString);
		sensorRadioButton.setMnemonic(KeyEvent.VK_B);
		sensorRadioButton.setActionCommand(Constants.SENSOR);

		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(alarmRadioButton);
		group.add(sensorRadioButton);

		// Register a listener for the radio buttons.
		alarmRadioButton.addActionListener(this);
		sensorRadioButton.addActionListener(this);

		// Put the radio buttons in a column in a panel.
		radioPanel = new JPanel(new GridLayout(1, 2));
		radioPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 60));

		radioPanel.add(alarmRadioButton);
		radioPanel.add(sensorRadioButton);

		// radioPanel.setBackground(Color.MAGENTA);

		centerPanel.add(radioPanel, BorderLayout.NORTH);

	}

	/**
	 * Sets up radio buttons for picking weekdays, weekends or all days
	 */
	public void setUpDayPickerPanel() {
		selectDayPanel = new JPanel(new GridBagLayout());

		TitledBorder title = BorderFactory.createTitledBorder(
				BorderFactory.createLoweredBevelBorder(), "Select Times");
		title.setTitlePosition(TitledBorder.ABOVE_TOP);
		selectDayPanel.setBorder(title);

		// selectTimePanel.setBorder(BorderFactory.createEmptyBorder( 10, 10,
		// 10, 10));

		// GridBagConstraints coord = new GridBagConstraints();

		allDayRadioButton = new JRadioButton(
				"All                                        ");
		allDayRadioButton.setMnemonic(KeyEvent.VK_A);
		allDayRadioButton.setActionCommand(this.allDays);
		allDayRadioButton.setSelected(true);

		weekdayRadioButton = new JRadioButton("Weekdays");
		weekdayRadioButton.setMnemonic(KeyEvent.VK_B);
		weekdayRadioButton.setActionCommand(this.weekdays);

		weekendRadioButton = new JRadioButton("Weekends");
		weekendRadioButton.setMnemonic(KeyEvent.VK_B);
		weekendRadioButton.setActionCommand(this.weekends);

		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(allDayRadioButton);
		group.add(weekdayRadioButton);
		group.add(weekendRadioButton);

		// Register a listener for the radio buttons.
		allDayRadioButton.addActionListener(this);
		weekdayRadioButton.addActionListener(this);
		weekendRadioButton.addActionListener(this);

		// Put the radio buttons in a column in a panel.
		selectDayPanel = new JPanel(new GridLayout(3, 1));
		selectDayPanel.add(allDayRadioButton);
		selectDayPanel.add(weekdayRadioButton);
		selectDayPanel.add(weekendRadioButton);

		// selectDayPanel.setBackground(Color.yellow);

		centerPanel.add(selectDayPanel, BorderLayout.EAST);
	}

	/**
	 * Checks if entered start time is before end time
	 * @param startTime
	 * @param endTime
	 * @return boolean
	 */
	private boolean isValidIntervalEntered(Object startTime, Object endTime) {
		DateFormat timeFormat = new SimpleDateFormat("HH:MM:SS");
		String stime = timeFormat.format(startTime);
		String etime = timeFormat.format(endTime);

		if (stime.compareTo(etime) >= 0) {
			return false;
		}
		return true;
	}

	/**
	 * Checks if a valid time has been entered
	 * @param startTime
	 * @param endTime
	 * @return boolean
	 */
	private boolean isValidTimeEntered(Object startTime, Object endTime) {
		DateFormat timeFormat = new SimpleDateFormat("HH:MM:SS");
		String stime = timeFormat.format(startTime);
		String etime = timeFormat.format(endTime);

		if (stime.compareTo("23:59:59") >= 0
				|| etime.compareTo("23:59:59") >= 0) {
			return false;
		}
		return true;
	}

	/**
	 * Gets the dates depending on whether the radio button for
	 * weekday, weekend or all the days was selected
	 * @return List<Date>
	 */
	private List<Date> getdates() {
		List<Date> daysList = null;
		if (allDayRadioButton.isSelected()) {
			daysList = DateUtil.getDaysBetweenDates(fromDate.getDate(),
					toDate.getDate());
		} else if (weekdayRadioButton.isSelected()) {
			daysList = DateUtil.getWeekdaysBetweenDates(fromDate.getDate(),
					toDate.getDate());
		} else if (weekendRadioButton.isSelected()) {
			daysList = DateUtil.getWeekendsBetweenDates(fromDate.getDate(),
					toDate.getDate());
		}
		return daysList;
	}

	/**
	 * gets from and to dates and times and stores it in the database
	 */
	private void getCalendarInfoAndStore() {

		if (fromDate.getDate() == null || toDate.getDate() == null
				|| model1.getValue() == null || model2.getValue() == null) {
			JOptionPane.showMessageDialog(this, "Enter valid Date.");
		}

		// else if(isValidTimeEntered(model1.getValue(), model2.getValue()) ==
		// false){
		// JOptionPane.showMessageDialog(this,
		// "Start time should be less than End time.");
		// }
		//
		else {
			List<Date> daysList = getdates();
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
				JOptionPane.showMessageDialog(this,
						"Start time should be less than End time.");
			} else {
				String securityOption = "";
				if (alarmRadioButton.isSelected()) {
					securityOption = Constants.FIREALARM;
				} else {
					securityOption = Constants.SENSOR;
				}

				DatabaseOperation dp = new DatabaseOperation();
				dp.insertIntoSchedule(days, ftime, ttime, securityOption);
				// JOptionPane.showMessageDialog(this,
				// "Your schedule is successfully saved.");
			}

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == saveString) {

			if (fromDate.getDate() == null || toDate.getDate() == null
					|| model1.getValue() == null || model2.getValue() == null) {
				JOptionPane.showMessageDialog(this, "Enter valid Date.");
			}

			else if (isValidIntervalEntered(model1.getValue(),
					model2.getValue()) == false) {
				JOptionPane.showMessageDialog(this,
						"Start time should be less than End time.");
			}

			else if (isValidTimeEntered(model1.getValue(), model2.getValue()) == false) {
				JOptionPane.showMessageDialog(this, "Enter valid time.");
			}

			else {
				System.out.println("saving schedule..");

				getCalendarInfoAndStore();
				JOptionPane.showMessageDialog(this,
						"Your schedule is successfully saved.");
			}
		}
	}
}
