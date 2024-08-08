/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import Base.BasePresenterInterface;
import Components.Label;
import Components.Panel;
import ViewModel.Sensor;
import ViewModel.UnitObject;
import com.irrigation.Messages.MessageData.Device;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import org.jfree.chart.ChartPanel;


/**
 * Implementation of GUI for showing the graph.
 * @author brune
 */
public class GraphGUI implements GraphGUIInterface{
    
    GraphPresenterInterface presenter;
    
    JDialog frame;
    JMenuBar menuBar;
    Panel graphPanel;
    JComboBox unitsComboBox = new JComboBox();
    JComboBox sensorsComboBox = new JComboBox();
    Label fromPeriodLabel = new Label("");
    Label toPeriodLabel = new Label("");
    Label initiatorLabel = new Label("");
    
    
    private JDialog getFrame(){
        frame = new JDialog();
        frame.setSize(new Dimension(600,600));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        return frame;
    }
    
    private Panel getMainPanel(){
        Panel mainPanel = new Panel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(getNorthPanel(),BorderLayout.NORTH);
        mainPanel.add(getGraphPanel(),BorderLayout.CENTER);
        return mainPanel;
    }
    
    private Panel getGraphPanel(){
        graphPanel = new Panel();
        graphPanel.setLayout(new BorderLayout());
        return graphPanel;
    }
    
    
    private JMenuBar getMenuBar(){
        menuBar = new JMenuBar();
        menuBar.add(changeRangeMenu());
        menuBar.add(calendarMenu());
        return menuBar;
    }
    
    
    private Panel getControlsPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        panel.add(getCompareLabelPanel());
        panel.add(getUnitsComboBoxPanel());
        panel.add(getSensorsComboBoxPanel());
        return panel;
           
    }
    
    private Panel getNorthPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
       // panel.add(getUpperNorthPanel());
        panel.add(getLowerNorthPanel());
        return panel;
    }
    
    private Panel getUpperNorthPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add(getInitiatorPanel(),BorderLayout.CENTER);
        return panel;
    }
    
    private Panel getLowerNorthPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add(getControlsPanel(),BorderLayout.EAST);
        panel.add(getPeriodPanel(),BorderLayout.WEST);
        return panel;
    }
    
    private Panel getCompareLabelPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add(new Label("Compare with"));
        return panel;
           
    }
    
    private Panel getInitiatorPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        panel.add(new Label("Graph for sensor: ",SwingConstants.CENTER));
        panel.add(initiatorLabel,SwingConstants.CENTER);
        return panel;
    }
    
    
    private Panel getUnitsComboBoxPanel(){
        Panel panel = new Panel();
         panel.setLayout(new BoxLayout(panel,BoxLayout.LINE_AXIS));
         panel.add(unitsComboBox);
            unitsComboBox.addItemListener((ItemEvent e) -> {
            if(e.getStateChange() == (ItemEvent.SELECTED)){
                presenter.onUnitSelected((UnitObject) e.getItem());
                System.out.println("selection changed");
            }

         });
         return panel;
    }
    
    private Panel getSensorsComboBoxPanel(){
        Panel panel = new Panel();
         panel.setLayout(new BoxLayout(panel,BoxLayout.LINE_AXIS));
         panel.add(sensorsComboBox);
            sensorsComboBox.addItemListener((ItemEvent e) -> {
            if(e.getStateChange() == (ItemEvent.SELECTED)){
                presenter.onSensorSelected((Sensor)e.getItem());
                System.out.println("selection changed");
            }

         });
         return panel;
    }
    
    private Panel getPeriodPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        panel.add(new Label("Selected period: "));
        panel.add(fromPeriodLabel);
        panel.add(new Label(" - "));
        panel.add(toPeriodLabel);
        return panel;
        
    }
    
 
    
    
    private JMenu changeRangeMenu(){
        JMenu changeRangeMenu = new JMenu("Change range");
        JMenuItem rangeMinutely = new JMenuItem("Minutes");
        rangeMinutely.addActionListener((ActionEvent e)->{
           presenter.onMinutesRangeChange();
        });
        changeRangeMenu.add(rangeMinutely);  
        JMenuItem rangeHourly = new JMenuItem("Hours");
        rangeHourly.addActionListener((ActionEvent e)->{
           presenter.onHoursRangeChange();
        });
        changeRangeMenu.add(rangeHourly);
        JMenuItem rangeDaily = new JMenuItem("Days");
        rangeDaily.addActionListener((ActionEvent e)->{
           presenter.onDaysRangeChange();
        });
        changeRangeMenu.add(rangeDaily);
        return changeRangeMenu;
    }
    
       private JMenu calendarMenu(){
        JMenu calendarMenu = new JMenu("Calendar");
        JMenuItem selectDate = new JMenuItem("Select date");
        selectDate.addActionListener((ActionEvent e)->{
           presenter.onDateSelected();
        });
        calendarMenu.add(selectDate); 
        JMenuItem selectRange = new JMenuItem("Select range");
        selectRange.addActionListener((ActionEvent e)->{
           presenter.onDateRangeSelected();
        });
        calendarMenu.add(selectRange); 
        JMenuItem resetRange = new JMenuItem("Reset range");
        resetRange.addActionListener((ActionEvent e)->{
           presenter.onDefaultRangeSelected();
        });
        calendarMenu.add(resetRange); 
        return calendarMenu;
    }
   

    @Override
    public void setPresenter(BasePresenterInterface presenter) {
        
        this.presenter = (GraphPresenterInterface) presenter;
    }

    @Override
    public void initView() {
        frame = getFrame();
        frame.add(getMainPanel());
        frame.setJMenuBar(getMenuBar());
    }

    @Override
    public void updateGUI() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setGraph(ChartPanel data) {
      graphPanel.removeAll();
      graphPanel.add(data);
      graphPanel.repaint();
      graphPanel.revalidate();
    }

    @Override
    public void setUnitsComboBoxModel(ArrayList<UnitObject> units) {
        unitsComboBox.setModel(new DefaultComboBoxModel(units.toArray()));
    }

    @Override
    public void setSensorsComboBoxModel(ArrayList<Device> sensors) {
        sensorsComboBox.setModel(new DefaultComboBoxModel(sensors.toArray()));
    }

    @Override
    public UnitObject getSelectedUnit() {
        return (UnitObject) unitsComboBox.getSelectedItem();
    }

    @Override
    public Sensor getSelectedSensor() {
        if(sensorsComboBox.getSelectedItem() != null){
            return (Sensor) sensorsComboBox.getSelectedItem();
        }
        else{
            return null;
        }
    }

    @Override
    public void setPeriodLabel(String from, String to) {
        fromPeriodLabel.setText(from);
        toPeriodLabel.setText(to);
    }

    @Override
    public String getFromPeriod() {
       return fromPeriodLabel.getText();
    }

    @Override
    public String getToPeriod() {
        return toPeriodLabel.getText();
    }

    @Override
    public void setGraphInitiator(Device initiator) {
        frame.setTitle("Graph for sensor: " + initiator);
        initiatorLabel.setText(initiator.toString());
    }

    @Override
    public String getGraphInitiator() {
       return initiatorLabel.getText();
    }


    
}
