import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.util.Vector;

public class Statistics extends JPanel {
    public DefaultTableModel tableModel;
    public JTable StatisticsTable;

    public DefaultTableModel getStatisticsTableModel()
    {
    	return tableModel;
    }
    public JTable getStatisticsTable()
    {
    	return StatisticsTable;
    }
    
    public Statistics() {
        setLayout(new BorderLayout());

        // Define the column names and data types for the table
        Vector<String> columnNames = new Vector<String>();
        columnNames.add("Segment");
        columnNames.add("Waiting time");
        columnNames.add("Waiting length");
        columnNames.add("Cross Time");

        tableModel = new DefaultTableModel(columnNames, 0);
        
        StatisticsTable = new JTable(tableModel); 
        StatisticsTable.getTableHeader().setBackground(Color.gray);
        StatisticsTable.setEnabled(false);
        
//        Vector<String> data = new Vector<String>();
//        String[] segments = {"S1", "S2", "S3", "S4"};
        
        
//        
//        data.add("S1");
//        data.add("10 s");
//        data.add("20 m");
//        data.add("30 s");
//        
//        
//        tableModel.addRow(data);
//        tableModel.addRow(data);
//        tableModel.addRow(data);
//        tableModel.addRow(data);        
//
//        

        JScrollPane scrollPane = new JScrollPane(StatisticsTable);

        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        scrollPane.setPreferredSize(new Dimension(200, 200));

        
        // Add the scroll pane to the panel
        setLayout(new BorderLayout());
        add(new JLabel("Statistics", JLabel.CENTER), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
}
