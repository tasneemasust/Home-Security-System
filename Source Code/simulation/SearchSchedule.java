package simulation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import DBConnection.ConfigurationSchedule;
import dataObjects.Constants;
import dataObjects.Schedule;

import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 * Search a date in the database, if temperature or motion
 * sensor is set for the date it shows the information to
 * the user.
 * @author Rahmi
 *
 */
public class SearchSchedule extends JPanel implements ActionListener{
	
	
	private static final long serialVersionUID = 1L;
	private JPanel topPanel;
	private JPanel mainPanel;
	
	private JLabel dateLabel;
	private JLabel showAlarmLabel;
	private JLabel showSensorLabel;

	private JTextField dateTextField;
	private JButton searchButton;

	private Schedule alarmShedule;
	private Schedule sensorShedule;
	private ConfigurationSchedule cs;
	HintTextField hintTextField;


	/**
	 * constructor
	 */
	public SearchSchedule(){
		
		setLayout(new BorderLayout());
		
		setUptopPanel();
		setUPMainPanel();		
	}

	/**
	 * Set up top panel. Top panel has a label, JTextField to
	 * enter date and a search button.
	 */
	public void setUptopPanel() {
		
		topPanel = new JPanel();

		dateLabel = new JLabel("Enter a Date: ");
		dateLabel.setAlignmentX(LEFT_ALIGNMENT);
		topPanel.add(dateLabel);

		hintTextField =new HintTextField("YYYY-MM-DD");
		hintTextField.setPreferredSize(new Dimension(140, 30));
		topPanel.add(hintTextField);
		
		searchButton = new JButton("Search");
		searchButton.setPreferredSize(new Dimension(140, 30));
		searchButton.addActionListener(this);
		topPanel.add(searchButton);
		
		add(topPanel, BorderLayout.NORTH);

	}
	
	/**
	 * Set up main Panel.  It has two labels.
	 * one for showing schedule of temperature
	 * sensor other is for motion sensor.
	 */
	public void setUPMainPanel(){
		mainPanel = new JPanel();
		
		setupShowAlarmLabel();
		mainPanel.add(showAlarmLabel);
		
		setupShowSensonLabel();
		mainPanel.add(showSensorLabel);
		
		add(mainPanel, BorderLayout.CENTER);
	}
	

	/**
	 * setting up label for showing  schedule of temperature
	 */
	public void setupShowAlarmLabel(){
		showAlarmLabel = new JLabel();
		showAlarmLabel.setPreferredSize(new Dimension(200,150));
		showAlarmLabel.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 14));
		TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder
				(EtchedBorder.LOWERED), "Fire Alarm Schedule");
		title.setTitleJustification(TitledBorder.LEFT);
		showAlarmLabel.setBorder(title);
	}
	
	/**
	 * setting up label for showing motion sensor schedule
	 */
	public void setupShowSensonLabel(){
		showSensorLabel = new JLabel();
		showSensorLabel.setPreferredSize(new Dimension(200,150));
		showSensorLabel.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 14));
		TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder
				(EtchedBorder.LOWERED), "Sensor Schedule");
		title.setTitleJustification(TitledBorder.LEFT);
		showSensorLabel.setBorder(title);
	}

	
	/**
	 * make a html string to show multiple line in a label
	 * @param sch
	 * @return
	 */
	public String getScheduleString(Schedule sch){
		// call database to get info
		String str = "<html>Date: "+ " &emsp;  "+ sch.getDate() +
    			"<br>Start Time: "+" &emsp;  "+ sch.getFromTime() +
    			"<br>End Time: "+ "  &emsp; "+ sch.getToTime() +
    			"</html>";
		
		return str;
		
	}
	
	
	/**
	 * change the label text after clicking search button
	 */
	private void setTextforMain(){
		cs = new ConfigurationSchedule();
		alarmShedule = cs.getDatafromScheduleTable(dateTextField.getText(), Constants.FIREALARM);
		sensorShedule = cs.getDatafromScheduleTable(dateTextField.getText(), Constants.SENSOR);	
		
		if(alarmShedule != null){
			showAlarmLabel.setText(getScheduleString(alarmShedule));
		}
		else{
			showAlarmLabel.setText("<html>Fire Alarm is not set for this date.</html>");
		}
		
		if(sensorShedule != null){
			showSensorLabel.setText(getScheduleString(sensorShedule));
		}
		else{
			showSensorLabel.setText("<html>Sensor is not set for this date.</html>");
		}
		
		showAlarmLabel.revalidate();
		showAlarmLabel.repaint();
		
		showSensorLabel.revalidate();
		showSensorLabel.repaint();
	}

	/**
	 * Action listener
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		setTextforMain();
	}

}
