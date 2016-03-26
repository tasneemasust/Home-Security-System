package DBConnection;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database connection class
 * @author Rahmi
 *
 */
public class DBConnection {

	public static String dataBaseUrl = "jdbc:mysql://localhost:3307/SecuritySystem";
	public static String dataBaseUserName = "root";
	public static String dataBasePassWord = "";

	public DBConnection() {
	}

	/**
	 * Connects to the database with given url, userid and password
	 * @param db_connect_string
	 * @param db_userid
	 * @param db_password
	 * @return Connection
	 * @throws SQLException
	 */
	public Connection dbConnect(String db_connect_string, String db_userid,
			String db_password) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection(db_connect_string,
					db_userid, db_password);
			System.out.println("connection done");

			return conn;
		} catch (Exception e) {
			System.out
					.println("DBConnection.java : Exception Occurred in dbConnect method!!!");
			return null;
		}

	}
}
