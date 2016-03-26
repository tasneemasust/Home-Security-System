package simulation;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLayeredPane;

import dataObjects.Constants;
import house.map.MainStructure;
import house.map.Room;

/**
 * This class show the house map for simulation. This is a JLayeredPane, it has
 * multiple layers to show map and animations.
 * 
 * @author Rahmi
 *
 */
public class ShowHouseMap extends JLayeredPane {

	private static final long serialVersionUID = 1L;
	public static MainStructure house;

	private BreakInSimulation breakInSimulation;
	private FireSimulation fireSimulation;

	/**
	 * constructor, sets up size, dimension.
	 */
	public ShowHouseMap() {

		this.setPreferredSize(new Dimension(600, 600));

		house = new MainStructure();
		house.setPreferredSize(new Dimension(600, 600));
		house.setBounds(0, 0, 600, 600);

		house.disableAllButton();
		showConfigurationInLabel();

		// add house to layered pane;
		this.add(house, new Integer(1));

	}

	/**
	 * Add new layered panel to show break in animation
	 * 
	 * @param roomNumber
	 */
	public void addBreakIn(int roomNumber) {
		breakInSimulation = new BreakInSimulation(roomNumber);
		breakInSimulation.setPreferredSize(new Dimension(600, 600));
		breakInSimulation.setBounds(0, 0, 600, 600);
		breakInSimulation.setOpaque(false);
		this.add(breakInSimulation, new Integer(2));
	}

	/**
	 * Add new layered panel to show fire animation
	 * 
	 * @param roomNumber
	 */
	public void addFire(int roomNumber) {

		fireSimulation = new FireSimulation(roomNumber);
		fireSimulation.setPreferredSize(new Dimension(600, 600));
		fireSimulation.setBounds(0, 0, 600, 600);
		fireSimulation.setOpaque(false);
		this.add(fireSimulation, new Integer(2));
	}

	/**
	 * Stops fire animation thread and sound
	 */
	public void stopFire() {

		// stop all the thread related to it.
		fireSimulation.stopFire();

		Constants.FIREALARMSOUND.stop();
		// stop all sounds
		fireSimulation.setVisible(false);

	}

	/**
	 * Stops break in animation thread
	 */
	public void stopBreakIn() {
		// stop all the thread related to it.
		breakInSimulation.stopBreakIn();
		breakInSimulation.setVisible(false);
	}

	/**
	 * shows the indicator label color for each room. yellow for temperature and
	 * green for motion sensor.
	 */
	public void showConfigurationInLabel() {

		for (int i = 0; i < house.getButtonArray().size(); i++) {

			Room room = (Room) house.getButtonArray().get(i);

			if (ScheduleAndConfiguration.curConfiguration.get(room.getAreaId() + Constants.FIREALARM) != null) {
				room.setfireAlarmIndicatorColor(Color.YELLOW);
			} else {
				room.setfireAlarmIndicatorColor(Color.WHITE);
			}

			if (ScheduleAndConfiguration.curConfiguration.containsKey(room.getAreaId() + Constants.SENSOR)) {
				room.setSensorIndicatorColor(Color.GREEN);
			} else {
				room.setSensorIndicatorColor(Color.WHITE);
			}
		}
	}

}
