package simulation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import dataObjects.Constants;
import dataObjects.Schedule;

/**
 * This is a thread class. Starts after break in simulation clicked.
 *  First it checks if the system is active, then if the schedule 
 *  is set for the current time. This keep checking every 10 seconds.
 *  Stops automatically if everything is set accordingly and it could
 *  start another thread for taking appropriate action. Or stop simulation 
 *  is clicked.
 *  
 * @author Rahmi
 *
 */
public class BreakInSimulationThread extends Thread {

	private volatile boolean running = true;
	private Schedule sensorSch;


	/**
	 * constructor
	 */
	public BreakInSimulationThread() {

	}

	/**
	 * start of the thread
	 */
	@Override
	public void run() {

		while (running == true) {

			// check if system is active
			if (Constants.SYSTEMACTIVE == true) {
				// system is active
				System.out.println("system is active");
				// check if Fire Alarm is on
				if (isSensorActivated()) {
					System.out.println("every thing set, start action");

					Thread showLog = new ShowLogThread(Messages.breakinAction, Messages.breakinMsgSize);
					showLog.start();
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
	 * Stop thread by changing running value
	 */
	public void stopThread() {
		running = false;
	}

	/**
	 * This function checks if in the current moment fire alarm is set or not.
	 * When alarm is set return true. Otherwise false.
	 * 
	 */
	public boolean isSensorActivated() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();

		String today = dateFormat.format(cal.getTime());

		sensorSch = ScheduleAndConfiguration.curSensorSchedule.get(today);

		if (sensorSch != null) {
			String mytime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());

			System.out.println(
					" start" + sensorSch.getFromTime() + " end" + sensorSch.getToTime() + " myDate " + mytime + "..");

			// if (myDate.after(start) && myDate.before(end)) {
			if (mytime.compareTo(sensorSch.getFromTime()) >= 0 && sensorSch.getToTime().compareTo(mytime) >= 0) {

				return true;
			}
		}

		return false;
	}

	/**
	 * sends email to the user.
	 */
	public void sendEmail(){
		new Email(Constants.breakInString, Constants.EMAILID);
	}
}
