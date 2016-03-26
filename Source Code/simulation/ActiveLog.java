package simulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dataObjects.Constants;

/**
 * This class show the current status of the system in a JTextArea.
 * @author Rahmi
 *
 */
public class ActiveLog extends JPanel {

	
	private static final long serialVersionUID = 1L;
	private JPanel logPanel;

	public static JTextArea description;

	private int msgcount;
	String[] str;

	/**
	 * Constructor
	 */
	public ActiveLog() {
		setupLog();
	}

	/**
	 * Constructor.
	 * Receives a string array of messages and size of the array 
	 * @param str string array
	 * @param n array size
	 */
	public ActiveLog(String[] str, int n) {
		this.str = str;
		this.msgcount = n;

		setupLog();
	}

	/**
	 * Setter function to set values of string array of messages and size of the
	 * array
	 * 
	 * @param str
	 * @param n
	 */
	public void showActiveLog(String[] str, int n) {
		this.str = str;
		this.msgcount = n;
	}

	/**
	 * Creates and add panel, JTextArea for description
	 * and set styles
	 * 
	 */
	public void setupLog() {

		// creating panel for Description
		logPanel = new JPanel();

		// setting layout
		logPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		// creating JTextArea for description
		description = new JTextArea();
		description.setFont(new Font("Calibri", Font.PLAIN, 16));
		description.setBackground(Color.BLACK);
		description.setForeground(Color.WHITE);

		JScrollPane scroll = new JScrollPane(description);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setPreferredSize(new Dimension(480, 230));

		// disables editing
		description.setEditable(false);

		// enable line wrap to wrap text around
		description.setLineWrap(true);

		// words will not be cut off when wrapped around
		description.setWrapStyleWord(true);

		// adding TextArea
		logPanel.add(scroll);

		// adding panel to main pane
		add(logPanel);

	}

	/**
	 * This is a synchronized function to write current activity
	 * in the log window.
	 * @param string
	 */
	synchronized public void writeOnActiveLog(String string) {
		description.append(string + "\n");
		System.out.println(string);
		description.setCaretPosition(description.getDocument().getLength());

	}

	/**
	 * A thread that will show messages in the log window one by one.
	 */
	Thread showLog = new Thread() {

		public void run() {
			if (Constants.getsimulationId() == Constants.breakInString) {
				Constants.BREAKINSOUND.play();
			}
			try {

				sleep(Messages.sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			for (int i = 0; i < msgcount; i++) {

				writeOnActiveLog(str[i]);

				System.out.println(str[i]);

				if (i == 1 && Constants.getsimulationId() == Constants.fireString) {
					Constants.FIREALARMSOUND.play();
				}

				if (i == 0 && Constants.getsimulationId() == Constants.breakInString) {
					Constants.BREAKINSOUND.play();
				}

			}
		}
	};

}
