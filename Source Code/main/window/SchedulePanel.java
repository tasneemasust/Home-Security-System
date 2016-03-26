package main.window;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Schedule Panel, it has two parts to 
 * set up and view schedule.
 *
 */
public class SchedulePanel extends JPanel{
	
	private SetSchedule setSchedulePanel;
	private ViewSchedule viewSchedulePanel;
	private JPanel bottomPanel;
	private JPanel sidePanel;

	/**
	 * Creates the Schedule panel to set and view schedule
	 */
	public SchedulePanel(){
		//this.setLayout(new GridLayout(2,1));
		this.setLayout(new BorderLayout());
		setUpsetSchedulePanel();
		setUpviewSchedulePanel();
		
		setUpBottomPanel();
		//setUpSidePanel();
	}
	
	/**
	 * Sets up the 'Set Schedule' panel
	 */
	private void setUpsetSchedulePanel(){
		setSchedulePanel = new SetSchedule();
		this.add(setSchedulePanel, BorderLayout.NORTH);
		
	}
	
	/**
	 * Sets up the 'View Schedule' panel
	 */
	private void setUpviewSchedulePanel(){
		viewSchedulePanel = new ViewSchedule();
		this.add(viewSchedulePanel, BorderLayout.CENTER);
	}
	
	/**
	 * Sets up bottom panel
	 */
	private void setUpBottomPanel(){
		bottomPanel = new JPanel();
		bottomPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		bottomPanel.setBackground(Color.ORANGE);
		JLabel tempLabel = new JLabel("     ");
		bottomPanel.add(tempLabel);
		this.add(bottomPanel, BorderLayout.SOUTH);
		
	}
	
//	private void setUpSidePanel(){
//		sidePanel = new JPanel();
//		sidePanel.setBorder(BorderFactory.createRaisedBevelBorder());
//		sidePanel.setBackground(Color.ORANGE);
//		JLabel tempLabel = new JLabel("     ");
//		sidePanel.add(tempLabel);
//		this.add(sidePanel, BorderLayout.EAST);
//	}
}
