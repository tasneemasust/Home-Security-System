package DBConnection;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dataObjects.Constants;

/**
 * This class has most of the database operation done in the project.
 * Like get data from tables, insert new data to the tables.
 * @author Rahmi
 *
 */
public final class DatabaseOperation {
	
	DBConnection db;
	Connection conn;
	
	ResultSet resultSet;
	 
	public DatabaseOperation(){
		db = new DBConnection();
		    
	}
	

	/**
	 * Insert user information to database.
	 * @param user_id
	 * @param fname
	 * @param lname
	 * @param address
	 * @param email
	 * @param password
	 * @param phone
	 */
	public void insertUser( String user_id, String fname, String lname, 
			String address, String email,  String password, String phone){
		
		String query = "INSERT INTO `SecuritySystem`.`user_info` (`user_id`, `first_name`,"+
				"`last_name`, `address`, `email`, `password`, `phone`) VALUES (\"" +user_id +"\", \""+
				fname + "\", \"" + lname + "\", \"" + address + "\", \""+ email +"\", \""+ password+
				"\", \""+ phone +"\");";
		
		System.out.println(query);
		executeQueryUpdate(query);
	}
	
	
	/**
	 * Insert configuration data to database.
	 * @param config_type
	 * @param area_id
	 * @param room_type
	 * @param date
	 * @param user_id
	 */
	public void insertConfiguration( String config_type, String area_id, String room_type, 
			String date, String user_id){
		
		String query = "INSERT INTO `SecuritySystem`.`configuration` (`config_type`, "+
				"`area_id`, `room_type`, `date`, `user_id`) VALUES (\"" + config_type+"\", \""+
				area_id+"\", \""+room_type+"\", \""+date +"\", \""+ user_id+ "\");" ;

		
		System.out.println(query);
		executeQueryUpdate(query);
	}
	
	/**
	 * Insert schedule data to database
	 * @param days
	 * @param fromTime
	 * @param toTime
	 * @param security
	 */
	public void insertIntoSchedule( List<String> days, String fromTime, String toTime, String security){
		
		String query = "";	
		for(int i = 0; i < days.size(); i++){
		
			query = "INSERT INTO `SecuritySystem`.`schedule`(`date`, `from_time`, `to_time`,"
					+ " `system_id` , `config_type`)  VALUES ( \"" + days.get(i)+"\", \"" + 
					fromTime + "\", \"" + toTime + "\",\"" + Constants.SYSTEMID +"\", " +
					"\"" + security + "\"" + ")  ON DUPLICATE KEY UPDATE date = LAST_INSERT_ID(date),"
					//+ " system_id = LAST_INSERT_ID(system_id), config_type = LAST_INSERT_ID(config_type)"
					+ " from_time = \""+fromTime +"\",  to_time = \""+ toTime +"\";";
			System.out.println(query);
			executeQueryUpdate(query);
		
		}
		

	}

	/**
	 * Get user data from database.
	 * @return
	 */
	public  ArrayList<String> getDatafromUserTable(){
		
		ArrayList<String> userList = new ArrayList<String>();
		
		String query = "select * from SecuritySystem.user_info";
		
		try {
			conn = db.dbConnect(DBConnection.dataBaseUrl, DBConnection.dataBaseUserName,
					DBConnection.dataBasePassWord);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		Statement statement = null;
		try{
			statement = (Statement) conn.createStatement();
			resultSet = statement.executeQuery(query);

	        try {
				while(resultSet.next()) {
					String key1 = (resultSet.getString("user_id")).trim();
					String key2 = (resultSet.getString("first_name")).trim();
					String key3 = (resultSet.getString("last_name")).trim();
					String key4 = (resultSet.getString("address")).trim();
					String key5 = (resultSet.getString("email")).trim();
					String key6 = (resultSet.getString("password")).trim();
					String key7 = (resultSet.getString("phone")).trim();
					
					userList.add(key1);

					System.out.println(key1 +", "+ key2 +", "+key3 +", "+ key4 +", "+key5 +", "+ 
					key6 +", "+key7); 
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		catch (Exception ex) {
            System.out.println("mess :" + ex.toString());
        }
		
		finally{
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
		return userList;
	}
	
	
	
	/**
	 * method for execute update queries. opens a
	 * database connection. execute query then 
	 * closes the connection
	 * @param query
	 */
	public void executeQueryUpdate(String query) {
		
		try {
			conn = db.dbConnect(DBConnection.dataBaseUrl, DBConnection.dataBaseUserName,
					DBConnection.dataBasePassWord);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		Statement statement = null; 
		try{
			statement = (Statement) conn.createStatement();
			statement.executeUpdate(query);
			
		}
		catch (Exception ex) {
            System.out.println("mess :" + ex.toString());
        }
		finally{
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
