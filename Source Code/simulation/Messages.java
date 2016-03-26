package simulation;


/**
 * Constant messages
 * @author Rahmi
 *
 */
public class Messages {
	
	public static int fireMsgSize = 5;
	public static int breakinMsgSize = 4;
	public static int systemActiveMsgSize = 2;
	public static int systemDeActiveMsgSize = 2;
	public static int sleepTime = 1000;
	
	
	/**
	 * Array of string to show when fire simulation starts
	 */
	public static String[] fireAction ={
		new String("Fire / smoke detected..."),
		new String("Activating Fire Alarm..."),
		new String("Calling Fire Department..."),
		new String("Sending email to the house owner.."),
		new String("*************************************************")	

	};
	
	/**
	 * Array of string to show when break in simulation starts
	 */
	public static String[] breakinAction ={
		new String("Suspicious movement detected..."),
		new String("Calling the Police..."),
		new String("Sending email to the house owner..."),
		new String("*************************************************")	

	};
	
	/**
	 * Array of string to show system activation string
	 */
	public static String[] SystemActive ={
			new String("Security system is running..."),
			new String("*************************************************")	
			
	};
	
	/**
	 * Array of string to show system deactivation string
	 */
	public static String[] SystemdeActive ={
			new String("Security system is Off..."),
			new String("*************************************************")	
			
	};
	
}
