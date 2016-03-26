package DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	/**
	 * Returns a connection to database
	 * @return Connection
	 */
	public static Connection getConnection() {
		try {
			return new DBConnection().dbConnect(DBConnection.dataBaseUrl,
					DBConnection.dataBaseUserName,
					DBConnection.dataBasePassWord);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Executes update
	 * @param query
	 * @return 1 if successful else 0
	 */
	public static int executeQueryUpdate(String query) {

		Connection conn = getConnection();
		Statement statement = null;

		try {
			statement = (Statement) conn.createStatement();
			return statement.executeUpdate(query);
		} catch (Exception ex) {
			System.out.println("mess :" + ex.toString());
			return 0;
		} finally {
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
	}
}
