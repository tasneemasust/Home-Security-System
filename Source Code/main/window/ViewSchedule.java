package main.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import simulation.ScheduleAndConfiguration;
import DBConnection.SensorInstallation;
import dataObjects.Constants;
import dataObjects.Schedule;

/**
 * Show user all upcoming the schedules in 
 * JTable. It also has a delete button to
 * delete set schedules.
 *
 */
public class ViewSchedule extends JPanel implements ActionListener {

	ArrayList<Schedule> fireAlarmSchedule;
	ArrayList<Schedule> sensorSchedule;

	private JSplitPane splitPane;
	private JScrollPane fireAlarmScrollPane;
	private JScrollPane sensorScrollPane;
	private JPanel topPanel;
	private JPanel centerPanel;
	private JTable fireAlarmScheduletable;
	private JTable sensorScheduletable;
	private DefaultTableModel tableModel1;
	private DefaultTableModel tableModel2;

	private JLabel headingLabel;
	private JButton refreshButton;
	private JButton fireAlarmDeleteButton;
	private JButton sensorDeleteButton;

	private String refresh = "refresh";
	private String alarmDelete = "alarmDelete";
	private String sensorDelete = "sensorDelete";

	private String[] cloumn = { "Type", "Date", "Start_time", "End_time" };

	/**
	 * Creates a panel to view schedule 
	 */
	public ViewSchedule() {

		this.setLayout(new BorderLayout());

		getScheduleData();
		setUpTableView(cloumn);
		setScheduleDatatoTable(tableModel1, fireAlarmSchedule,
				Constants.fireAlarmString);
		setScheduleDatatoTable(tableModel2, sensorSchedule,
				Constants.SensorString);

		setUpTopPanel();
		setUpCenterPanel();

		setUpFireAlarmDeleteButton();
		setUpRefreashButton();
		setUpSensorDeleteButton();

		setUpSpitpane();

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
	private void setUpHeadingLabel() {
		headingLabel = new JLabel("View Schedule");
		headingLabel.setFont(new Font("Courier New", Font.BOLD, 16));

	}

	/**
	 * Sets up the center panel
	 */
	public void setUpCenterPanel() {

		centerPanel = new JPanel();
		centerPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 20, 20));
		this.add(centerPanel, BorderLayout.CENTER);
	}

	/**
	 * Sets up a split pane
	 */
	private void setUpSpitpane() {

		fireAlarmScrollPane = new JScrollPane(fireAlarmScheduletable);
		sensorScrollPane = new JScrollPane(sensorScheduletable);

		// Create a split pane with the two scroll panes in it.
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				fireAlarmScrollPane, sensorScrollPane);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(400);

		// Provide a preferred size for the split pane.
		splitPane.setPreferredSize(new Dimension(800, 200));
		centerPanel.add(splitPane);
	}

	/**
	 * Sets up the refresh button
	 */
	private void setUpRefreashButton() {
		refreshButton = new JButton("Refresh");
		refreshButton.setPreferredSize(new Dimension(150, 30));
		refreshButton.setActionCommand(this.refresh);
		refreshButton.addActionListener(this);
		centerPanel.add(refreshButton);
	}

	/**
	 * Sets up delete temperature sensor schedule button
	 */
	private void setUpFireAlarmDeleteButton() {
		fireAlarmDeleteButton = new JButton(
				"Delete all Temperature Sensor schedule");
		fireAlarmDeleteButton.setActionCommand(this.alarmDelete);
		fireAlarmDeleteButton.addActionListener(this);
		centerPanel.add(fireAlarmDeleteButton);
	}

	/**
	 * Sets up delete motion sensor schedule button
	 */
	private void setUpSensorDeleteButton() {
		sensorDeleteButton = new JButton("Delete all Motion Sensor schedule");
		sensorDeleteButton.setActionCommand(this.sensorDelete);
		sensorDeleteButton.addActionListener(this);
		centerPanel.add(sensorDeleteButton);
	}

	/**
	 * Adds schedule data to a table
	 * @param table
	 * @param schedule
	 * @param str
	 */
	public void setScheduleDatatoTable(DefaultTableModel table,
			ArrayList<Schedule> schedule, String str) {
		String[] row = new String[4];
		for (int i = 0; i < schedule.size(); i++) {
			row[0] = str;
			row[1] = schedule.get(i).getDate();
			row[2] = schedule.get(i).getFromTime();
			row[3] = schedule.get(i).getToTime();
			System.out.println(str + row);

			table.addRow(row);
		}
	}

	/**
	 * Gets the schedule data
	 */
	private void getScheduleData() {
		ScheduleAndConfiguration sc = new ScheduleAndConfiguration();
		fireAlarmSchedule = sc.getFireAlarmScheduleList();
		sensorSchedule = sc.getSensorScheduleList();
	}

	/**
	 * Sets up the tables to store schedule data in
	 * @param col
	 */
	private void setUpTableView(String[] col) {
		tableModel1 = new DefaultTableModel(col, 0);
		tableModel2 = new DefaultTableModel(col, 0);

		fireAlarmScheduletable = new JTable(tableModel1) {
			// Returning the Class of each column will allow different

			public Class getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}

			public Component prepareRenderer(TableCellRenderer renderer,
					int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				JComponent jc = (JComponent) c;

				// Color row based on a cell value
				// Alternate row color

				if (!isRowSelected(row))
					c.setBackground(row % 2 == 0 ? getBackground()
							: Color.LIGHT_GRAY);
				else
					jc.setBorder(new MatteBorder(1, 0, 1, 0, Color.ORANGE));
				// Use bold font on selected row

				return c;
			}
		};

		sensorScheduletable = new JTable(tableModel2) {
			// Returning the Class of each column will allow different

			public Class getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}

			public Component prepareRenderer(TableCellRenderer renderer,
					int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				JComponent jc = (JComponent) c;

				// Color row based on a cell value
				// Alternate row color

				if (!isRowSelected(row))
					c.setBackground(row % 2 == 0 ? getBackground()
							: Color.LIGHT_GRAY);
				else
					jc.setBorder(new MatteBorder(1, 0, 1, 0, Color.ORANGE));
				// Use bold font on selected row

				return c;
			}
		};
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == this.refresh) {
			tableModel1.setRowCount(0);
			tableModel2.setRowCount(0);

			getScheduleData();
			setScheduleDatatoTable(tableModel1, fireAlarmSchedule,
					Constants.fireAlarmString);
			setScheduleDatatoTable(tableModel2, sensorSchedule,
					Constants.SensorString);
		}
		if (e.getActionCommand() == this.alarmDelete) {
			if (SensorInstallation.deleteFromSchedule(Constants.FIREALARM,
					Constants.adminUserId)) {

			}
		}
		if (e.getActionCommand() == this.sensorDelete) {
			if (SensorInstallation.deleteFromSchedule(Constants.SENSOR,
					Constants.adminUserId)) {

			}

		}
	}

}
