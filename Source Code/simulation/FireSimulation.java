package simulation;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 * Shows fire animation when fire simulation 
 * is selected.
 * @author Rahmi
 *
 */
public class FireSimulation extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLayeredPane layeredPaneFire;

	ArrayList<Point> firePoint = new ArrayList<Point>();

	private int level;
	private volatile boolean running = true;

	/**
	 * constructor, receives parameter roomNumber,
	 * which room to catch fire.
	 * @param roomNumber
	 */
	public FireSimulation(int roomNumber) {

		level = 1;

		// create a new Panel to show fire
		setUpFirePanel();

		// add new Panel to show fire to layered pane;
		add(layeredPaneFire);

		generateFirePoint(roomNumber);

		startFire();
	}

	/**
	 * starts fire thread.
	 */
	public void startFire() {
		fireThread.start();
	}

	/**
	 * stop animation by setting running false.
	 */
	public void stopFire() {
		running = false;
	}

	/**
	 * A transparent panel all over the house map
	 * to show fire animation. It is a layeredPane
	 * so that fire can overlap each other.
	 */
	public void setUpFirePanel() {

		layeredPaneFire = new JLayeredPane();
		layeredPaneFire.setPreferredSize(new Dimension(600, 600));
		layeredPaneFire.setBounds(0, 0, 600, 600);
		layeredPaneFire.setOpaque(false);
	}

	/**
	 * Creates and add a new label for the fire to show.
	 * @param x
	 * @param y
	 */
	public void addFireLabel(int x, int y) {

		JLabel fireLabel = new JLabel();
		fireLabel.setPreferredSize(new Dimension(50, 60));
		fireLabel.setBounds(x, y, 50, 60);

		java.net.URL imgUrl2 = FireSimulation.class.getResource("images/Flame-1.gif");
		Icon icon = new ImageIcon(imgUrl2);
		fireLabel.setIcon(icon);

		layeredPaneFire.add(fireLabel, level);

	}

	/**
	 * Calculate the coordinates how fire should be spread.
	 * @param num
	 */
	public void generateFirePoint(int num) {

		int x = 0, y = 0;
		if (num == 1) {
			x = 310;
			y = 350; // start position of fire in room1
		} else if (num == 2) {
			x = 340;
			y = 120;// start position of fire in room3
		} else if (num == 0) {
			x = 80;
			y = 120;// start position of fire in room4
		}
		System.out.println(num);

		Point point = new Point(x, y);
		int jumpFire = 20;

		firePoint.add(point);

		for (int i = 1; i < 10; i += 2) {
			for (int j = 0; j < i; j++) {
				x -= jumpFire;
				firePoint.add(new Point(x, y));
			}
			for (int j = 0; j < i; j++) {
				y += jumpFire;
				firePoint.add(new Point(x, y));
			}
			for (int j = 0; j < i; j++) {
				x += jumpFire;
				firePoint.add(new Point(x, y));
			}
			for (int j = 0; j < i; j++) {
				y -= (2) * jumpFire;
				x += jumpFire;
				firePoint.add(new Point(x, y));
			}
		}
	}

	/**
	 * This thread shows the fire spreading animation.
	 * Every second it generates a new fire in a new
	 * coordinate.
	 */
	Thread fireThread = new Thread() {

		public void run() {
			int i = 0;
			while (running == true && i < 16) {
				addFireLabel(firePoint.get(i).x, firePoint.get(i).y);
				level++;

				try {

					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				i++;
			}
		}
	};

}
