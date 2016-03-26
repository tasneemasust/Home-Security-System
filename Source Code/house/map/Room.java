package house.map;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * Room type object. 
 * @author Rahmi
 *
 */
public class Room extends JButton{
	
	private static final long serialVersionUID = 1L;
	private JLabel fireAlarmIndicatorLabel;
	private JLabel sensorIndicatorLabel;

	
	private String area_id;
	private String room_type;
	private Dimension dimension;
	private boolean sensor, fireAlerm;

	private int count;
	
	/**
	 * Constructor, default
	 */
	public Room(){
		this.setOpaque(false);
  		this.setContentAreaFilled(false);
  		this.setBorderPainted(false);
  		
	}
	
	/**
	 * Constructor
	 */
	public Room(String area_id, String room_type, Dimension dimension, int locX, int locY){
		this.area_id = area_id;
		this.room_type = room_type;
		this.dimension = dimension;
		this.count = 0;
		
		this.setOpaque(false);
  		this.setContentAreaFilled(false);
  		this.setBorderPainted(false);
  		this.setLayout(null);
  		
  		setUpfireAlarmIndicatorLabel();
		setUpsensorIndicatorLabel();
	}
	
	/**
	 * Constructor
	 */
	public Room(String area_id, String room_type, Dimension dimension){
		this.area_id = area_id;
		this.room_type = room_type;
		this.dimension = dimension;
		this.count = 0;
		
		this.setOpaque(false);
  		this.setContentAreaFilled(false);
  		this.setBorderPainted(false);
  		this.setLayout(null);
  		
  		setUpfireAlarmIndicatorLabel();
  		setUpsensorIndicatorLabel();
	}
	
	/**
	 * Setup the room temperature sensor indicator.
	 * It shows yellow color when temperature sensor 
	 * is activated. otherwise white.
	 */
	public void setUpfireAlarmIndicatorLabel(){
		fireAlarmIndicatorLabel = new JLabel();
		fireAlarmIndicatorLabel.setBounds(0, 0, 10, 10);
		fireAlarmIndicatorLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		fireAlarmIndicatorLabel.setOpaque(true);
		add(fireAlarmIndicatorLabel);
	}
	
	/**
	 * Setup the room motion sensor indicator.
	 * It shows green color when motion sensor 
	 * is activated. otherwise white.
	 */
	public void setUpsensorIndicatorLabel(){
		sensorIndicatorLabel = new JLabel();
		sensorIndicatorLabel.setBounds(10, 0, 10, 10);
		sensorIndicatorLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		sensorIndicatorLabel.setOpaque(true);
		add(sensorIndicatorLabel);
	}
	
	/**
	 * setter function to set area_id
	 * @param area_id
	 */
	public void setAreaId(String area_id){
		this.area_id = area_id;
	}
	
	/**
	 * setter function to set room_type
	 * @param room_type
	 */
	public void setRoomType(String room_type){
		this.room_type = room_type;
	}
	
	/**
	 * setter function to set dimension of the room
	 * @param dimension
	 */
	public void setDimension(Dimension dimension){
		this.dimension = dimension;
	}
	
	/**
	 * setter function to set color of the temperature
	 * sensor of the room.
	 * @param color
	 */
	public void setfireAlarmIndicatorColor(Color color){
		fireAlarmIndicatorLabel.setBackground(color);
	}
	
	/**
	 * setter function to set color of the motion
	 * sensor of the room.
	 * @param color
	 */
	public void setSensorIndicatorColor(Color color){
		sensorIndicatorLabel.setBackground(color);
	}


	/**
	 * Getter function
	 * @return area_id
	 */
	public String getAreaId(){
		return this.area_id;
	}
	
	/**
	 * Getter function
	 * @return room_type
	 */
	public String getRoomType(){
		return this.room_type;
	}
	
	public Dimension getDimension(){
		return this.dimension;
	}
	
	/**
	 * Setter function of the count. count odd means
	 * room is selected, even means room is not selected.
	 */
	public void setCount(int i){
		this.count = i;
	}
	
	/**
	 * increament count by one
	 * @return count
	 */
	public int getCount(){
		this.count++;
		return count;
	}
	
	/**
	 * Getter function
	 * @return true if motion sensor is set.
	 */
	public boolean isSensor() {
		return sensor;
	}

	/**
	 * Setter function
	 * @param sensor
	 */
	public void setSensor(boolean sensor) {
		this.sensor = sensor;
	}

	/**
	 * Getter function
	 * @return true if temperature sensor is set.
	 */
	public boolean isFireAlerm() {
		return fireAlerm;
	}

	/**
	 * Setter function
	 * @param fireAlerm
	 */
	public void setFireAlerm(boolean fireAlerm) {
		this.fireAlerm = fireAlerm;
	}
}
