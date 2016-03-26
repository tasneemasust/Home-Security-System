package house.map;


import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * This class Setup and draws the Master bedroom of house map.
 * @author Rahmi
 *
 */
public class MasterBedroom extends JPanel {
	

	private static final long serialVersionUID = 1L;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	
	/**
	 * Constructor, sets up layout and labels for images.
	 */
	public MasterBedroom(){
		
		//set layout and boundary
		this.setLayout(null);
		this.setBackground(Color.WHITE);

		this.setSize(Data.bedOneWidth, Data.bedOneHeight);
    	
    	//set up image in the picture panel
		setBedPanel();
		add(label1);
		
		setSideTablePanel();
		add(label2);
		
		setSideTablePanel2();
		add(label3);
		
		setDresserPanel();
		add(label4);
    	
		setCarpetPanel();
		add(label5);
		
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
		
		label1.setLocation(40, 10);
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
		
		label2.setLocation(24, 15);
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
		
		label3.setLocation(128, 15);
		label3.setSize(20, 20);

		// add image to the label
		Util.setPicturetoLabel(label3, "images/bedTable.png") ;

	}
	
	/**
	 * Set up label for Dresser. Then add picture to the label.
	 * Add label to the bedroom panel.
	 
	 */
	public void setDresserPanel(){
		
		//create and set up label
		label4 = new JLabel();
		
		label4.setPreferredSize(new Dimension(80, 25));
		label4.setBackground(Color.white);
		
		label4.setLocation(50, 185);
		label4.setSize(80, 25);

		// add image to the label
		Util.setPicturetoLabel(label4, "images/table.png") ;

	}
	
	/**
	 * Set up label for Carpet. Then add picture to the label.
	 * Add label to the bedroom panel.
	 
	 */
	public void setCarpetPanel(){
		
		//create and set up label
		label5 = new JLabel();
		
		label5.setPreferredSize(new Dimension(80, 50));
		label5.setBackground(Color.white);
		
		label5.setLocation(40, 110);
		label5.setSize(80, 50);

		// add image to the label
		Util.setPicturetoLabel(label5, "images/carpet.jpg") ;

	}
	
}
