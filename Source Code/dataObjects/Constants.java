package dataObjects;

import simulation.PlaySound;

/**
 * This class contains all the constants and 
 * global variables used in the total system.
 *
 */
public class Constants {
	
	public static final String CONFIG = "Configuration";
	public static final String BILL = "Bill";
	public static final String REGISTER_CARD = "Register";
	public static final String SCHEDULE_CARD = "Schedule";
	public static final String LOGIN_CARD = "Login";
	public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static final String ADMIN_LOGIN = "Admin_Login";
	public static final String INITIALIZE = "Main";
	public static final String SENSOR = "sensor";
	public static final String FIRE_ALARM = "fire_alarm";
//	public static final String CONTROL_PANEL = "Control_Panel";
	public static final String TAB_PANE = "Tab";
	public static final String DEVICE = "Device";
	public static final double FIRE_ALARM_RATE = 3;
	public static final double SENSOR_RATE = 2;
	

	
	public static boolean SYSTEMACTIVE = true;

	public static final String SYSTEM_LOGIN = "system_login";
	public static final String ADMIN_PANEL = "admin_panel";

	public static String SYSTEMID = "security_system_1";
	public static String adminUserId = "";
	public static String machineUserId = "";

	public static String simulationId = "";
	
	public static String FIREALARM = "fire_alarm";
//	public static String SENSOR = "sensor";
	public static String EMAILID = "sdevate@scu.edu";

	public static String fireAlarmString = "Temperature Sensor";
	public static String SensorString = "Motion Sensor";	
	public static String fireString = "Fire";
	public static String breakInString = "BreakIn";
	public static String simulateString = "Simulate";
	public static String stopString = "Stop Simulation";
	
	
	public static String FIREALARMSOUNDURL = "sound/FireAlarm.wav";
	public static String BREAKINSOUNDURL = "sound/BreakIn.wav";

	public static final PlaySound FIREALARMSOUND = new PlaySound(FIREALARMSOUNDURL);
	public static final PlaySound BREAKINSOUND = new PlaySound(BREAKINSOUNDURL);
	//public static final PlaySound FIREBURNSOUND;

	public static void setsimulationId(String simulation){
		simulationId = simulation;
	}

	public static String getsimulationId( ){
		return simulationId ;
	}
	
	public static void setemailId(String email){
		EMAILID = email;
	}

	public static String getemailId( ){
		return EMAILID ;
	}
	
	
}
