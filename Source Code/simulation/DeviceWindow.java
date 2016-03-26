package simulation;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import dataObjects.Constants;

/**
 * Device window has a Tabbed panel to save schedule,
 * view schedule and search. It also has a log panel
 * which shows the current activity of the device.
 * @author Rahmi
 *
 */

public class DeviceWindow extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private static final int NORMAL = 0;
	public static ActiveLog activeLog;
	
	private CardLayout parentCardLayout;
	private JPanel parentCardPanel;
	private JTabbedPane tabbedPane;
	
	private JButton logout;
	private JButton activateButton;
	private ControlPanel controlPanel;
	private ViewSchedulePanel viewShcedulePanel;
	private SearchSchedule searchSchedule;
	
	
	static String active = "Activate System";
	static String deactive = "Deactivate System";
	static String activationString = "activation";

	/**
	 * constructor.
	 */
	public DeviceWindow(){
		createLogoutButton();
		createControlPanel();		
	}

	/**
	 * create and add control panel to set schedule.
	 * Initializes the activeLog to show live status 
	 * of the device.
	 */
	private void createControlPanel(){
		createTabbedPane();
		add(tabbedPane);
		
		setUpActivateButton();
		
		activeLog = new ActiveLog();
		activeLog.setPreferredSize(new Dimension(500,250));
		activeLog.setBorder(BorderFactory.createBevelBorder(NORMAL));
		add(activeLog);
		
		Thread showLog = new ShowLogThread(Messages.SystemActive, Messages.systemActiveMsgSize);
		showLog.start();
	}
	
	/**
	 * create a tabbed panel for the upper part of the
	 * device window.
	 */
	private void createTabbedPane() {
		tabbedPane = new JTabbedPane();
		tabbedPane.setPreferredSize(new Dimension(500,350));
		tabbedPane.setBorder(BorderFactory.createBevelBorder(NORMAL));

		tabbedPane.setOpaque(true);
		
		controlPanel = new ControlPanel();
		viewShcedulePanel = new ViewSchedulePanel();
		searchSchedule = new SearchSchedule();
		
		tabbedPane.addTab("Set Schedule", controlPanel);
    	tabbedPane.addTab("View Schedule", viewShcedulePanel);
    	tabbedPane.addTab("Search", searchSchedule);

	}
	
	/**
	 * set parent card
	 * @param cardPanel
	 * @param cards2
	 */
	public void setParentCard(JPanel cardPanel, CardLayout cards2){
		parentCardPanel = cardPanel;
		parentCardLayout = cards2;
	}
	
	/**
	 * create and add logout button
	 */
	private void createLogoutButton() {
		logout = new JButton("Logout");
		logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parentCardLayout.show(parentCardPanel, Constants.LOGIN_CARD);
			}
		});

		add(logout);
	}
	
	/**
	 * create and add Activation button to activate
	 * or deactivate the total system.
	 */
	private void setUpActivateButton(){
		
		activateButton = new JButton();
		activateButton.addActionListener(this);
		activateButton.setActionCommand(activationString);
		
		if(Constants.SYSTEMACTIVE == true){
			activateButton.setText(deactive);
		}
		else{
			activateButton.setText(active);
		}
		
		activateButton.setFont(new Font("Calibri", Font.PLAIN, 16));
		activateButton.setBackground(new Color(0x2dce98));
		activateButton.setForeground(Color.BLUE);
		activateButton.setUI(new StyledButtonUI());
		
		add(activateButton);
	}
	

	/**
	 * Action listener for activation button.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if( e.getActionCommand() == activationString){
			
			System.out.println(Constants.SYSTEMACTIVE);
			//activation button pressed
			if(Constants.SYSTEMACTIVE == true){
				Constants.SYSTEMACTIVE = false;
				activateButton.setText(active);
				Thread showLog = new ShowLogThread(Messages.SystemdeActive, Messages.systemDeActiveMsgSize);
				showLog.start();
			}
			else if(Constants.SYSTEMACTIVE == false){
				Constants.SYSTEMACTIVE = true;
				activateButton.setText(deactive);
				Thread showLog = new ShowLogThread(Messages.SystemActive, Messages.systemActiveMsgSize);
				showLog.start();
			}
		}
	}
		
}
