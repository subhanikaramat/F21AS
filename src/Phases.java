import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class Phases extends JPanel {
    public DefaultTableModel tableModel;
    public JTable PhaseTable;

    public Phases() {

        // Define the column names and data types for the table
        Vector<String> columnNames = new Vector<String>();
        columnNames.add("Phase");
        columnNames.add("Duration");

        // Create the table model and set the column names
        tableModel = new DefaultTableModel(columnNames, 0);

        // Read the data from the CSV file and add it to the table model
        String csvFile = "src/intersection.csv";
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                tableModel.addRow(data);
            }
        } catch (IOException e) {
            System.out.println("Phases file not found");
        }

        // Create the table and set its model
        PhaseTable = new JTable(tableModel);
        PhaseTable.getTableHeader().setBackground(Color.gray);
        PhaseTable.setEnabled(false);
        PhaseTable.setRowHeight(20);


        // Set the preferred width of each column
        TableColumnModel columnModel = PhaseTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(30);
        columnModel.getColumn(1).setPreferredWidth(30);

        // Set the cell alignment to center
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.LEFT);
        columnModel.getColumn(0).setCellRenderer(centerRenderer);
        columnModel.getColumn(1).setCellRenderer(centerRenderer);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(PhaseTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        scrollPane.setPreferredSize(new Dimension(100, 100));

        // Add the scroll pane to the panel
        setLayout(new BorderLayout());
        add(new JLabel("Phases", JLabel.CENTER), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

    }
}
