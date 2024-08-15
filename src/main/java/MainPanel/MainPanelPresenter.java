/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPanel;

import Constants.ConstantsList;
import ViewModel.LiteSensor;
import ViewModel.Sensor;
import Model.SensorsPanelObserver;
import Model.ServiceManager;
import Model.UnitsPanelObserver;
import RegisteredSensor.RegisteredSensorGUI;
import RegisteredSensor.RegisteredSensorGUIInterface;
import RegisteredSensor.RegisteredSensorPresenter;
import RegisteredSensor.RegisteredSensorPresenterInterface;
import Unit.UnitGUI;
import Unit.UnitGUIInterface;
import Unit.UnitPresenter;
import Unit.UnitPresenterInterface;
import UnregisteredSensor.UnregisteredSensorGUI;
import UnregisteredSensor.UnregisteredSensorGUIInterface;
import UnregisteredSensor.UnregisteredSensorPresenter;
import UnregisteredSensor.UnregisteredSensorPresenterInterface;
import ViewModel.Group;
import ViewModel.UnitObject;
import com.irrigation.Messages.MessageData.Device;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 * Implementation of content panel component controller
 * @author brune
 */
public class MainPanelPresenter implements MainPanelPresenterInterface,SensorsPanelObserver,UnitsPanelObserver {
     MainPanelGUIInterface gui;   
     ServiceManager model;
     /**
      * Creates new controls for content panel component
      * @param gui component to be controlled
      * @param model service manager
      */
     public MainPanelPresenter(MainPanelGUIInterface gui,ServiceManager model){
         this.gui = gui;
         gui.setPresenter(this);
         this.model = model;
     }

    @Override
    public void initView() {
      //  gui.initView();
        updateUnits();
    }
    
    public void updateGUI(){
        gui.clearSensors();
        gui.clearUnits();
        
    }

    @Override
    public void onChangeNotification(UnitObject unit_ID) {
             ConstantsList.selectedUnit = unit_ID;
             updateDevices();
    }
    
    private void updateDevices(){
        gui.clearSensors();
        
        
        
         try {
             ArrayList<Device> sensors = model.getRegisteredDevices(ConstantsList.loggedUser);
             
             for(Device s : sensors){
                 RegisteredSensorGUIInterface sensorView = new RegisteredSensorGUI();
                 sensorView.enableGroupListener(false);
                 RegisteredSensorPresenterInterface sensorPresenter = new RegisteredSensorPresenter(sensorView,model,s);
                 sensorView.setNickname(s.getNickname());
                 sensorView.setID(s.getID());
                 /*
                 if(s.isStatus()){
                 sensorView.setStatus("greenCircle.png");
                 }
                 else{
                 sensorView.setStatus("redCircle.png");
                 }
                 */
                 sensorView.setMoisture(s.getLastMeasuredValue());
                 sensorView.setThreshold(s.getThreshold());
                 sensorView.setIrrigationTime(s.getIrrigationTime());
                 sensorPresenter.initView();
                 sensorView.setGroups(getGroupModel());
                 sensorView.setSelectedGroup(new Group(s.getGroup()));
                 gui.addRegisteredSensor(sensorView);
                 sensorView.enableGroupListener(true);
                 }
             
      } catch (InterruptedException ex) {
             Logger.getLogger(MainPanelPresenter.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
               
    private ArrayList<Group> getGroups() throws InterruptedException{
            ArrayList<Group> groups = new ArrayList();
            for(String group : model.getGroups(ConstantsList.loggedUser)){
                    groups.add(new Group(group));
            }
           return groups;
    }
    
    private DefaultComboBoxModel getGroupModel() throws InterruptedException{
         DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
         comboBoxModel.addAll(getGroups());
         return comboBoxModel;
    }
    
    @Override
    public void updateUnits() {
       updateDevices();
    }

    @Override
    public void onUnitsChange() {
        System.out.println("on units change called");
        updateUnits();
        
    }

    @Override
    public void onUpdateNotification(ArrayList<Device> registerRensors,ArrayList<LiteSensor> unregisterSensors) {
             updateDevices();
   
    }
}
