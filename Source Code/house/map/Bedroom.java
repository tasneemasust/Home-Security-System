package house.map;


import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class Setup and draws the 2nd bedroom of house map.
 * @author Rahmi
 *
 */
public class Bedroom extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	
	
	/**
	 * Constructor, sets up layout and labels for images.
	 */
	public Bedroom(){
			
		//set layout and boundary
		this.setLayout(null);
		this.setBackground(Color.white);
		this.setSize(Data.bedTwoWidth, Data.bedTwoHeight);
    	
    	//set up image in the picture panel
		setBedPanel();
		add(label1);
		
		setSideTablePanel();
		add(label2);
		
		setSideTablePanel2();
		add(label3);
		
		setReadingTablePanel();
		add(label4);
		
	}
	
	
	/**
	 * Set up label for bed. Then add bed picture to the label.
	 * Add label to the bedroom panel.
	 * 
	 */
	public void setBedPanel(){
		
		//create and set up label
		label1 = new JLabel();
		
		label1.setPreferredSize(new Dimension(120, 100));
		label1.setBackground(Color.white);
		
		label1.setLocation(18, 10);
		label1.setSize(120, 100);

		// add image to the label
		Util.setPicturetoLabel(label1, "images/bed.jpg") ;

	}

	/**
	 * Set up label for side Table. Then add table picture to the label.
	 * Add label to the bedroom panel.
	 
	 */
	public void setSideTablePanel(){
		
		//create and set up label
		label2 = new JLabel();
		
		label2.setPreferredSize(new Dimension(20, 20));
		label2.setBackground(Color.white);
		
		label2.setLocation(2, 15);
		label2.setSize(20, 20);

		// add image to the label
		Util.setPicturetoLabel(label2, "images/bedTable.png") ;

	}
	
	/**
	 * Set up label for side Table. Then add table picture to the label.
	 * Add label to the bedroom panel.
	 
	 */
	public void setSideTablePanel2(){
		
		//create and set up label
		label3 = new JLabel();
		
		label3.setPreferredSize(new Dimension(20, 20));
		label3.setBackground(Color.white);
		
		label3.setLocation(106, 15);
		label3.setSize(20, 20);

		// add image to the label
		Util.setPicturetoLabel(label3, "images/bedTable.png") ;

	}
	
	/**
	 * Set up label for Reading Table. Then add table picture to the label.
	 * Add label to the bedroom panel.
	 
	 */
	public void setReadingTablePanel(){
		
		//create and set up label
		label4 = new JLabel();
		
		label4.setPreferredSize(new Dimension(80, 25));
		label4.setBackground(Color.white);
		
		label4.setLocation(20, 185);
		label4.setSize(80, 20);

		// add image to the label
		Util.setPicturetoLabel(label4, "images/tble2.png") ;

	}
}
