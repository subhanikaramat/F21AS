import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class SimulationStage extends JFrame {

    JButton addButton, cancelButton, exitButton;
    JLabel Result;

    Phases phasesTable= new Phases();
    Statistics statisticsTable = new Statistics();
    addVehicles addVehiclesTable= new addVehicles();
    Vehicles vehicles = new Vehicles();
	   
    

    public SimulationStage() {
    	CalculationOfStatisticsTable();
        // Create instances of each table and add them to the GUI
    	 vehicles = new Vehicles();
    	    JTable vehiclesTable = vehicles.getTable();
    	    
    	
         phasesTable= new Phases();
        statisticsTable = new Statistics();
        addVehiclesTable= new addVehicles();
        
        
        
        addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the vehicle data from the addVehicles panel
                Vector<Object> vehicleData = addVehiclesTable.getVehicleData();
                	
                		
                    DefaultTableModel tableModel = vehicles.getTableModel();
                    String[] data = {
                            vehicleData.get(0).toString().trim(),
                            vehicleData.get(1).toString().trim(),
                            vehicleData.get(2).toString().trim(),
                            vehicleData.get(3).toString().trim(),
                            vehicleData.get(4).toString().trim(),
                            vehicleData.get(5).toString().trim(),
                            vehicleData.get(6).toString().trim(),
                            vehicleData.get(7).toString().trim()
                    };
                    boolean checkValues = false;
                    for (int i = 0; i < data.length; i++) {
                        if (data[i].length() == 0) {
                            checkValues = true; // make it true to get correct functionality
                            break;
                        }
                    }
                    if (checkValues) {
                        JOptionPane.showMessageDialog(SimulationStage.this, "Please insert the values of missing fields of Add Vehicles table.", "Error", JOptionPane.ERROR_MESSAGE);

                    } else {
                        tableModel.addRow(data);
                        vehicles.records++;
                        System.out.println(vehicles.records);
                        

//                        // Clear the input fields in the addVehicles panel
                        addVehiclesTable.tableModel.setValueAt("", 0, 0); // Vehicle
                        addVehiclesTable.tableModel.setValueAt("", 0, 1); // Type
                        addVehiclesTable.tableModel.setValueAt("", 0, 2); // Crossing Time
                        addVehiclesTable.tableModel.setValueAt("", 0, 3); // Direction
                        addVehiclesTable.tableModel.setValueAt("", 0, 4); // Length
                        addVehiclesTable.tableModel.setValueAt("", 0, 5); // Emission
                        addVehiclesTable.tableModel.setValueAt("", 0, 6); // Status
                        addVehiclesTable.tableModel.setValueAt("", 0, 7); // Segment
                    }


            }
        });

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
                addVehiclesTable.tableModel.setValueAt("", 0, 0); // Vehicle
                addVehiclesTable.tableModel.setValueAt("", 0, 1); // Type
                addVehiclesTable.tableModel.setValueAt("", 0, 2); // Crossing Time
                addVehiclesTable.tableModel.setValueAt("", 0, 3); // Direction
                addVehiclesTable.tableModel.setValueAt("", 0, 4); // Length
                addVehiclesTable.tableModel.setValueAt("", 0, 5); // Emission
                addVehiclesTable.tableModel.setValueAt("", 0, 6); // Status
                addVehiclesTable.tableModel.setValueAt("", 0, 7); // Segment
     		
        	}
        });
        
        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Create a FileWriter object with the vehicles.csv file path
                    FileWriter fw = new FileWriter("src/vehicles.csv");
                    
                    // Create a BufferedWriter object to write data to the file
                    BufferedWriter bw = new BufferedWriter(fw);
                    
                    // Get the number of rows and columns in the vehicles table
                    int rowCount = vehicles.records;
                    int colCount = 8;
                    
                    // Loop through each row and column in the vehicles table
                    for (int i = 0; i < rowCount; i++) {
                        for (int j = 0; j < colCount; j++) {
                            // Write each cell value to the file
                            bw.write(vehiclesTable.getValueAt(i, j).toString());
                            
                            // Add a comma after each value (except the last value in the row)
                            if (j < colCount - 1) {
                                bw.write(",");
                            }
                        }
                        
                        // Add a new line after each row
                        bw.newLine();
                    }
                    
                    // Close the BufferedWriter and FileWriter objects
                    bw.close();
                    fw.close();
                    
                } 
                catch (IOException ex) {
                	System.out.println("Error While writting data to the vehicles.csv file");
//                    ex.printStackTrace();
                }
                
                
                
                
                
                // Exit the program
                System.exit(0);
            }
        });



        Result = new JLabel("Result Comes Here");
        Result.setPreferredSize(new Dimension(150, 150));

        addButton.setBackground(Color.gray);
        cancelButton.setBackground(Color.gray);
        exitButton.setBackground(Color.gray);

        addButton.setPreferredSize(new Dimension(120, 20));
        cancelButton.setPreferredSize(new Dimension(120, 70));
        exitButton.setPreferredSize(new Dimension(120, 70));

        addButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.RIGHT_ALIGNMENT);


        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(Box.createHorizontalGlue());
        buttonBox.add(addButton);
        addButton.setBounds(100,100,110,10);
        buttonBox.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonBox.add(cancelButton);
        buttonBox.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonBox.add(exitButton);
        buttonBox.add(Box.createHorizontalGlue());

        setTitle("Intersection Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 300);
        getContentPane().setLayout(new BorderLayout());



        JPanel tablesPanel = new JPanel(new GridLayout(1, 3));
        tablesPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        tablesPanel.add(vehicles);
        tablesPanel.add(phasesTable);
        tablesPanel.add(statisticsTable);

        
        getContentPane().add(tablesPanel, BorderLayout.NORTH);
        getContentPane().add(addVehiclesTable, BorderLayout.CENTER);

        // Add other components to the right and bottom sides of the frame
        JPanel rightPanel = new JPanel();
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        rightPanel.add(Result);
        rightPanel.setPreferredSize(new Dimension(400, getHeight()));

        getContentPane().add(rightPanel, BorderLayout.EAST);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        bottomPanel.setPreferredSize(new Dimension(getWidth(), 200));
        bottomPanel.add(buttonBox);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public void CalculationOfStatisticsTable()
    {
        int S1[] = {0,0,0};
        int S2[] = {0,0,0};
        int S3[] = {0,0,0};
        int S4[] = {0,0,0};

    	DefaultTableModel table = vehicles.getVehiclesTableModel();
    	
//    	String[] data = new String[table.getRowCount()];
    	
    	int S1W=0,S2W=0,S3W=0,S4W=0;
    	
    	for(int i=0; i<table.getRowCount(); i++)		
    	{
    		System.out.println(table.getValueAt(i, 6).toString().toLowerCase());
    			if(table.getValueAt(i, 7).toString().trim()=="S1" && table.getValueAt(i, 6).toString().trim().toLowerCase()=="Not Crossed".toLowerCase())
    			{
    				S1W+= (int)table.getValueAt(i, 4);
    				System.out.println(S1W);
    			}
    			else if(table.getValueAt(i, 7).toString()=="S2" && table.getValueAt(i, 6).toString().trim().toLowerCase()=="Not Crossed".toLowerCase())
    			{
    				S2W+= (int)table.getValueAt(i, 4);
    			}
    			else if(table.getValueAt(i, 7).toString()=="S3" && table.getValueAt(i, 6).toString().trim().toLowerCase()=="Not Crossed".toLowerCase())
    			{
    				S3W+= (int)table.getValueAt(i, 4);
    			}
    			else if(table.getValueAt(i, 7).toString()=="S4" && table.getValueAt(i, 6).toString().trim().toLowerCase()=="Not Crossed".toLowerCase())
    			{
    				S4W+= (int)table.getValueAt(i, 4);
    			}
    	}
    	System.out.println(S1W);
    	System.out.println(S2W);
    	System.out.println(S3W);
    	System.out.println(S4W);
    	

    }
    
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SimulationStage());
    }
}
