package house.map;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class draws the Bathroom one of the house map
 * @author Rahmi
 *
 */
public class BathOne extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	
	/**
	 * Constructor, set layout and labels for images to set.
	 */
	public BathOne(){
		
		this.setLayout(null);
		this.setBackground(Color.white);
		this.setSize(Data.bathOneWidth, Data.bathOneHeight);
    	
    	//set up image in the picture panel
		setBathPanel();
		add(label1);
		
		setSinkPanel();
		add(label2);
		add(label3);
		
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
		
		label1.setPreferredSize(new Dimension(95, 50));
		label1.setBackground(Color.red);
		
		label1.setLocation(0, 0);
		label1.setSize(95, 50);

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
		
		label2.setPreferredSize(new Dimension(40, 30));
		label2.setLocation(10, 110);
		label2.setSize(40, 30);
		
		label3 = new JLabel();
		
		label3.setPreferredSize(new Dimension(40, 30));
		label3.setLocation(50, 110);
		label3.setSize(40, 30);

		// add image to the label
		Util.setPicturetoLabel(label2, "images/basin.jpg") ;
		Util.setPicturetoLabel(label3, "images/basin.jpg") ;


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
		label4.setLocation(50, 60);
		label4.setSize(50, 40);

		// add image to the label
		Util.setPicturetoLabel(label4, "images/comode.jpg") ;

	}
}
