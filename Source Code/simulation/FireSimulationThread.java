package simulation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import dataObjects.Constants;
import dataObjects.Schedule;

/**
 * This thread starts when fire starts
 * It check the system and start necessary actions
 * 
 * @author Rahmi
 *
 */
public class FireSimulationThread extends Thread{
	
	private volatile boolean running = true;
	private PlaySound fireAlarmSound = new PlaySound(Constants.FIREALARMSOUNDURL);
	
	/**
	 * constructor
	 */
	public FireSimulationThread(){
		
	}
	
	/**
	 * start of the thread
	 */
	@Override
	public void run() {
		
		while(running == true){
			
			//check if system is active
			if(Constants.SYSTEMACTIVE == true){
				//system is active
				System.out.println("system is active");
				//check if Fire Alarm is on
				if(isAlarmActivated()){
					

					Thread showLog = new ShowLogThread(Messages.fireAction, Messages.fireMsgSize);
					showLog.start();
					System.out.println("every thing set, start action");
					
					fireAlarmSound.play();
					sendEmail();
					stopThread();
				}				
			}
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * fire alarm sound stopper
	 */
	public void stopAlarm(){
		fireAlarmSound.stop();
	}

	/**
	 * assigning running false to stop the thread.
	 */
	public void stopThread(){		
		running = false;
	}
	
	/**
	 * This function checks if in the current moment fire alarm is set
	 * or not. When alarm is set return true. Otherwise false.
	 */
	public boolean isAlarmActivated(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		
		String today = dateFormat.format(cal.getTime());
		
		Schedule fireSch = ScheduleAndConfiguration.curFireAlarmSchedule.get(today);
		
		if( fireSch != null){
			//check if the alarm is activated now
			
		    String mytime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());    
			System.out.println(" start"+fireSch.getFromTime()+" end"+fireSch.getToTime()+" myDate "+mytime+"..");

						
			 if (mytime.compareTo(fireSch.getFromTime()) >= 0 && fireSch.getToTime().compareTo(mytime) >= 0) {

			    	return true;
			   }
		}
		
		return false;
	}
	
	/**
	 * create a new Email to send email.
	 */
	public void sendEmail(){
		new Email(Constants.fireString, Constants.EMAILID);
	}
}
