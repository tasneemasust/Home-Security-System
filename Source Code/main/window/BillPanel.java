package main.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import DBConnection.SensorInstallation;
import dataObjects.Constants;

/**
 * This class generates and print Bill of the
 * current month.
 *
 */
public class BillPanel extends JPanel {

	private JPanel custInfo;
	private HashMap<String, String> user_info;
	private JPanel billPage;
	private JPanel fireAlarmBill;
	private JPanel sensorBill;
	private JLabel totalDue;
	private double totalSensorBill = 0.0;
	private double totalfireBill = 0.0;
	private JButton refreshButton;
	private JPanel topPanel;
	private JButton printButton;

	/**
	 * creates a BillPanel to view and print the bill
	 */

	public BillPanel() {
		setLayout(new BorderLayout());

		createBackPrint();

		createCustomerInfoTable();
		setUpFireAlarmBillPanel();
		setUpSensorBillPanel();

		ArrayList<String> sensortypes = SensorInstallation.getSensorTypes();
		if (sensortypes.contains(Constants.SENSOR)) {
			createSensorBill();
		}

		if (sensortypes.contains(Constants.FIRE_ALARM)) {
			createFireAlarmBill();
		}

		createTotalDue();

	}

	/**
	 * refreshes the current bill
	 */
	private void refreshAndShow() {

		billPage.remove(fireAlarmBill);
		billPage.remove(sensorBill);
		billPage.remove(totalDue);
		totalSensorBill = 0.0;
		totalfireBill = 0.0;

		setUpFireAlarmBillPanel();
		setUpSensorBillPanel();

		ArrayList<String> sensortypes = SensorInstallation.getSensorTypes();
		if (sensortypes.contains(Constants.SENSOR)) {
			createSensorBill();
		}

		if (sensortypes.contains(Constants.FIRE_ALARM)) {
			createFireAlarmBill();
		}

		createTotalDue();

		billPage.revalidate();
		billPage.repaint();
	}

	/**
	 * creates a refresh button and a print button
	 */

	private void createBackPrint() {
		topPanel = new JPanel(new GridBagLayout());
		topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;

		refreshButton = new JButton("Refresh");
		refreshButton.setFont(new Font(null, Font.BOLD, 16));
		refreshButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// parentCardLayout.show(parentCardPanel, Constants.CONFIG);
				refreshAndShow();
			}
		});

		topPanel.add(refreshButton, gbc);

		gbc.gridx++;
		printButton = new JButton("Print Bill");
		printButton.setFont(new Font(null, Font.BOLD, 16));
		printButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				PrinterJob job = PrinterJob.getPrinterJob();
				job.setPrintable(new PrintUIWindow());
				boolean ok = job.printDialog();
				if (ok) {
					try {
						job.print();
					} catch (PrinterException ex) {
						/* The job did not successfully complete */
					}
				}

			}
		});

		topPanel.add(printButton, gbc);

		add(topPanel, BorderLayout.NORTH);
	}

	/**
	 * creates the total bill amount
	 */
	private void createTotalDue() {
		double total = totalfireBill + totalSensorBill;
		totalDue = new JLabel("Total amount due: $" + total);
		totalDue.setFont(new Font(null, Font.BOLD, 16));
		billPage.add(totalDue);
	}

	/**
	 * creates the part of the bill panel that shows customer information
	 */
	private void createCustomerInfoTable() {
		billPage = new JPanel();

		billPage.setLayout(new BoxLayout(billPage, BoxLayout.Y_AXIS));
		// billPage.setPreferredSize(new Dimension(700, 500));
		billPage.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(20, 50, 60, 50),
				BorderFactory.createLineBorder(Color.BLACK)));
		billPage.setBackground(Color.WHITE);

		custInfo = new JPanel();
		custInfo.setLayout(new BoxLayout(custInfo, BoxLayout.Y_AXIS));
		custInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		custInfo.setBackground(Color.WHITE);

		JPanel namePanel = new JPanel();
		namePanel.setBackground(Color.WHITE);
		namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
		namePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JLabel nameLabel = new JLabel("SoSafe Home Security Inc.");
		nameLabel.setFont(new Font("Lucida Calligraphy", Font.BOLD
				| Font.ITALIC, 22));
		nameLabel.setForeground(Color.BLUE);

		namePanel.add(nameLabel);

		JLabel addressLabel = new JLabel("Santa Clara, CA");
		namePanel.add(addressLabel);

		billPage.add(namePanel);

		Calendar cal = Calendar.getInstance();
		String billMonth = new SimpleDateFormat("MMMM YYYY").format(cal
				.getTime());

		billPage.add(new JLabel("    "));
		JLabel billMonthLabel = new JLabel("Bill for month: " + billMonth);
		billMonthLabel.setFont(new Font(null, Font.BOLD, 16));
		billPage.add(billMonthLabel);

		JLabel systemIdLabel = new JLabel("ID: " + Constants.SYSTEMID);
		custInfo.add(new JLabel("   "));

		custInfo.add(systemIdLabel);

		user_info = SensorInstallation.getCustomerDetails();

		JLabel name = new JLabel(
				user_info.containsKey("name") ? user_info.get("name") : "");
		JLabel address = new JLabel(
				user_info.containsKey("address") ? user_info.get("address")
						: "");
		JLabel email = new JLabel(
				user_info.containsKey("email") ? user_info.get("email") : "");
		JLabel phone = new JLabel(user_info.containsKey("phone") ? "Phone: "
				+ user_info.get("phone") : "");

		name.setFont(new Font(null, Font.BOLD, 14));
		custInfo.add(name);
		custInfo.add(address);
		custInfo.add(email);
		custInfo.add(phone);

		billPage.add(custInfo);
		add(billPage, BorderLayout.CENTER);
	}

	/**
	 * creates a panel to store the bill
	 */
	private void setUpFireAlarmBillPanel() {
		fireAlarmBill = new JPanel();
		fireAlarmBill.setLayout(new BoxLayout(fireAlarmBill, BoxLayout.Y_AXIS));
		fireAlarmBill
				.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		fireAlarmBill.setBackground(Color.WHITE);
		billPage.add(fireAlarmBill);
	}

	/**
	 * creates the fire sensor charge and adds it to bill
	 */
	private void createFireAlarmBill() {
		String hoursUsed = SensorInstallation.createBill(Constants.FIRE_ALARM);

		if (hoursUsed != null && !hoursUsed.equals("")) {

			JLabel fab = new JLabel("Fire Alarm Bill: ");
			fab.setFont(new Font(null, Font.BOLD, 14));
			fireAlarmBill.add(fab);
			fireAlarmBill.add(new JLabel("    "));
			fireAlarmBill.add(new JLabel("Charge per sensor: $"
					+ Constants.FIRE_ALARM_RATE));
			fireAlarmBill.add(new JLabel("    "));

			String[] columnNames = { "Hours Used", "No. of sensors", "Total" };

			String noofSensors = SensorInstallation
					.getNoOfSensor(Constants.FIRE_ALARM);
			totalfireBill = noofSensors != "" ? (Double.parseDouble(hoursUsed) * Integer
					.parseInt(noofSensors)) * Constants.FIRE_ALARM_RATE
					: 0;

			totalfireBill = Math.round(totalfireBill * 100) / 100.0;
			Object[][] data = { { hoursUsed, noofSensors, totalfireBill } };
			JTable fireBillTable = new JTable(data, columnNames);
			fireBillTable.setEnabled(false);

			fireBillTable
					.setBorder(BorderFactory.createLineBorder(Color.black));

			JTableHeader t = fireBillTable.getTableHeader();
			t.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			t.setFont(new Font(null, Font.BOLD, 14));

			fireAlarmBill.add(t);
			fireAlarmBill.add(fireBillTable);

		}
	}

	/**
	 * sets up the motion sensor part of the bill
	 */
	private void setUpSensorBillPanel() {

		sensorBill = new JPanel();
		sensorBill.setLayout(new BoxLayout(sensorBill, BoxLayout.Y_AXIS));
		sensorBill.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		sensorBill.setBackground(Color.WHITE);
		billPage.add(sensorBill);

	}

	/**
	 * creates the motion sensor charge and adds it to bill
	 */
	private void createSensorBill() {

		// System.out.println("in createSensorBill");
		String hoursUsed = SensorInstallation.createBill(Constants.SENSOR);

		if (hoursUsed != null && !hoursUsed.equals("")) {

			JLabel sb = new JLabel("Security Sensor Bill");
			sb.setFont(new Font(null, Font.BOLD, 14));
			sensorBill.add(sb);
			sensorBill.add(new JLabel("    "));
			sensorBill.add(new JLabel("Charge per sensor: $"
					+ Constants.SENSOR_RATE));
			sensorBill.add(new JLabel("   "));

			String[] columnNames = { "Hours Used", "No. of sensors", "Total" };

			String noofSensors = SensorInstallation
					.getNoOfSensor(Constants.SENSOR);
			totalSensorBill = noofSensors != "" ? (Double
					.parseDouble(hoursUsed) * Integer.parseInt(noofSensors))
					* Constants.SENSOR_RATE : 0;
			totalSensorBill = Math.round(totalSensorBill * 100) / 100.0;
			Object[][] data = { { hoursUsed, noofSensors, totalSensorBill } };

			JTable sensorBillTable = new JTable(data, columnNames);
			sensorBillTable.setEnabled(false);
			sensorBillTable.setBorder(BorderFactory
					.createLineBorder(Color.black));

			JTableHeader t = sensorBillTable.getTableHeader();
			t.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			t.setFont(new Font(null, Font.BOLD, 14));

			sensorBill.add(t);
			sensorBill.add(sensorBillTable);

		}
	}

	/**
	 * creates a print window
	 * 
	 * @author soumyadevate
	 *
	 */
	class PrintUIWindow implements Printable {

		public int print(Graphics g, PageFormat pf, int page)
				throws PrinterException {

			if (page > 0) {
				/* We have only one page, and 'page' is zero-based */
				return NO_SUCH_PAGE;
			}

			/*
			 * User (0,0) is typically outside the imageable area, so we must
			 * translate by the X and Y values in the PageFormat to avoid
			 * clipping
			 */
			Graphics2D g2d = (Graphics2D) g;

			pf.setOrientation(PageFormat.LANDSCAPE);
			g2d.translate(pf.getImageableX(), pf.getImageableY());

			/* Now print the window and its visible contents */
			billPage.printAll(g);

			/* tell the caller that this page is part of the printed document */
			return PAGE_EXISTS;
		}

	}

}
