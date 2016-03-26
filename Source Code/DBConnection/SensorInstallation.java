package DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import dataObjects.Constants;

/**
 * Saves and gets all the sensor information
 * to and from the database.
 *
 */
public class SensorInstallation {

	/**
	 * Inserts sensor activation data into the database
	 * 
	 * @param configType
	 * @param areaId
	 * @param roomType
	 * @param userId
	 * @return true if successful
	 */
	public static boolean insertConfig(String configType, String areaId,
			String roomType, String userId) {

		String sql = "insert into configuration "
				+ "(config_type, area_id, room_type, date, user_id, system_id) "
				+ "values " + "('" + configType + "', '" + areaId + "', '"
				+ roomType + "', curdate(), '" + userId + "', '"
				+ Constants.SYSTEMID + "')ON DUPLICATE KEY UPDATE "
				+ "room_type = '" + roomType
				+ "', date = curdate(), user_id = '" + userId + "';";

		System.out.println(sql);
		if (DBUtil.executeQueryUpdate(sql) == 1) {
			return true;
		}
		return false;
	}

	/**
	 * Deletes sensor activation data from the database
	 * @param configType
	 * @param areaId
	 * @param roomType
	 * @param userId
	 * @return true if successful
	 */
	public static boolean deleteFromConfig(String configType, String areaId,
			String roomType, String userId) {

		String sql = "DELETE FROM SecuritySystem.configuration WHERE config_type = '"
				+ configType
				+ "' and area_id = '"
				+ areaId
				+ "' and user_id = '" + userId + "';";

		System.out.println(sql);
		if (DBUtil.executeQueryUpdate(sql) == 1) {
			return true;
		}
		return false;
	}

	/**
	 * Deletes schedule information from database
	 * @param configType
	 * @param userId
	 * @return true if successful
	 */
	public static boolean deleteFromSchedule(String configType, String userId) {

		String sql = "DELETE FROM SecuritySystem.schedule WHERE config_type = '"
				+ configType
				+ "' and system_id = '"
				+ Constants.SYSTEMID
				+ "';";

		System.out.println(sql);
		if (DBUtil.executeQueryUpdate(sql) == 1) {
			return true;
		}
		return false;
	}

	/**
	 * Gets customer details from database
	 * @return HashMap of customer information 
	 */
	public static HashMap<String, String> getCustomerDetails() {
		String query = "select i.first_name, i.last_name, i.address, i.email, i.phone "
				+ "from user_info i, user2system u where u.system_id = '"
				+ Constants.SYSTEMID + "' " + "and i.level = 'admin';";
		HashMap<String, String> userInfo = new HashMap<String, String>();

		Connection conn = DBUtil.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			if (rs.next()) {
				userInfo.put(
						"name",
						rs.getString("first_name") + " "
								+ rs.getString("last_name"));
				userInfo.put("address", rs.getString("address"));
				userInfo.put("email", rs.getString("email"));
				userInfo.put("phone", rs.getString("phone"));
			}
			return userInfo;
		} catch (SQLException e) {
			e.printStackTrace();
			return userInfo;
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
			}
			try {
				stmt.close();
			} catch (Exception e) {
			}
			try {
				rs.close();
			} catch (Exception e) {
			}
		}

	}

	/**
	 * Gets the types of sensors activated in a house
	 * @return ArrayList of sensor types activated in the house
	 */
	public static ArrayList<String> getSensorTypes() {
		String query = "select distinct config_type from configuration where system_id = '"
				+ Constants.SYSTEMID + "';";
		System.out.println(query);
		Connection conn = DBUtil.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<String> configType = new ArrayList<String>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				configType.add(rs.getString("config_type"));
				System.out.println("got config_type" + configType);
			}
			return configType;
		} catch (SQLException e) {
			e.printStackTrace();
			return configType;
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
			}
			try {
				stmt.close();
			} catch (Exception e) {
			}
			try {
				rs.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * Returns the number of hours all sensors of the given type have been used
	 * @param configType
	 * @return number of hours sensors have been used
	 */
	public static String createBill(String configType) {
		
		String sql = "select truncate(sum(time_to_sec(subtime(s.to_time, s.from_time)))/3600, 2) as hours "
				+ "from schedule s where s.system_id = '"
				+ Constants.SYSTEMID
				+ "' "
				+ "and s.config_type = '"
				+ configType
				+ "' "
				+ "and MONTH(s.date) = MONTH(CURDATE()) "
				+ "and YEAR(s.date) = YEAR(curdate());";

		System.out.println(sql);
		Connection conn = DBUtil.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String hours = "";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				hours = rs.getString("hours");
			}
			return hours;
		} catch (SQLException e) {
			e.printStackTrace();
			return hours;
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
			}
			try {
				stmt.close();
			} catch (Exception e) {
			}
			try {
				rs.close();
			} catch (Exception e) {
			}
		}

	}

	/**
	 * Returns the number of sensors installed in the house
	 * @param config_type
	 * @return number of sensors
	 */
	public static String getNoOfSensor(String config_type) {
		String number = "";

		String sql = "select count(*) from configuration "
				+ "where system_id = '" + Constants.SYSTEMID + "' "
				+ "and config_type = '" + config_type + "'";
		System.out.println(sql);
		Connection conn = DBUtil.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				number = rs.getString(1);
			}
			return number;
		} catch (SQLException e) {
			e.printStackTrace();
			return number;
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
			}
			try {
				stmt.close();
			} catch (Exception e) {
			}
			try {
				rs.close();
			} catch (Exception e) {
			}
		}
	}

}
