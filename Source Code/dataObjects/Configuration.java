package dataObjects;

/**
 * This is a Configuration type object.
 * There is Configuration type object for each area or room
 * 
 * @author Rahmi
 *
 */

public class Configuration {
	
	private String area_id;
	private String config_type;
	
	public Configuration(String area_id, String config_type){
		
		this.area_id = area_id;
		this.config_type = config_type;
		
	}
	
	/**
	 * 
	 * @return String the area_id 
	 */
	public String getAreaId(){
		return this.area_id;
	}
	
	/**
	 * 
	 * @return String Configuration type
	 */
	public String getConfigType(){
		return this.config_type;
	}
}
