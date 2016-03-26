package house.map;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * This is a utility class to fit a image in a label.
 * @author Rahmi
 *
 */
public class Util {

	/**
	 * Make the height and width of the image proportioned to the label. Renders
	 * the selected image. Then add it to the label.
	 * 
	 * @param name
	 */

	protected static void setPicturetoLabel(JLabel label, String name) {

		ImageIcon icon = createImageIcon(name);
		ImageIcon imgThisImg = icon;

		if (icon != null) {

			Image img = imgThisImg.getImage();
			Image newimg;
			int h, w;

			// make the height and width proportioned to the label
			// calculate new h and w
			if (imgThisImg.getIconHeight() > imgThisImg.getIconWidth()) {
				h = (int) label.getPreferredSize().getHeight();
				w = imgThisImg.getIconWidth() * h / imgThisImg.getIconHeight();
				if (w > label.getPreferredSize().getWidth())
					w = (int) label.getPreferredSize().getWidth();

			} else {
				w = (int) label.getPreferredSize().getWidth();
				h = imgThisImg.getIconHeight() * w / imgThisImg.getIconWidth();

				if (h > label.getPreferredSize().getHeight())
					h = (int) label.getPreferredSize().getHeight();
			}

			newimg = img.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH);
			ImageIcon icon1 = new ImageIcon(newimg);

			label.setIcon(icon1);
		} else {
			label.setText("Image not found");
		}
	}

	/**
	 * Returns an ImageIcon, or null if the path was invalid.
	 */

	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = MainStructure.class.getResource(path);
		// java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

}
