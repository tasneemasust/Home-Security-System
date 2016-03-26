package house.map;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class Setup and draws the living room of house map.
 * @author Rahmi
 *
 */
public class Livingroom extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;

	/**
	 * Constructor, sets up layout and labels for images.
	 */
	public Livingroom() {

		// set layout and boundary
		this.setLayout(null);
		this.setBackground(Color.white);
		this.setSize(Data.livingroomWidth, Data.livingroomHeight);

		// set up image in the picture panel
		setSofaPanel();
		add(label1);

		setChairPanel();
		add(label2);

		setTVPanel();
		add(label3);

	}

	/**
	 * Set up label for sofa. Then add sofa picture to the label. Add label to
	 * the living room panel.
	 * 
	 */
	public void setSofaPanel() {

		// create and set up label
		label1 = new JLabel();

		label1.setPreferredSize(new Dimension(200, 145));
		label1.setBackground(Color.white);

		label1.setLocation(60, 80);
		label1.setSize(200, 145);

		// add image to the label
		Util.setPicturetoLabel(label1, "images/sofa1.jpg");

	}

	/**
	 * Set up label for chair. Then add chair picture to the label. Add label to
	 * the living room panel.
	 * 
	 */
	public void setChairPanel() {

		// create and set up label
		label2 = new JLabel();

		label2.setPreferredSize(new Dimension(80, 36));
		label2.setBackground(Color.white);

		label2.setLocation(80, 40);
		label2.setSize(80, 36);

		// add image to the label
		Util.setPicturetoLabel(label2, "images/ch1.jpg");

	}

	/**
	 * Set up label for tv. Then add tv picture to the label. Add label to the
	 * living room panel.
	 * 
	 */
	public void setTVPanel() {

		// create and set up label
		label3 = new JLabel();

		label3.setPreferredSize(new Dimension(200, 80));
		label3.setBackground(Color.white);

		label3.setLocation(200, 100);
		label3.setSize(200, 80);

		// add image to the label
		Util.setPicturetoLabel(label3, "images/tv.png");

	}

}
