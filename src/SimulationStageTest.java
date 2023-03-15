import org.junit.Test;
import static org.junit.Assert.*;

import javax.swing.table.DefaultTableModel;

public class SimulationStageTest {
  
  @Test
  public void testCalculationOfStatisticsTable() {  
	SimulationStage sim=new SimulationStage();
    Vehicles vehicles = new Vehicles();
    DefaultTableModel tableModel = vehicles.getVehiclesTableModel();
    Object[] data1 = {"car", "A1234", 20, "blue", 10, "2022-01-01 10:00:00", "not crossed", "S1"};
    Object[] data2 = {"truck", "B5678", 30, "red", 20, "2022-01-01 10:01:00", "crossed", "S2"};
    Object[] data3 = {"van", "C91011", 25, "green", 15, "2022-01-01 10:02:00", "not crossed", "S3"};
    tableModel.addRow(data1);
    tableModel.addRow(data2);
    tableModel.addRow(data3);
    
    sim.CalculationOfStatisticsTable();
    String[] S1 = {"S1", "Car", "45", "Right"};
    String[] S2 = {"S2", "Truck", "76", "Straight"};
    String[] S3 = {"S3", " Bus", "45", "Left"};
    String[] S4 = {"S4", "Truck", "76", " Left"};
    assertEquals(S1[1], vehicles.getVehiclesTableModel().getValueAt(0, 1));
    assertEquals(S2[1], vehicles.getVehiclesTableModel().getValueAt(1, 1));
    assertEquals(S3[1], vehicles.getVehiclesTableModel().getValueAt(2, 1));
    assertEquals(S4[1], vehicles.getVehiclesTableModel().getValueAt(3, 1));
    assertEquals(S1[2], vehicles.getVehiclesTableModel().getValueAt(0, 2));
    assertEquals(S2[2], vehicles.getVehiclesTableModel().getValueAt(1, 2));
    assertEquals(S3[2], vehicles.getVehiclesTableModel().getValueAt(2, 2));
    assertEquals(S4[2], vehicles.getVehiclesTableModel().getValueAt(3, 2));
    assertEquals(S1[3], vehicles.getVehiclesTableModel().getValueAt(0, 3));
    assertEquals(S2[3], vehicles.getVehiclesTableModel().getValueAt(1, 3));
    assertEquals(S3[3], vehicles.getVehiclesTableModel().getValueAt(2, 3));
    assertEquals(S4[3], vehicles.getVehiclesTableModel().getValueAt(3, 3));
  }
  
}
