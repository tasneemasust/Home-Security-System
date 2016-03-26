package simulation;

import java.awt.Color;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

/**
 * This class is used for showing the date format hint in date TextField
 * 
 * @author Rahmi
 *
 */

public class HintTextField extends JTextField implements FocusListener {

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String hint;
	  private boolean showingHint;

	  /**
	   * Constructor create a HintTextField with a hint string
	   * @param hint
	   */
	  public HintTextField( String hint) {
	    super(hint);
	    this.hint = hint;
	    this.showingHint = true;
	    this.setForeground(Color.GRAY);
	    super.addFocusListener(this);
	  }

	  /**
	   * When clicked on the text field the hint text will be gone
	   */
	  @Override
	  public void focusGained(FocusEvent e) {
	    if(this.getText().isEmpty()) {
	      super.setText("");
	      showingHint = false;
	    }
	  }
	  
	  /**
	   * The hint text will appear before the use writes something
	   */
	  @Override
	  public void focusLost(FocusEvent e) {
	    if(this.getText().isEmpty()) {
	      super.setText(hint);
	      showingHint = true;
	    }
	  }

	  /**
	   * return the text of the field
	   */
	  @Override
	  public String getText() {
	    return showingHint ? "" : super.getText();
	  }
	}
