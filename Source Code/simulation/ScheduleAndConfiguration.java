package simulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import DBConnection.ConfigurationSchedule;
import dataObjects.Configuration;
import dataObjects.Constants;
import dataObjects.Schedule;

/**
 * This class get all configuration and schedule data from database. And then
 * stores in to HashMap.
 * 
 * @author Rahmi
 *
 */
public class ScheduleAndConfiguration {

	public static Map<String, Schedule> curFireAlarmSchedule = new HashMap<String, Schedule>();
	public static Map<String, Schedule> curSensorSchedule = new HashMap<String, Schedule>();
	public static Map<String, Configuration> curConfiguration = new HashMap<String, Configuration>();

	ConfigurationSchedule cs;


	/**
	 * constructor
	 */
	public ScheduleAndConfiguration() {
		// this is for getting data from database
		cs = new ConfigurationSchedule();
	}

	/**
	 * add new configuration object to hash map
	 * 
	 * @param con
	 */
	public void addConfiguration(Configuration con) {
		curConfiguration.put(con.getAreaId() + con.getConfigType(), con);
	}

	/**
	 * add new fire alarm schedule to hash map
	 * 
	 * @param sch
	 */
	public void addFireAlarmSchedule(Schedule sch) {
		curFireAlarmSchedule.put(sch.getDate(), sch);
	}

	/**
	 * Add new sensor schedule to hash map
	 * 
	 * @param sch
	 */
	public void addSensorSchedule(Schedule sch) {
		curSensorSchedule.put(sch.getDate(), sch);
	}

	/**
	 * Getter method
	 * Returns the size of temperature sensor Schedule size
	 * @return 
	 */
	public int getCurAlarmScheduleSize() {
		return curFireAlarmSchedule.size();
	}

	/**
	 * Getter method
	 * Return the size of motion sensor Schedule size
	 * @return the size of sensor Schedule size
	 */
	public int getCurSensorScheduleSize() {
		return curSensorSchedule.size();
	}


	/**
	 * This function get array list of Configuration, and stores in a HashMap
	 * named curConfiguration.
	 */
	public void setCofiguration() {
		clearcurConfiguration();
		ArrayList<Configuration> tempCon = cs.getDatafromConfigurationTable();
		for (int i = 0; i < tempCon.size(); i++) {
			Configuration con = tempCon.get(i);
			curConfiguration.put(con.getAreaId() + con.getConfigType(), con);
		}
	}

	/**
	 * This function get array list of Temperature sensor Schedule, and stores
	 * in a HashMap named curFireAlarmSchedule.
	 */
	public void getFireAlarmSchedule() {
		ArrayList<Schedule> tempSch = cs.getDatafromScheduleTable(Constants.FIREALARM);
		for (int i = 0; i < tempSch.size(); i++) {
			Schedule sch = tempSch.get(i);
			curFireAlarmSchedule.put(sch.getDate(), sch);

		}
	}

	/**
	 * This function get array list of Motion sensor Schedule, and stores in a
	 * HashMap named curFireAlarmSchedule.
	 */
	public void getSensorSchedule() {
		ArrayList<Schedule> tempSch = cs.getDatafromScheduleTable(Constants.SENSOR);
		for (int i = 0; i < tempSch.size(); i++) {
			Schedule sch = tempSch.get(i);
			curSensorSchedule.put(sch.getDate(), sch);

		}
	}

	/**
	 *  return the current fire alarm schedule
	 * @return ArrayList<Schedule>
	 */
	public ArrayList<Schedule> getFireAlarmScheduleList() {
		return cs.getDatafromScheduleTable(Constants.FIREALARM);
	}

	/**
	 * return the current sensor schedule
	 * @return ArrayList<Schedule>
	 */
	public ArrayList<Schedule> getSensorScheduleList() {
		return cs.getDatafromScheduleTable(Constants.SENSOR);
	}

	/**
	 * Reset the current configuration
	 */
	public void clearcurConfiguration() {
		curConfiguration.clear();
	}
}
