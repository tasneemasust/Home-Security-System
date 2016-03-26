package DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dataObjects.Configuration;
import dataObjects.Constants;
import dataObjects.Schedule;

/**
 * Get all the configuration and schedule data 
 * from database, and return ArrayLists of
 * schedule and configurations.
 * @author Rahmi
 *
 */
public class ConfigurationSchedule {

	DBConnection db;
	Connection conn;

	ResultSet resultSet;

	public ConfigurationSchedule() {

		db = new DBConnection();

	}

	/**
	 * Gets Data from Configuration Table
	 * @return ArrayList of Configuration information
	 */
	public ArrayList<Configuration> getDatafromConfigurationTable() {

		ArrayList<Configuration> curConfig = new ArrayList<Configuration>();

		String query = "SELECT * FROM SecuritySystem.configuration WHERE system_id = \""
				+ Constants.SYSTEMID + "\";";

		System.out.println(query);

		try {
			conn = db.dbConnect(DBConnection.dataBaseUrl,
					DBConnection.dataBaseUserName,
					DBConnection.dataBasePassWord);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		Statement statement = null;
		try {
			statement = (Statement) conn.createStatement();
			resultSet = statement.executeQuery(query);

			try {
				while (resultSet.next()) {
					String key1 = (resultSet.getString("config_type")).trim();
					String key2 = (resultSet.getString("area_id")).trim();

					System.out.println(key1 + ", " + key2);

					curConfig.add(new Configuration(key2, key1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			System.out.println("mess :" + ex.toString());
		}

		finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return curConfig;
	}

	/**
	 * Gets Data from Schedule Table
	 * @param sensor
	 * @return ArrayList of Schedule information
	 */
	public ArrayList<Schedule> getDatafromScheduleTable(String sensor) {

		ArrayList<Schedule> curSch = new ArrayList<Schedule>();

		String query = "SELECT * FROM SecuritySystem.schedule WHERE date >= CURDATE() and"
				+ " system_id = \""
				+ Constants.SYSTEMID
				+ "\" and config_type = \"" + sensor + "\";";

		System.out.println(query);
		try {
			conn = db.dbConnect(DBConnection.dataBaseUrl,
					DBConnection.dataBaseUserName,
					DBConnection.dataBasePassWord);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		Statement statement = null;
		try {
			statement = (Statement) conn.createStatement();
			resultSet = statement.executeQuery(query);

			try {
				while (resultSet.next()) {
					String key1 = (resultSet.getString("date")).trim();
					String key2 = (resultSet.getString("from_time")).trim();
					String key3 = (resultSet.getString("to_time")).trim();

					curSch.add(new Schedule(key1, key2, key3));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			System.out.println("mess :" + ex.toString());
		}

		finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return curSch;
	}

	/**
	 * Gets Data from Schedule Table
	 * @param date
	 * @param sensor
	 * @return Schedule object
	 */
	public Schedule getDatafromScheduleTable(String date, String sensor) {

		Schedule sch = new Schedule();
		int count = 0;

		String query = "SELECT * FROM SecuritySystem.schedule WHERE date >= CURDATE() and"
				+ " system_id = \""
				+ Constants.SYSTEMID
				+ "\" and config_type = \""
				+ sensor
				+ "\""
				+ "and date = \""
				+ date + "\";";

		try {
			conn = db.dbConnect(DBConnection.dataBaseUrl,
					DBConnection.dataBaseUserName,
					DBConnection.dataBasePassWord);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		Statement statement = null;
		try {
			statement = (Statement) conn.createStatement();
			resultSet = statement.executeQuery(query);

			try {
				while (resultSet.next()) {
					String key1 = (resultSet.getString("date")).trim();
					String key2 = (resultSet.getString("from_time")).trim();
					String key3 = (resultSet.getString("to_time")).trim();

					sch = new Schedule(key1, key2, key3);
					count++;
					System.out.println(key1 + ", " + key2);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			System.out.println("mess :" + ex.toString());
		}

		finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (count == 0) {
			return null;
		} else
			return sch;
	}
}
