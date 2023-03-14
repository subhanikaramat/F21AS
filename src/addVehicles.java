import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class addVehicles extends JPanel {
    public DefaultTableModel tableModel;
    public JTable addVehiclesTable;
    
    public addVehicles() {
        setLayout(new BorderLayout());

        // Define the column names and data types for the table
        Vector<String> columnNames = new Vector<String>();
        columnNames.add("Vehicle");
        columnNames.add("Type");
        columnNames.add("Crossing Time");
        columnNames.add("Direction");
        columnNames.add("Length");
        columnNames.add("Emission");
        columnNames.add("Status");
        columnNames.add("Segment");

        // Define the options for the drop-down lists
        String[] vehicleTypes = {"Car", "Truck", "Bus"};
        String[] directions = {"Right", "Left", "Straight"};
        String[] statuses = {"Crossed", "Not Crossed"};
        String[] segments = {"S1", "S2", "S3", "S4"};

        // Create the table model and set the column names
        tableModel = new DefaultTableModel(columnNames, 0);

        // Add a dummy record to the table
        Vector<Object> dummyRecord = new Vector<Object>();
        dummyRecord.add("");
        dummyRecord.add("");
        dummyRecord.add("");
        dummyRecord.add("");
        dummyRecord.add("");
        dummyRecord.add("");
        dummyRecord.add("");
        dummyRecord.add("");
        tableModel.addRow(dummyRecord);

        // Create the table and set its model
        addVehiclesTable = new JTable(tableModel);
        addVehiclesTable.getTableHeader().setBackground(Color.gray);
        addVehiclesTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 10));
     // Set the preferred width of the first two columns
        addVehiclesTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        addVehiclesTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        
        // Set the drop-down lists as cell editors for the relevant columns
        TableColumn typeColumn = addVehiclesTable.getColumnModel().getColumn(1);
        JComboBox<String> typeComboBox = new JComboBox<String>(vehicleTypes);
        typeColumn.setCellEditor(new DefaultCellEditor(typeComboBox));

        TableColumn directionColumn = addVehiclesTable.getColumnModel().getColumn(3);
        JComboBox<String> directionComboBox = new JComboBox<String>(directions);
        directionColumn.setCellEditor(new DefaultCellEditor(directionComboBox));

        TableColumn statusColumn = addVehiclesTable.getColumnModel().getColumn(6);
        JComboBox<String> statusComboBox = new JComboBox<String>(statuses);
        statusColumn.setCellEditor(new DefaultCellEditor(statusComboBox));

        TableColumn segmentColumn = addVehiclesTable.getColumnModel().getColumn(7);
        JComboBox<String> segmentComboBox = new JComboBox<String>(segments);
        segmentColumn.setCellEditor(new DefaultCellEditor(segmentComboBox));

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(addVehiclesTable);

        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        scrollPane.setPreferredSize(new Dimension(100, 100));

        // Add the scroll pane to the panel
        setLayout(new BorderLayout());
        add(new JLabel("Add Vehicles", JLabel.CENTER), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

    }
    
    public Vector<Object> getVehicleData() {
        Vector<Object> vehicleData = new Vector<Object>();
        vehicleData.add(addVehiclesTable.getValueAt(0, 0)); // Vehicle
        vehicleData.add(addVehiclesTable.getValueAt(0, 1)); // Type
        vehicleData.add(addVehiclesTable.getValueAt(0, 2)); // Crossing Time
        vehicleData.add(addVehiclesTable.getValueAt(0, 3)); // Direction
        vehicleData.add(addVehiclesTable.getValueAt(0, 4)); // Length
        vehicleData.add(addVehiclesTable.getValueAt(0, 5)); // Emission
        vehicleData.add(addVehiclesTable.getValueAt(0, 6)); // Status
        vehicleData.add(addVehiclesTable.getValueAt(0, 7)); // Segment
        return vehicleData;
    }
    
    
}
