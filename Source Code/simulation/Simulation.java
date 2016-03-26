package simulation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

/**
 * Simulation window
 * @author Rahmi
 *
 */
public class Simulation extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	LoginWindow loginWindow;
	private SimulationControl simulationControl;
	private ScheduleAndConfiguration sc;
	
	/**
	 * Constructor, set up dimensions
	 */
	public Simulation(){
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		setSize(6 * width / 7, height);
		setLocationRelativeTo(null);
		
		setTitle("Simulation Window");
		getContentPane().setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getConfigurationData();
		
		setUpFrame();
		
	    setVisible(true);
	}	
	
	/**
	 * create and add login window and simulation control
	 */
	private void setUpFrame(){
		
		this.setLayout(new GridLayout(1,2));
				
		loginWindow = new LoginWindow();
		loginWindow.setBorder(BorderFactory.createBevelBorder(NORMAL));
		loginWindow.setBackground(Color.ORANGE);
		
		simulationControl = new SimulationControl();
		simulationControl.setBorder(BorderFactory.createBevelBorder(NORMAL));

		simulationControl.setBackground(Color.WHITE);
		add(loginWindow);
 		add(simulationControl);
	}
	
	/**
	 * get configuation data from database
	 */
	public void getConfigurationData(){
		 sc = new ScheduleAndConfiguration();
		sc.setCofiguration();
	}
	
	public static void main(String[] args) {		
		new Simulation();
		   
	}
}
