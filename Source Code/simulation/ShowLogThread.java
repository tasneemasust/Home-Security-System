package simulation;

import dataObjects.Constants;

/**
 * This thread shows live log on activelog panel at the 
 * bottom of the device window.
 * @author Rahmi
 *
 */
public class ShowLogThread extends Thread{
	
	private volatile boolean running = true;

	private int msgcount;
	String[] str;
	
	public  ShowLogThread(){}
	
	/**
	 * Constructor.
	 * @param str string array of messages
	 * @param n size of the array
	 */
	public  ShowLogThread(String[] str, int n){
		this.str = str;
		this.msgcount = n;

	}
	
	/**
	 * thread stopper
	 */
	public void stopRunning() {
		running = false;		
	}

	/**
	 * thread starts
	 */
	@Override
	public void run() {
		
		System.out.println(Constants.fireString);	
		
    	int i = 0;
        while (running == true && i < msgcount){
        	
			DeviceWindow.activeLog.writeOnActiveLog(str[i]);
        	i++;
        	
        	try {
            	
        		Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }		
	}
}
