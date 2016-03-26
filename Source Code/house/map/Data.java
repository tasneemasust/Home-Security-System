package house.map;

import java.util.ArrayList;

/**
 * Contains Locations and Dimensions of all room. Also contains 
 * an array list of selected rooms.
 * 
 * @author Rahmi
 *
 */
public class Data {

	//Dimension of all rooms
	public static int bedOneWidth = 180 - 6, bedOneHeight = 220 - 12;
	public static int bedTwoWidth = 130 - 6, bedTwoHeight = 220 - 12;
	public static int livingroomWidth = 275, livingroomHeight = 270 - 12;
	public static int diningroomWidth = 125, diningroomHeight = 120 - 0;
	public static int kitchenWidth = 145, kitchenHeight = 130 - 10;
	public static int closetWidth = 95, closetHeight = 65 - 6;
	public static int bathOneWidth = 95, bathOneHeight = 145 - 6;
	public static int bathTwoWidth = 85, bathTwoHeight = 145 - 6;

	//Location of all rooms
	public static int bedOneX = 180 - 6, bedOneY = 220 - 6;
	public static int bedTwoX = 130 - 6, bedTwoY = 220 - 6;
	public static int livingroomX = 275, livingroomY = 270;
	public static int diningroomX = 125, diningroomY = 120;
	public static int kitchenX = 145, kitchenY = 148;
	public static int closetX = 95, closetY = 65;
	public static int bathOneX = 95, bathOneY = 145;
	public static int bathTwoX = 85, bathTwoY = 145;

	private static ArrayList<Room> selectedRoom = new ArrayList<>();

	public Data() {

	}

	/**
	 * Returns the array of selected rooms.
	 * @return
	 */
	public static ArrayList<Room> getSelectedRoom() {
		return selectedRoom;
	}

	/**
	 * add a room to selectedRoom ArrayList
	 * @param room
	 */
	public static void selectRoom(Room room) {
		selectedRoom.add(room);
	}

	/**
	 * remove a room from selectedRoom ArrayList
	 * @param room
	 */
	public static void deSelectRoom(Room room) {

		if (selectedRoom.contains(room)) {
			selectedRoom.remove(room);
		}
	}
	
	/**
	 * Remove all the rooms from selectedRoom ArrayList
	 */
	public static void clearSelectRoom() {
		selectedRoom.clear();
	}
}
