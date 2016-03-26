package house.map;


import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * Setup and draws the dining place of the house map.
 * @author Rahmi
 *
 */
public class DiningPlace extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JLabel label1;
	
	/**
	 * Constructor, sets up layout and labels for images.
	 */
	public DiningPlace(){	
		
		this.setLayout(null);
		this.setBackground(Color.white);
		this.setSize(Data.diningroomWidth, Data.diningroomHeight);
    	
    	//set up image in the picture panel
		setDiningPanel();
		add(label1);
    	
		
	}
	
	
	/**
	 * Set up label for picture. Then add picture to the label.
	 * Add label to the dining place panel.
	 * 
	 */
	public void setDiningPanel(){
		
		//create and set up label
		label1 = new JLabel();
		
		label1.setPreferredSize(new Dimension(100, 100));
		label1.setBackground(Color.white);
		
		label1.setLocation(10, 10);
		label1.setSize(100, 100);

		// add image to the label
		Util.setPicturetoLabel(label1, "images/dining_table.png") ;

	}

	
}
