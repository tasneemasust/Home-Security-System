package house.map;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Setup and draws the kitchen of the house map.
 * @author Rahmi
 *
 */
public class Kitchen extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel label1;

	/**
	 * Constructor, sets up layout and labels for images.
	 */
	public Kitchen() {

		this.setLayout(null);
		this.setBackground(Color.WHITE);
		this.setSize(Data.kitchenWidth, Data.kitchenHeight);

		// set up image in the picture panel
		setKitchenPanel();
		add(label1);

	}

	/**
	 * Set up label for picture. Then add picture to the label. Add label to the
	 * kitchen panel.
	 * 
	 */
	public void setKitchenPanel() {

		// create and set up label
		label1 = new JLabel();

		label1.setPreferredSize(new Dimension(150, 130));
		label1.setBackground(Color.red);

		label1.setLocation(0, 0);
		label1.setSize(150, 130);

		// add image to the label
		Util.setPicturetoLabel(label1, "images/kitchen.png");
	}
}
