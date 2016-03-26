package dataObjects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * This is the Utility class for getting intermediate dates.
 * @author Rahmi
 *
 */
public class DateUtil {

	/**
	 * Constructor
	 */
	public DateUtil(){
		
	}
	
	/**
	 * 
	 * @param startdate
	 * @param enddate
	 * @return A list of dates between startdate and enddate
	 */
	public static List<Date> getDaysBetweenDates(Date startdate, Date enddate)
	{
	    List<Date> dates = new ArrayList<Date>();
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(startdate);

	    while (calendar.getTime().before(enddate))
	    {
	        Date result = calendar.getTime();
	        dates.add(result);
	        calendar.add(Calendar.DATE, 1);
	    }
	    dates.add(enddate);
	    return dates;
	}
	
	/**
	 * 
	 * @param startdate
	 * @param enddate
	 * @return A list of dates of weekdays between startdate and enddate
	 */
	public static List<Date> getWeekdaysBetweenDates(Date startdate, Date enddate)
	{
	    List<Date> dates = new ArrayList<Date>();
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(startdate);

	    while (calendar.getTime().before(enddate))
	    {
	    	int day = calendar.get(Calendar.DAY_OF_WEEK);
	    	if(day != 1 && day != 7){
	    		Date result = calendar.getTime();
	    		dates.add(result);
	    	}
	        calendar.add(Calendar.DATE, 1);
	    }
	    
	    int day = calendar.get(Calendar.DAY_OF_WEEK);
    	if(day != 1 && day != 7){
    		Date result = calendar.getTime();
    		dates.add(result);
    	}

	    return dates;
	}
	
	/**
	 * 
	 * @param startdate
	 * @param enddate
	 * @return A list of dates of weekend between startdate and enddate
	 */
	public static List<Date> getWeekendsBetweenDates(Date startdate, Date enddate)
	{
	    List<Date> dates = new ArrayList<Date>();
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(startdate);

	    while (calendar.getTime().before(enddate))
	    {
	    	int day = calendar.get(Calendar.DAY_OF_WEEK);
	    	if(day == 1 || day == 7){
	    		Date result = calendar.getTime();
	    		dates.add(result);
	    	}
	        calendar.add(Calendar.DATE, 1);
	    }
	    
	    int day = calendar.get(Calendar.DAY_OF_WEEK);
    	if(day == 1 || day == 7){
    		Date result = calendar.getTime();
    		dates.add(result);
    	}
        calendar.add(Calendar.DATE, 1);
        
	    return dates;
	}
	
	
	/**
	 * This function compares two time. Receive two strings
	 * parse them to get time than compares.
	 *  
	 * @param before
	 * @param after
	 * @return True if before time is less than after time
	 */
	public static boolean timeComparison(String before, String after) {
		SimpleDateFormat parser = new SimpleDateFormat("HH:mm:ss");

		Date start = null;
		try {
			start = parser.parse(before);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date end = null;
		try {
			end = parser.parse(after);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (start.after(end)) {
			return false;
		}
		return true;
	}
	
	/**
	 * Compares two Date type variables.
	 * @param start
	 * @param end
	 * @return True if start date is less than end date
	 */
	public static boolean timeComparison(Date start, Date end) {
		if (start.after(end)) {
			return false;
		}
		return true;
	}
}
