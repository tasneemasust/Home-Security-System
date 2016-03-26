package DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import dataObjects.Constants;
//import ui.rooms.Room;

/**
 * Insert user data, verifies login information.
 *
 */
public class LoginRegisteration {

	/**
	 * Returns if the userid exists in the database
	 * @param userId
	 * @return true if userid exixts
	 */
	public static boolean exists(String userId) {
		String sql = "select user_id from user_info where user_id = '" + userId
				+ "'";
		Connection conn = DBUtil.getConnection();
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				String user = rs.getString(1);
				if (user != null) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				conn.close();
				stmt.close();
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return false;
	}

	/**
	 * Saves given user information into the database
	 * @param firstName
	 * @param lastName
	 * @param address
	 * @param phone
	 * @param email
	 * @param userId
	 * @param password
	 * @param level
	 * @return true if successful
	 */
	public static boolean saveUserInfo(String firstName, String lastName,
			String address, String phone, String email, String userId,
			String password, String level) {
		String query = "INSERT INTO `SecuritySystem`.`user_info` (`user_id`, `first_name`,"
				+ "`last_name`, `address`, `email`, `password`, `phone`, level) VALUES (\""
				+ userId
				+ "\", \""
				+ firstName
				+ "\", \""
				+ lastName
				+ "\", \""
				+ address
				+ "\", \""
				+ email
				+ "\", \""
				+ password
				+ "\", \"" + phone + "\", '" + level + "');";

		System.out.println(query);
		if (DBUtil.executeQueryUpdate(query) == 1) {
			return true;
		}
		return false;
	}
	
	/**
	 * Enters information in user2system table
	 * @param userId
	 * @return true if successful
	 */
	public static boolean saveUser2System(String userId) {
		String query = "INSERT INTO `SecuritySystem`.`user2system` (`user_id`, `system_id`) VALUES ('"
				+ userId
				+ "', '"
				+ Constants.SYSTEMID
				+ "');";

		System.out.println(query);
		if (DBUtil.executeQueryUpdate(query) == 1) {
			return true;
		}
		return false;
	}
	

	/**
	 * Checks if admin login information is correct 
	 * @param userId
	 * @param password
	 * @return true if information is correct 
	 */
	public static boolean checkAdminLogin(String userId, String password) {
		String sql = "select user_id, password, level from user_info where user_id = '"
				+ userId + "'";
		Connection conn = DBUtil.getConnection();
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				String user = rs.getString(1);
				if (user != null) {
					String pwd = rs.getString(2);
					String level = rs.getString(3);
					// System.out.println("pwd::"+pwd+" level::"+level);
					if (password.equals(pwd) && level.equalsIgnoreCase("admin")) {
						System.out.println("In here");
						return true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				conn.close();
				stmt.close();
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * Checks if login information is correct 
	 * @param userId
	 * @param password
	 * @return true if information is correct 
	 */
	public static boolean checkLogin(String userId, String password) {
		String sql = "select user_id, password from user_info where user_id = '"
				+ userId + "'";
		Connection conn = DBUtil.getConnection();
		ResultSet rs = null;
		Statement stmt = null;
		ResultSet rs1 = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				String user = rs.getString(1);
				if (user != null) {
					String pwd = rs.getString(2);
					if (password.equals(pwd)) {
						System.out.println("In here");
						sql = "select system_id from SecuritySystem.user2system where user_id = \'"
								+ user + "\'";
						System.out.println(sql);
						 rs1 = stmt.executeQuery(sql);
						if (rs1.next()) {
							String systemId = rs1.getString(1);
							if (systemId != null) {
								//UserInfo.setSystemId(Integer.parseInt(systemId));
							}
						}
						return true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				conn.close();
				stmt.close();
				rs.close();
				rs1.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
