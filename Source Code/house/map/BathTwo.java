package house.map;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * This class draws the Bathroom Two of the house map
 * @author Rahmi
 *
 */
public class BathTwo extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel label1;
	private JLabel label2;
	private JLabel label4;
	
	/**
	 * Constructor, set layout and labels for images to set.
	 */
	public BathTwo(){
		
		this.setLayout(null);
		this.setBackground(Color.white);
		this.setSize(Data.bathTwoWidth, Data.bathTwoHeight);
    	
    	//set up image in the picture panel
		setBathPanel();
		add(label1);
		
		setSinkPanel();
		add(label2);
		
		setdotPanel();
		add(label4);
		
	}

	/**
	 * Add tub image.
	 * Set up label for picture. Then add picture to the label.
	 * Add label to the bathroom panel.
	 * 
	 */
	public void setBathPanel(){
		
		//create and set up label
		label1 = new JLabel();
		
		label1.setPreferredSize(new Dimension(85, 50));
		label1.setBackground(Color.red);
		
		label1.setLocation(0, 0);
		label1.setSize(85, 50);

		// add image to the label
		Util.setPicturetoLabel(label1, "images/tub.jpg") ;

	}
	
	/**
	 * Add basin image.
	 * Set up label for picture. Then add picture to the label.
	 * Add label to the bathroom panel.
	 * 
	 */
	public void setSinkPanel(){
		
		//create and set up label
		label2 = new JLabel();
		
		label2.setPreferredSize(new Dimension(30, 50));
		label2.setLocation(0, 80);
		label2.setSize(30, 50);
		

		// add image to the label
		Util.setPicturetoLabel(label2, "images/basin2.jpg") ;


	}
	
	/**
	 * Add toilet image.
	 * Set up label for picture. Then add picture to the label.
	 * Add label to the bathroom panel.
	 * 
	 */
	public void setdotPanel(){
		
		//create and set up label
		label4 = new JLabel();
		
		label4.setPreferredSize(new Dimension(50, 40));		
		label4.setLocation(40, 60);
		label4.setSize(50, 40);

		// add image to the label
		Util.setPicturetoLabel(label4, "images/comode.jpg") ;

	}
}
