package house.map;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * This class constructs the total house map with
 * rooms and buttons.
 * @author Rahmi
 *
 */
public class MainStructure extends JPanel implements ActionListener {

	
	private static final long serialVersionUID = 1L;

	public ArrayList<JButton> buttonArray = new ArrayList<>();

	private Shape mShapeOne;
	private Shape bedRoomOne;
	private Shape bedRoomTwo;
	private Shape bathRoomOne;
	private Shape bathRoomTwo;
	private Shape closet1;

	private Room btnLivingRoom;
	private Room btnbedRoomOne;
	private Room btnbedRoomTwo;
	private Room btnDiningPlace;
	private Room btnKitchen;
	private Room btnBathOne;
	private Room btnBathTwo;
	private Room btnCloset;

	Livingroom livingroom;
	MasterBedroom masterBedroom;
	Bedroom bedroom;
	BathOne bathOne;
	BathTwo bathTwo;
	Closet closet;
	DiningPlace diningPlace;
	Kitchen kitchen;
	
	Border thickBorder = new LineBorder(Color.MAGENTA, 6);

	public MainStructure() {

		//boundary of the house
		double shapeOneX = 50;
		double shapeOneY = 50;
		double shapeOneWidth = 500;
		double shapeOneHeight = 500;

		// Create an Rectangular house structure
		mShapeOne = new Rectangle2D.Double(shapeOneX, shapeOneY, shapeOneWidth,
				shapeOneHeight);

		setBackground(Color.white);
		this.setPreferredSize(new Dimension(600,600));

		setLayout(null);

		//setting function for each room
		setupLivingRoom();
		setupDiningPlace();
		setupMasterBedroom();
		setupBedroom();
		setupBathOne();
		setupBathTwo();
		setupKitchen();
		setupCloset();

	}

	/**
	 * This function draws outline of house, and separate each 
	 * room with boundary.
	 */
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setStroke(new BasicStroke(5));
		g2.draw(mShapeOne);

		// Create Areas from the shapes.
		Area areaOne = new Area(mShapeOne);

		bedRoomOne = new Rectangle2D.Double(270, 50, 180, 220);
		bathRoomOne = new Rectangle2D.Double(450, 50, 100, 150);
		closet1 = new Rectangle2D.Double(450, 200, 100, 70);

		bedRoomTwo = new Rectangle2D.Double(50, 50, 130, 220);
		bathRoomTwo = new Rectangle2D.Double(180, 50, 90, 150);

		// Draw the outline of the resulting Area.
		g2.setPaint(Color.black);
		g2.draw(areaOne);
		g2.draw(bedRoomOne);
		g2.draw(bathRoomOne);
		g2.draw(closet1);
		g2.draw(bedRoomTwo);
		g2.draw(bathRoomTwo);

		g2.drawLine(50, 420, 200, 420);
		g2.drawLine(200, 420, 200, 510);
		g2.drawLine(200, 510, 200, 550);

	}

	/**
	 * Action listener, detect which room is clicked and 
	 * call getSelectedRoom().
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if ((Room) e.getSource() == btnLivingRoom) {
			System.out.println("living room clickecd");
			getSelectedRoom(btnLivingRoom, (JPanel) livingroom);

		} else if ((Room) e.getSource() == btnDiningPlace) {
			System.out.println("dining room clickecd");
			getSelectedRoom(btnDiningPlace, (JPanel) diningPlace);

		} else if ((Room) e.getSource() == btnbedRoomOne) {
			System.out.println("Bedroom1 clickecd");
			getSelectedRoom(btnbedRoomOne, (JPanel) masterBedroom);

		} else if ((Room) e.getSource() == btnbedRoomTwo) {
			System.out.println("Bedroom2 clickecd");
			getSelectedRoom(btnbedRoomTwo, (JPanel) bedroom);

		} else if ((Room) e.getSource() == btnBathOne) {
			System.out.println("dining room clickecd");
			getSelectedRoom(btnBathOne, (JPanel) bathOne);

		} else if ((Room) e.getSource() == btnBathTwo) {
			System.out.println("dining room clickecd");
			getSelectedRoom(btnBathTwo, (JPanel) bathTwo);

		} else if ((Room) e.getSource() == btnKitchen) {
			System.out.println("kitchen clickecd");
			getSelectedRoom(btnKitchen, (JPanel) kitchen);

		}

		else if ((Room) e.getSource() == btnCloset) {
			System.out.println("Closet clickecd");
			getSelectedRoom(btnCloset, (JPanel) closet);

		}
	}

	/**
	 * If room is clicked odd number of times, then it is
	 * selected, and roomPicture boder will be highlighted.
	 * @param room
	 * @param roomPicture
	 */
	public void getSelectedRoom(Room room, JPanel roomPicture) {
		int count = room.getCount();

		if (count % 2 == 1) {
			
			Data.selectRoom(room);
			roomPicture.setBorder(thickBorder);

		} else {
			Data.deSelectRoom(room);
			roomPicture.setBorder(BorderFactory.createEmptyBorder());
		}
	}

	/**
	 * Add button to dining place.
	 * Add JPanel with picture to dining Place
	 */
	public void setupDiningPlace() {

		// add button for dining room
		btnDiningPlace = new Room("room2", "Dining Room", new Dimension(
				Data.diningroomWidth, Data.diningroomHeight), 55, 275);
		btnDiningPlace.setSize(Data.diningroomWidth, Data.diningroomHeight);
		add(btnDiningPlace);
		btnDiningPlace.setLocation(55, 275);
		btnDiningPlace.addActionListener(this);
		buttonArray.add(btnDiningPlace);

		// add picture
		diningPlace = new DiningPlace();
		add(diningPlace);
		diningPlace.setLocation(55, 285);
	}

	/**
	 * Add button to Living Room.
	 * Add JPanel with picture to Living Room.
	 */
	public void setupLivingRoom() {
		// add button for living room
		btnLivingRoom = new Room("room1", "Living Room", new Dimension(
				Data.livingroomWidth, Data.livingroomHeight), 270, 270);
		btnLivingRoom.setSize(Data.livingroomWidth, Data.livingroomHeight);
		add(btnLivingRoom);
		btnLivingRoom.setLocation(270, 270);
		btnLivingRoom.addActionListener(this);
		buttonArray.add(btnLivingRoom);

		// add picture for living room
		livingroom = new Livingroom();
		add(livingroom);
		livingroom.setLocation(270, 280);
	}

	/**
	 * Add button to Master Bedroom.
	 * Add JPanel with picture to Master Bedroom.
	 */
	public void setupMasterBedroom() {

		// add button for Bedroom
		btnbedRoomOne = new Room("room3", "Bedroom", new Dimension(
				Data.bedOneWidth, Data.bedOneHeight), 273, 50);
		btnbedRoomOne.setSize(Data.bedOneWidth, Data.bedOneHeight);
		add(btnbedRoomOne);
		btnbedRoomOne.setLocation(273, 50);
		btnbedRoomOne.addActionListener(this);
		buttonArray.add(btnbedRoomOne);

		// add picture
		masterBedroom = new MasterBedroom();
		add(masterBedroom);
		masterBedroom.setLocation(273, 60);
	}

	/**
	 * Add button to second Bedroom.
	 * Add JPanel with picture to second Bedroom.
	 */
	public void setupBedroom() {

		// add button for Bedroom
		btnbedRoomTwo = new Room("room4", "Bedroom", new Dimension(
				Data.bedTwoWidth, Data.bedTwoHeight), 53, 50);
		btnbedRoomTwo.setSize(Data.bedTwoWidth, Data.bedTwoHeight);
		add(btnbedRoomTwo);
		btnbedRoomTwo.setLocation(53, 50);
		btnbedRoomTwo.addActionListener(this);
		buttonArray.add(btnbedRoomTwo);

		// add picture
		bedroom = new Bedroom();
		add(bedroom);
		bedroom.setLocation(53, 60);
		bedroom.add(new JLabel("hi"));
	}

	/**
	 * Add button to Bathroom.
	 * Add JPanel with picture to Bathroom.
	 */
	public void setupBathOne() {

		// add button for Bathroom
		btnBathOne = new Room("room5", "Bathroom", new Dimension(
				Data.bathOneWidth, Data.bathOneHeight), 453, 50);
		btnBathOne.setLocation(453, 50);
		btnBathOne.setSize(Data.bathOneWidth, Data.bathOneHeight);
		add(btnBathOne);
		btnBathOne.addActionListener(this);
		buttonArray.add(btnBathOne);

		// add picture
		bathOne = new BathOne();
		add(bathOne);
		bathOne.setLocation(453, 60);
	}


	/**
	 * Add button to Bathroom.
	 * Add JPanel with picture to Bathroom.
	 */
	public void setupBathTwo() {

		// add button for bathroom
		btnBathTwo = new Room("room6", "Bathroom", new Dimension(
				Data.bathTwoWidth, Data.bathTwoHeight), 183, 50);
		btnBathTwo.setLocation(183, 50);
		btnBathTwo.setSize(Data.bathTwoWidth, Data.bathTwoHeight);
		add(btnBathTwo);
		btnBathTwo.addActionListener(this);
		buttonArray.add(btnBathTwo);

		// add picture
		bathTwo = new BathTwo();
		add(bathTwo);
		bathTwo.setLocation(183, 60);
	}


	/**
	 * Add button to Kitchen.
	 * Add JPanel with picture to Kitchen.
	 */
	public void setupKitchen() {

		// add button for bathroom
		btnKitchen = new Room("room7", "Kitchen", new Dimension(
				Data.kitchenWidth, Data.kitchenHeight), 53, 415);
		btnKitchen.setLocation(53, 415);
		btnKitchen.setSize(Data.kitchenWidth, Data.kitchenHeight);
		add(btnKitchen);
		btnKitchen.addActionListener(this);
		buttonArray.add(btnKitchen);

		// add picture
		kitchen = new Kitchen();
		add(kitchen);
		kitchen.setLocation(53, 425);
	}

	/**
	 * Add button to Closet.
	 * Add JPanel with picture to Closet.
	 */
	public void setupCloset() {

		btnCloset = new Room("room8", "Closet", new Dimension(Data.closetWidth,
				Data.closetHeight), 453, 200);
		btnCloset.setLocation(453, 200);
		btnCloset.setSize(Data.closetWidth, Data.closetHeight);
		add(btnCloset);

		btnCloset.addActionListener(this);
		buttonArray.add(btnCloset);

		closet = new Closet();
		add(closet);
		closet.setLocation(453, 210);

	}
	
	/**
	 * Returns the room array.
	 * @return the  ArrayList<JButton> 
	 */
	public ArrayList<JButton> getButtonArray() {
		return buttonArray;
	}
	
	/**
	 * make all the rooms not clickable
	 */
	public void disableAllButton(){
		btnLivingRoom.setEnabled(false);
		btnbedRoomOne.setEnabled(false);
		btnbedRoomTwo.setEnabled(false);
		btnDiningPlace.setEnabled(false);
		btnKitchen.setEnabled(false);
		btnBathOne.setEnabled(false);
		btnBathTwo.setEnabled(false);
		btnCloset.setEnabled(false);
	}
	
	/**
	 * Select all the rooms
	 */
	public void selectAllRooms() {
		getAllSelected(btnLivingRoom, (JPanel) livingroom);
		getAllSelected(btnDiningPlace, (JPanel) diningPlace);
		getAllSelected(btnbedRoomOne, (JPanel) masterBedroom);
		getAllSelected(btnbedRoomTwo, (JPanel) bedroom);
		getAllSelected(btnBathOne, (JPanel) bathOne);
		getAllSelected(btnBathTwo, (JPanel) bathTwo);
		getAllSelected(btnKitchen, (JPanel) kitchen);
		getAllSelected(btnCloset, (JPanel) closet);

	}

	/**
	 * To select all the rooms. the count should be odd. And 
	 * room border should appear.
	 */
	public void getAllSelected(Room room, JPanel roomPicture) {

		room.setCount(1);
		//room.setSensor(true);
		//room.setFireAlerm(true);
		if (Data.getSelectedRoom().contains(room) == false) {
			Data.selectRoom(room);
			roomPicture.setBorder(thickBorder);
		}
	}
	
	/**
	 * de-Select all the rooms
	 */
	public void deselectAllRooms() {
		getAlldeSelected(btnLivingRoom, (JPanel) livingroom);
		getAlldeSelected(btnDiningPlace, (JPanel) diningPlace);
		getAlldeSelected(btnbedRoomOne, (JPanel) masterBedroom);
		getAlldeSelected(btnbedRoomTwo, (JPanel) bedroom);
		getAlldeSelected(btnBathOne, (JPanel) bathOne);
		getAlldeSelected(btnBathTwo, (JPanel) bathTwo);
		getAlldeSelected(btnKitchen, (JPanel) kitchen);
		getAlldeSelected(btnCloset, (JPanel) closet);
		Data.clearSelectRoom();

	}

	/**
	 * To de-select all the rooms. the count should be even. And 
	 * room border should disappear.
	 */
	public void getAlldeSelected(Room room, JPanel roomPicture) {
		room.setCount(0);
		room.setSensor(false);
		room.setFireAlerm(false);
		roomPicture.setBorder(BorderFactory.createEmptyBorder());
		
	}
}
