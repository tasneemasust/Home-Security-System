package dataObjects;

/**
 * Schedule type object.
 * @author Rahmi
 *
 */
public class Schedule {
	
	String date;
	String from_time;
	String to_time;
	//String config_type;
	
	public Schedule(){
		
	}
	public Schedule(String date, String from_time, String to_time){
		this.date = date;
		this.from_time = from_time;
		this.to_time = to_time;
		
	}
	
	/**
	 * Getter method
	 * @return date
	 */
	public String getDate(){
		return this.date;
	}
	
	/**
	 * Getter method
	 * @return start time
	 */
	public String getFromTime(){
		return this.from_time;
	}
	
	/**
	 * Getter method
	 * @return end time
	 */
	public String getToTime(){
		return this.to_time;
	}

}
