package house.map;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class Setup and draws the closet of house map.
 * @author Rahmi
 *
 */
public class Closet extends JPanel{
	

	private static final long serialVersionUID = 1L;
	private JLabel label1;
	
	/**
	 * Constructor, sets up layout and labels for images.
	 */
	public Closet(){
			
		this.setLayout(null);
		this.setBackground(Color.white);
		this.setSize(Data.closetWidth, Data.closetHeight);
    	
    	//set up image in the picture panel
		setClosetPanel();
		add(label1);	
	}
	
	
	/**
	 * Set up label for picture. Then add picture to the label.
	 * Add label to the closet panel.
	 * 
	 */
	public void setClosetPanel(){
		
		//create and set up label
		label1 = new JLabel();
		
		label1.setPreferredSize(new Dimension(Data.closetWidth, Data.closetHeight));
		//label1.setBackground(Color.red);
		
		label1.setLocation(39, 0);
		label1.setSize(Data.closetWidth, Data.closetHeight);

		// add image to the label
		Util.setPicturetoLabel(label1, "images/closet.png") ;

	}

}
