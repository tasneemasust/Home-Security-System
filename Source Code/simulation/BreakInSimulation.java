package simulation;

import java.awt.Dimension;

import java.awt.Point;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dataObjects.Constants;

/**
 * This class generates the break in animation.
 * @author Rahmi
 *
 */
public class BreakInSimulation extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel thiefPanel;
	private JLabel thiefLabel;
	private Point position;

	private boolean running = true;
	private PlaySound breakInSound;

	public BreakInSimulation() {

	}

	public BreakInSimulation(int roomNumber) {

		// create a new Panel to show thief movement
		setUpthiefPanel();
		// add new Panel to show fire to layered pane;
		add(thiefPanel);
		addThiefLabel();
		setInitialThiefPosition(roomNumber);

		startBreakIn(roomNumber);
		
		breakInSound = new PlaySound(Constants.BREAKINSOUNDURL);
		breakInSound.play();
	}

	/**
	 * this method identifies in which room the thief currently 
	 * broke in and start the animation accordingly
	 * @param num
	 */
	public void startBreakIn(int num) {
		if(num == 1)
			thiefPositionLivingroom.start();
		else if(num == 2)
			thiefPositionMasterBedroom.start();
		else 
			thiefPositionBedroom.start();
	}

	/**
	 * Stops thread by changing the running value.
	 */
	public void stopBreakIn() {
		running = false;
	}

	/**
	 * Place a transparent panel all over the place , to 
	 * generate animation.
	 */
	public void setUpthiefPanel() {

		thiefPanel = new JPanel();
		thiefPanel.setLayout(null);
		thiefPanel.setPreferredSize(new Dimension(600, 600));
		thiefPanel.setBounds(0, 0, 600, 600);
		thiefPanel.setOpaque(false);
	}

	/**
	 * Set up a JLabel where the thief image will appear.
	 */
	public void addThiefLabel() {

		thiefLabel = new JLabel();
		thiefLabel.setPreferredSize(new Dimension(50, 50));
		
		thiefPanel.add(thiefLabel);
	}

	/**
	 * set initial thief position according to room area_id
	 * @param num
	 */
	public void setInitialThiefPosition(int num) {

		if (num == 1){
			position = new Point(500, 500);
			thiefLabel.setBounds(500, 510, 50, 50);

			java.net.URL imgUrl2 = FireSimulation.class.getResource("images/thief1.gif");
			Icon icon = new ImageIcon(imgUrl2);
			thiefLabel.setIcon(icon);
		}
			
		else if (num == 2){
			position = new Point(410, 50);
			thiefLabel.setBounds(410, 40, 50, 50);

			java.net.URL imgUrl2 = FireSimulation.class.getResource("images/thief1.gif");
			Icon icon = new ImageIcon(imgUrl2);
			thiefLabel.setIcon(icon);
		}
			
		else{
			position = new Point(50, 200);
			thiefLabel.setBounds(40, 200, 50, 50);

			java.net.URL imgUrl2 = FireSimulation.class.getResource("images/thief2.gif");
			Icon icon = new ImageIcon(imgUrl2);
			thiefLabel.setIcon(icon);
		}
			
	}

	/**
	 * this thread generates the thief movement in the living room.
	 * By changing the position in every 500 milliseconds.
	 */
	Thread thiefPositionLivingroom = new Thread() {
		int state = 0, count = 0;

		public void run() {
			while (running == true && count < 10) {

				for (int i = 0; i < 22; i++) {

					if (running == false)
						break;

					if (state == 0) {
						position.y -= 10;
					}

					if (state == 1) {
						position.x -= 10;
					}

					if (state == 2) {
						position.y += 10;
					}

					if (state == 3) {
						position.x += 10;
					}
					
					System.out.println(running + "    " + position.x + ",  " + position.y);
					thiefLabel.setLocation(position.x, position.y);
					try {
						sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				state = (state + 1) % 4;
				count++;
				int temp = (state % 2) + 1;
				java.net.URL imgUrl2 = FireSimulation.class.getResource("images/thief" + temp + ".gif");
				System.out.println(state + ",  images/thief" + state + ".gif");
				ImageIcon icon = new ImageIcon(imgUrl2);

				thiefLabel.setIcon(icon);

			}
		}
	};

	/**
	 * this thread generates the thief movement in the Master bedroom.
	 * By changing the position in every 500 milliseconds.
	 */
	Thread thiefPositionMasterBedroom = new Thread() {
		int state = 0, count = 0;

		public void run() {
			while (running == true && count < 20) {

				for (int i = 0; i < 16; i++) {

					if (running == false)
						break;

					if (state == 0) {
						position.y += 10;
					}

					if (state == 1) {
						position.y -= 10;
					}

					thiefLabel.setLocation(position.x, position.y);
					try {
						sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				state = (state + 1) % 2;
				count++;
				java.net.URL imgUrl2 = FireSimulation.class.getResource("images/thief1.gif");
				ImageIcon icon = new ImageIcon(imgUrl2);

				thiefLabel.setIcon(icon);

			}
		}
	};

	/**
	 * this thread generates the thief movement in the 2nd Bedroom.
	 * By changing the position in every 500 milliseconds.
	 */
	Thread thiefPositionBedroom = new Thread() {
		int state = 0, count = 0;

		public void run() {
			while (running == true && count < 20) {

				for (int i = 0; i < 8; i++) {

					if (running == false)
						break;

					if (state == 0) {
						position.x += 10;
					}

					if (state == 1) {
						position.y -= 10;
					}

					if (state == 2) {
						position.x -= 10;
					}

					if (state == 3) {
						position.y += 10;
					}
					System.out.println(running + "    " + position.x + ",  " + position.y);
					thiefLabel.setLocation(position.x, position.y);
					try {
						sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				state = (state + 1) % 4;
				count++;
				int temp = 1;
				if(state % 2 == 0)temp = 2;
				
				java.net.URL imgUrl2 = FireSimulation.class.getResource("images/thief" + temp + ".gif");
				System.out.println(state + ",  images/thief" + state + ".gif");
				ImageIcon icon = new ImageIcon(imgUrl2);

				thiefLabel.setIcon(icon);

			}
		}
	};
}
