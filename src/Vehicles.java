import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Vehicles extends JPanel {
    public DefaultTableModel vehiclesTableModel;
    public JTable vehiclesTable;
    public static int records = 0;
    
    public JTable getTable()
    {
    	return vehiclesTable;
    }
    
    public DefaultTableModel getVehiclesTableModel()
    {
    	return vehiclesTableModel;
    }
    
    
    
    public Vehicles() {
        setLayout(new BorderLayout());

        // Define the column names and data types for the table
        Vector<String> columnNames = new Vector<String>();
        columnNames.add("Vehicles");
        columnNames.add("Type");
        columnNames.add("Crossing Time");
        columnNames.add("Direction");
        columnNames.add("Length");
        columnNames.add("Emission");
        columnNames.add("Status");
        columnNames.add("Segment");

        // Create the table model and set the column names
        vehiclesTableModel = new DefaultTableModel(columnNames, 0);
        
        // Read the data from the CSV file and add it to the table model
        String csvFile = "src/vehicles.csv";
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                vehiclesTableModel.addRow(data);
                records++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        vehiclesTable = new JTable(vehiclesTableModel);
        vehiclesTable.getTableHeader().setBackground(Color.gray);
        vehiclesTable.setName("VEHICLES");
        vehiclesTable.setEnabled(false);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(vehiclesTable);

        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        scrollPane.setPreferredSize(new Dimension(100, 100));

        
        // Add the scroll pane to the panel
        setLayout(new BorderLayout());
        add(new JLabel("Vehicles", JLabel.CENTER), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        
        
    }
	public DefaultTableModel getTableModel() {
		// TODO Auto-generated method stub
		return vehiclesTableModel;
	}
}
