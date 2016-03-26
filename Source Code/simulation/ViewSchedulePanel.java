package simulation;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import dataObjects.Schedule;
/**
 * Show upcoming schedule to user in JTable
 * @author Rahmi
 *
 */
public class ViewSchedulePanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Schedule> fireAlarmSchedule;
	ArrayList<Schedule> sensorSchedule;

	private JSplitPane splitPane;
	private JScrollPane fireAlarmScrollPane;
	private JScrollPane sensorScrollPane;
	private JTable fireAlarmScheduletable;
	private JTable sensorScheduletable;
	private DefaultTableModel tableModel1;
	private DefaultTableModel tableModel2;

	private JButton refreshButton;

	private String[] cloumn = { "Type", "Date", "Start_time", "End_time" };
	private ScheduleAndConfiguration sc;
	
	public ViewSchedulePanel(){
		
		this.setLayout(new FlowLayout(FlowLayout.RIGHT));

		getScheduleData();
		setUpTableView(cloumn);
		setScheduleDatatoTable(tableModel1, fireAlarmSchedule, "Fire Alarm");
		setScheduleDatatoTable(tableModel2, sensorSchedule, "Sensor");
		setUpRefreashButton();
		setUpSpitpane();

	}
	
	public static void setScheduleDatatoTable(DefaultTableModel table, ArrayList<Schedule> schedule, String str){
		String[] row = new String[4];
		for(int i = 0; i < schedule.size(); i++){
			row[0] = str;
			row[1] = schedule.get(i).getDate();
			row[2] = schedule.get(i).getFromTime();
			row[3] = schedule.get(i).getToTime();
			System.out.println(str+row);
			
			table.addRow(row);
		}
		
	}
	
	private void getScheduleData(){
		 sc = new ScheduleAndConfiguration();
		fireAlarmSchedule = sc.getFireAlarmScheduleList();
		sensorSchedule = sc.getSensorScheduleList();
	}
	
	
	private void setUpSpitpane(){
		
		fireAlarmScrollPane = new JScrollPane(fireAlarmScheduletable);
		sensorScrollPane = new JScrollPane(sensorScheduletable);
		
		 //Create a split pane with the two scroll panes in it.
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
        		fireAlarmScrollPane, sensorScrollPane);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(140);
        
        //Provide a preferred size for the split pane.
        splitPane.setPreferredSize(new Dimension(470, 260));
        add(splitPane);
	}
	
	private void setUpRefreashButton(){
		refreshButton = new JButton("Refresh");
		refreshButton.setBorderPainted(false);
		refreshButton.setOpaque(false);
		refreshButton.addActionListener(this);
		add(refreshButton);
		
	}
	
	
	private void setUpTableView( String[] col){
		tableModel1 = new DefaultTableModel(col, 0);
		tableModel2 = new DefaultTableModel(col, 0);

		fireAlarmScheduletable = new JTable(tableModel1){
    		//  Returning the Class of each column will allow different

    		/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public Class<? extends Object> getColumnClass(int column)
    		{
    			return getValueAt(0, column).getClass();
    		}

    		public Component prepareRenderer(
    			TableCellRenderer renderer, int row, int column)
    		{
    			Component c = super.prepareRenderer(renderer, row, column);
    			JComponent jc = (JComponent)c;

    			//  Color row based on a cell value
    			//  Alternate row color

    			if (!isRowSelected(row))
    				c.setBackground(row % 2 == 0 ? getBackground() : Color.LIGHT_GRAY);
    			else
    				jc.setBorder(new MatteBorder(1, 0, 1, 0, Color.ORANGE) );
    			//  Use bold font on selected row

    			return c;
    		}
    	};
    	
    	
    	sensorScheduletable = new JTable(tableModel2){
    		//  Returning the Class of each column will allow different

    		/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public Class<? extends Object> getColumnClass(int column)
    		{
    			return getValueAt(0, column).getClass();
    		}

    		public Component prepareRenderer(
    			TableCellRenderer renderer, int row, int column)
    		{
    			Component c = super.prepareRenderer(renderer, row, column);
    			JComponent jc = (JComponent)c;

    			//  Color row based on a cell value
    			//  Alternate row color

    			if (!isRowSelected(row))
    				c.setBackground(row % 2 == 0 ? getBackground() : Color.LIGHT_GRAY);
    			else
    				jc.setBorder(new MatteBorder(1, 0, 1, 0, Color.ORANGE) );
    			//  Use bold font on selected row

    			return c;
    		}
    	};
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		tableModel1.setRowCount(0);
		tableModel2.setRowCount(0);

		getScheduleData();
		setScheduleDatatoTable(tableModel1, fireAlarmSchedule, "Fire Alarm");
		setScheduleDatatoTable(tableModel2, sensorSchedule, "Sensor");
		
	}

}
