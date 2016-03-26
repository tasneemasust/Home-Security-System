package dataObjects;

/**
 * User object.
 *
 */
public class UserInfo {

	private String userId;

	private static int systemId = 1;

	public static int getSystemId() {
		return systemId;
	}

	/**
	 * Setter method
	 * @param sysId
	 */
	public static void setSystemId(int sysId) {
		systemId = sysId;
	}

	/**
	 * Getter method
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Setter method
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
