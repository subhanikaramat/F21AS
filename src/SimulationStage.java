import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class SimulationStage extends JFrame {

    JButton addButton, cancelButton, exitButton;
    JLabel Result;
    
    public int totalCrossedVehicles = 0; // Counter for total number of crossed vehicles
	public int totalWaitingTime = 0,one,two,three,four; // Counters for total waiting time of vehicles in each phase
	public int totalEsmission = 0; // Counter for total emission produced by vehicles
    Phases phasesTable= new Phases(); // Instance of Phases table
    Statistics statisticsTable = new Statistics(); // Instance of Statistics table
    addVehicles addVehiclesTable= new addVehicles(); // Instance of addVehicles table
    Vehicles vehicles = new Vehicles(); // Instance of Vehicles table
	   
    

    public SimulationStage() 
    {
        // Create instances of each table and add them to the GUI
    	vehicles = new Vehicles(); // Re-initialize Vehicles table instance
    	vehicles.setBackground(new Color(240, 240, 240));
    	BorderLayout borderLayout = (BorderLayout) vehicles.getLayout(); // Set layout for Vehicles table
    	JTable vehiclesTable = vehicles.getTable(); // Get the table from Vehicles instance
    	
        phasesTable= new Phases(); // Re-initialize Phases table instance
        phasesTable.setPreferredSize(new Dimension(80, 114));
        statisticsTable = new Statistics(); // Re-initialize Statistics table instance
        addVehiclesTable= new addVehicles(); // Re-initialize addVehicles table instance
        
        addButton = new JButton("Add"); // Create a "Add" button
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the vehicle data from the addVehicles panel
                Vector<Object> vehicleData = addVehiclesTable.getVehicleData();
                	
                		
                    DefaultTableModel tableModel = vehicles.getVehiclesTableModel(); // Get the table model for Vehicles table
                    String[] data = { // Create an array of vehicle data
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
                            checkValues = true; // set checkValues flag to true if any field is empty
                            break;
                        }
                    }
                    if (checkValues) {
                        JOptionPane.showMessageDialog(SimulationStage.this, "Please insert the values of missing fields of Add Vehicles table.", "Error", JOptionPane.ERROR_MESSAGE);

                    } else {
                        tableModel.addRow(data); // Add the vehicle data to the Vehicles table
                        vehicles.records++; // Increment the records counter for Vehicles table
                        
                        // Remove previous data from Statistics table
                        DefaultTableModel tableModel2 = statisticsTable.getStatisticsTableModel();
                        int rowCount = tableModel2.getRowCount();
                        for (int i = rowCount - 1; i >= 0; i--) {
                            tableModel2.removeRow(i);
                        }
                    	CalculationOfStatisticsTable(); // Calculate statistics and add them to the Statistics table 

                    	

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
//     		
        	}
        });
        
        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
                try {
                    // Get the table model of the Vehicles table
                    DefaultTableModel tableModel = vehicles.getVehiclesTableModel();
                    // Create a FileWriter object for the CSV file
                    FileWriter csvWriter = new FileWriter("src/vehicles.csv");

                    // Write the data to the CSV file
                    for (int i = 0; i < tableModel.getRowCount(); i++) {
                        for (int j = 0; j < tableModel.getColumnCount(); j++) {
                            csvWriter.append(tableModel.getValueAt(i, j).toString());
                            if (j != tableModel.getColumnCount() - 1) {
                                csvWriter.append(",");
                            }
                        }
                        csvWriter.append("\n");
                    }
                    // Close the FileWriter object
                    csvWriter.flush();
                    csvWriter.close();
                    // Exit the program
                } catch (IOException ex) {
                	System.out.println("You have an error in the file reading");
                    ex.printStackTrace();
                }
            
                    
            
         // calculate statistics

            DefaultTableModel table = vehicles.getVehiclesTableModel();
        	
            
            int avgWaitingTime = totalWaitingTime / totalCrossedVehicles;
            

            // write report to file
            try {
                FileWriter writer = new FileWriter("report.txt");
                writer.write("Total vehicles crossed per phase: \n");
                writer.write("S1: " + one + "\n");
                writer.write("S2: " + two + "\n");
                writer.write("S3: " + three + "\n");
                writer.write("S4: " + four + "\n\n");
                writer.write("Average waiting time to cross: " + avgWaitingTime + " seconds\n\n");
                writer.write("Total  emission TIme : " + totalEsmission  + " kg\n");
                writer.close();
                System.exit(0);
            } catch (IOException ex) {
                System.out.println("Error writing report to file");
                ex.printStackTrace();
            }
        }
        });



     // Create a JLabel with initial text "0" and set its preferred size
        Result = new JLabel("0");
        Result.setFont(new Font("Tahoma", Font.PLAIN, 50));

        // Set background color for buttons
        addButton.setBackground(Color.gray);
        cancelButton.setBackground(Color.gray);
        exitButton.setBackground(Color.gray);

        // Set preferred sizes for buttons
        addButton.setPreferredSize(new Dimension(120, 20));
        cancelButton.setPreferredSize(new Dimension(120, 70));
        exitButton.setPreferredSize(new Dimension(120, 70));

        // Set alignment for buttons
        addButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.RIGHT_ALIGNMENT);

        // Create a horizontal box to contain the buttons
        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(Box.createHorizontalGlue());
        buttonBox.add(addButton);
        addButton.setBounds(100,100,110,10);
        buttonBox.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonBox.add(cancelButton);
        buttonBox.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonBox.add(exitButton);
        buttonBox.add(Box.createHorizontalGlue());

        // Set title of the JFrame and its close operation
        setTitle("Intersection Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 300);
        getContentPane().setLayout(new BorderLayout());

        // Create a JPanel with a grid layout of 1 row and 3 columns to hold the tables
        GridLayout gl_tablesPanel = new GridLayout(1, 3);
        JPanel tablesPanel = new JPanel(gl_tablesPanel);
        tablesPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        vehicles.setBorder(BorderFactory.createEmptyBorder(0,0,0,20));
        phasesTable.setBorder(BorderFactory.createEmptyBorder(0,0,0,20));
        tablesPanel.add(vehicles);
        tablesPanel.add(phasesTable);
        tablesPanel.add(statisticsTable);

        // Add the tables panel and the addVehicles panel to the JFrame
        getContentPane().add(tablesPanel, BorderLayout.NORTH);
        getContentPane().add(addVehiclesTable, BorderLayout.CENTER);

        // Create a JPanel for the right side of the JFrame and add the Result JLabel to it
        JPanel rightPanel = new JPanel();
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        rightPanel.add(Result);

        // Add the right panel to the JFrame
        getContentPane().add(rightPanel, BorderLayout.EAST);

        // Create a JPanel for the bottom of the JFrame and add the button box to it
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        bottomPanel.setPreferredSize(new Dimension(getWidth(), 200));
        bottomPanel.add(buttonBox);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        // Pack the JFrame and center it on the screen
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Call the CalculationOfStatisticsTable
        CalculationOfStatisticsTable();

    }
    
    // This method calculates and populates the Statistics table.
    // whenever the new record is inserted into the Vehicles table the changes are reflected to the statistics table too.
    // beacuse this method is called in addButton EventHandler.
   
    public void CalculationOfStatisticsTable()
    {
    	
    	// Define string arrays for each segment
    	String S1[] = new String[4];
    	String S2[] = new String[4];
    	String S3[] = new String[4];
    	String S4[] = new String[4];

    	// Assign the name of each segment to the first index of their respective string array
    	S1[0] = "S1";
    	S2[0] = "S2";
    	S3[0] = "S3";
    	S4[0] = "S4";

    	// Get the table model of the vehicles table
    	DefaultTableModel table = vehicles.getVehiclesTableModel();

    	// Initialize variables for storing waiting time for each segment
    	int S1W = 0, S2W = 0, S3W = 0, S4W = 0;

    	// Iterate through each row in the table to calculate waiting time for each segment
    	for (int i = 0; i < table.getRowCount(); i++) {
    		
    	// Get the segment value for the current row
    	String segment = table.getValueAt(i, 7).toString();

    	// Get the crossing status value for the current row
    	String crossingStatus = table.getValueAt(i, 6).toString().trim().toLowerCase();

    	// If the crossing status is "not crossed", add the wait time to the corresponding segment variable
    	if (segment.equals("S1") && crossingStatus.equals("not crossed")) {
    	    S1W += Integer.parseInt(table.getValueAt(i, 4).toString());
    	} else if (segment.equals("S2") && crossingStatus.equals("not crossed")) {
    	    S2W += Integer.parseInt(table.getValueAt(i, 4).toString());
    	} else if (segment.equals("S3") && crossingStatus.equals("not crossed")) {
    	    S3W += Integer.parseInt(table.getValueAt(i, 4).toString());
    	} else if (segment.equals("S4") && crossingStatus.equals("not crossed")) {
    	    S4W += Integer.parseInt(table.getValueAt(i, 4).toString());
    	}
    	}

    	// Store the waiting time for each segment in their respective string arrays
    	S1[1] = Integer.toString(S1W)+" s";
    	S2[1] = Integer.toString(S2W)+" s";
    	S3[1] = Integer.toString(S3W)+" s";
    	S4[1] = Integer.toString(S4W)+" s";

    	// Reset the waiting time variables to 0
    	S1W=0;
    	S2W=0;
    	S3W=0;
    	S4W=0;    	
    	
    
    	for (int i = 0; i < table.getRowCount(); i++) {
    	    String segment = table.getValueAt(i, 7).toString().trim();    
    	    String crossingStatus = table.getValueAt(i, 6).toString().trim();

    	    if (segment.equals("S1") && crossingStatus.equals("Crossed")){
    	        totalEsmission +=Integer.parseInt(table.getValueAt(i, 5).toString());
    	        S1W += Integer.parseInt(table.getValueAt(i, 2).toString());
    	        totalCrossedVehicles++;
    	    } else if (segment.equals("S2") && crossingStatus.equals("Crossed")) {
    	        totalEsmission +=Integer.parseInt(table.getValueAt(i, 5).toString());
    	        S2W += Integer.parseInt(table.getValueAt(i, 2).toString());
    	        totalCrossedVehicles++;
    	    } else if (segment.equals("S3") && crossingStatus.equals("Crossed")) {
    	        totalEsmission +=Integer.parseInt(table.getValueAt(i, 5).toString());
    	        S3W += Integer.parseInt(table.getValueAt(i, 2).toString());
    	        totalCrossedVehicles++;
    	    } else if (segment.equals("S4") && crossingStatus.equals("Crossed")) {
    	        totalEsmission +=Integer.parseInt(table.getValueAt(i, 5).toString());
    	        S4W += Integer.parseInt(table.getValueAt(i, 2).toString());
    	        totalCrossedVehicles++;
    	    }
    	}

    	// This loop goes through each row of the table and calculates the total emission,
    	// waiting time for each segment and the total number of crossed vehicles for each segment.

    	try {
    	    totalWaitingTime = S1W+S2W+S3W+S4W;
    	    one = S1W;
    	    two= S2W;
    	    three = S3W;
    	    four=S4W;
    	    S1[2] = Integer.toString(S1W/totalCrossedVehicles)+" m";
    	    S2[2] = Integer.toString(S2W/totalCrossedVehicles)+" m";
    	    S3[2] = Integer.toString(S3W/totalCrossedVehicles)+" m";
    	    S4[2] = Integer.toString(S4W/totalCrossedVehicles)+" m";
    	} catch(Exception e) {
    	    System.out.println("Please recheck your calculations in the code");
    	}

    	// The waiting time for each segment is calculated by dividing the total waiting time by the
    	// total number of vehicles that crossed that segment. The results are stored in the S1, S2, S3, and S4 arrays.

    	S1W=0;
    	S2W=0;
    	S3W=0;
    	S4W=0;
	
    	
    	for (int i = 0; i < table.getRowCount(); i++) {
    		   // Get the segment and crossing status from the table
    		   String segment = table.getValueAt(i, 7).toString().trim();
    		   String crossingStatus = table.getValueAt(i, 6).toString().trim();

    		   // Check if the segment is S1, S2, S3 or S4 and if it has not been crossed yet
    		   if (segment.equals("S1") && crossingStatus.equals("Not Crossed")){
    		       // If it matches, add the weight to the corresponding S1, S2, S3 or S4 counter
    		       S1W += Integer.parseInt(table.getValueAt(i, 5).toString());
    		       
    		   } else if (segment.equals("S2") && crossingStatus.equals("Not Crossed")) {
    		       S2W += Integer.parseInt(table.getValueAt(i, 5).toString());
    		       
    		   } else if (segment.equals("S3") && crossingStatus.equals("Not Crossed")) {
    		       S3W += Integer.parseInt(table.getValueAt(i, 5).toString());
    		    
    		   } else if (segment.equals("S4") && crossingStatus.equals("Not Crossed")) {
    		       S4W += Integer.parseInt(table.getValueAt(i, 5).toString());
    		    
    		   }
    		}

    		// Update the statistics table with the new values
    		S1[3] = Integer.toString(S1W)+" s";
    		S2[3] = Integer.toString(S2W)+" s";
    		S3[3] = Integer.toString(S3W)+" s";
    		S4[3] = Integer.toString(S4W)+" s";

    		DefaultTableModel tableModel = statisticsTable.getStatisticsTableModel();
    		tableModel.addRow(S1);
    		tableModel.addRow(S2);
    		tableModel.addRow(S3);
    		tableModel.addRow(S4);
    		

    		// Set the text of the Result label to show the total emission
    		Result.setText("CO2:  "+Integer.toString(totalEsmission)+" kg");

    }
   
   // This is the main method which creates an Object of SimulationStage class.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SimulationStage());
    }
}
