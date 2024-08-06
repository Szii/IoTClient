/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPanel;

import Base.BasePresenterInterface;
import Components.Label;
import Components.Panel;
import RegisteredSensor.RegisteredSensorGUIInterface;
import Unit.UnitGUIInterface;
import UnregisteredSensor.UnregisteredSensorGUIInterface;
import ViewModel.UnitObject;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

/**
 * Implementation of content panel component of main frame.
 * @author brune
 */
public class MainPanelGUI extends Panel implements MainPanelGUIInterface {
    MainPanelPresenter presenter;
    Panel sensorsPanel;
    Panel unitsPanel;
    Label selectedUnit = new Label("Selected unit: ",SwingConstants.CENTER);
    Label selectedUnitID = new Label("",SwingConstants.CENTER);
    
    /**
     * Creates new ccntent panel component
     */
    public MainPanelGUI(){
        initView();
    }
    
    private Panel getLeftPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add(getUnitInfoPanel(),BorderLayout.NORTH);
        panel.add(getSensorsHolder(),BorderLayout.CENTER);
        
        return panel;
    }
    
     private Panel getRightPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add(getUnitsLabelPanel(),BorderLayout.NORTH);
        panel.add(getUnitsHolder(),BorderLayout.CENTER);
        
        return panel;
    }
     
     private Panel getUnitsLabelPanel(){
         Panel panel = new Panel();
         panel.setLayout(new BorderLayout());
         Label label = new Label("Registered units",SwingConstants.CENTER);
         panel.add(label);
         return panel;
     }
    
    
    private JScrollPane getUnitsHolder(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add(getUnitsPanel(),BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setEnabled(false);
        return scrollPane;
    }
    
    private JScrollPane getSensorsHolder(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add(getSensorsPanel(),BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setEnabled(false);
        return scrollPane;
    }
    
    private Panel getUnitInfoPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        panel.add(selectedUnit);
        panel.add(selectedUnitID);
        return panel;
    }
    
    
   private Panel getUnitsPanel(){
        unitsPanel = new Panel();
        unitsPanel.setLayout(new BoxLayout(unitsPanel,BoxLayout.Y_AXIS));
        return unitsPanel;
    }
    
    private Panel getSensorsPanel(){
        sensorsPanel = new Panel();
        sensorsPanel.setLayout(new BoxLayout(sensorsPanel,BoxLayout.Y_AXIS));
        return sensorsPanel;
    }

    @Override
    public void setPresenter(BasePresenterInterface presenter) {
        this.presenter = (MainPanelPresenter) presenter;
    }

    @Override
    public void initView() {
         this.setBorder(null);
         this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
         this.add(getLeftPanel(),BorderLayout.WEST);
         this.add(getRightPanel(),BorderLayout.CENTER);
         
       
    }
    
    

    @Override
    public void addComponent(Component comp) {
        this.add(comp);
    }

    @Override
    public void updateGUI() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clearUnits() {
        unitsPanel.removeAll();
        unitsPanel.repaint();
        unitsPanel.revalidate();
    }

    @Override
    public void clearSensors() {
        sensorsPanel.removeAll();
        sensorsPanel.repaint();
        sensorsPanel.revalidate();
    }

    @Override
    public void addUnit(UnitGUIInterface unit) {

        unitsPanel.add((Panel) unit);
        unitsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        unitsPanel.repaint();
        unitsPanel.revalidate();
    }

    @Override
    public void addRegisteredSensor(RegisteredSensorGUIInterface sensor) {
        synchronized(this){
        sensorsPanel.add((Panel) sensor);
        sensorsPanel.add(Box.createRigidArea(new Dimension(0, 7)));
        sensorsPanel.repaint();
        sensorsPanel.revalidate();
        }
    }

    @Override
    public void addUnregisteredSensor(UnregisteredSensorGUIInterface sensor) {
        sensorsPanel.add((Panel) sensor);
        sensorsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sensorsPanel.repaint();
        sensorsPanel.revalidate();
    }

    @Override
    public void refresh() {
        
        
    }
    @Override
    public void setSelectedUnit(UnitObject unit){
        selectedUnitID.setText(unit.toString());
    }

    
}
