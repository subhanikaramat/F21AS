import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.util.Vector;

public class Statistics extends JPanel {
    public DefaultTableModel tableModel; // instance variable for the table model
    public JTable StatisticsTable; // instance variable for the table

    public DefaultTableModel getStatisticsTableModel() {
        return tableModel; // method to return the table model
    }

    public JTable getStatisticsTable() {
        return StatisticsTable; // method to return the table
    }
    
    public Statistics() {
        setLayout(new BorderLayout());

        // Define the column names and data types for the table
        Vector<String> columnNames = new Vector<String>();
        columnNames.add("Segment"); // add column for segment
        columnNames.add("Waiting time"); // add column for waiting time
        columnNames.add("Waiting length"); // add column for waiting length
        columnNames.add("Cross Time"); // add column for cross time

        // Create the table model with the column names and 0 rows
        tableModel = new DefaultTableModel(columnNames, 0);
        
        // Create the table with the table model
        StatisticsTable = new JTable(tableModel); 
        
        // Set the background color of the table header to gray
        StatisticsTable.getTableHeader().setBackground(Color.gray);
        StatisticsTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 10));
        // Disable editing on the table
        StatisticsTable.setEnabled(false);
          
        // Create a scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(StatisticsTable);

        // Add empty border to the scroll pane
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        
        // Set the preferred size of the scroll pane
        scrollPane.setPreferredSize(new Dimension(200, 200));

        // Add the scroll pane to the panel
        setLayout(new BorderLayout());
        add(new JLabel("Statistics", JLabel.CENTER), BorderLayout.NORTH); // Add a label "Statistics" to the top center of the panel
        add(scrollPane, BorderLayout.CENTER); // Add the scroll pane to the center of the panel
    }
}
