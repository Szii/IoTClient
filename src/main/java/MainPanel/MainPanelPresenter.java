/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPanel;

import Constants.ConstantsList;
import ViewModel.LiteSensor;
import Model.SensorsPanelObserver;
import Model.ServiceManager;
import Model.UnitsPanelObserver;
import RegisteredSensor.RegisteredSensorGUI;
import RegisteredSensor.RegisteredSensorGUIInterface;
import RegisteredSensor.RegisteredSensorPresenter;
import RegisteredSensor.RegisteredSensorPresenterInterface;
import ViewModel.GroupViewModel;
import ViewModel.MeasurementTypeViewModel;
import com.irrigation.Messages.MessageData.Device;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

/**
 * Implementation of content panel component controller
 * @author brune
 */
public class MainPanelPresenter implements MainPanelPresenterInterface,SensorsPanelObserver,UnitsPanelObserver {
     MainPanelGUIInterface gui;   
     ServiceManager model;
     GroupViewModel selectedGroup = new GroupViewModel("Default");
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
        prepareGroups();
        updateDevices(model.getRegisteredDevices()); 
    }
    
    public void updateGUI(){
        gui.clearSensors();     
    }

    @Override
    public void onChangeNotification() {
        prepareGroups();
        getDevicesBasedOnGroupSelected(selectedGroup);
    }
    
    private void prepareGroups(){
        gui.enableGroupListener(false);
        gui.setGroups(getGroupModel(true));
        gui.enableGroupListener(true);
        if(!model.checkIfGroupsContainGroup(selectedGroup)){
              selectedGroup = new GroupViewModel("Default");
        }
        gui.setSelectedGroup(selectedGroup);
    }
    
    
    private void updateDevices(ArrayList<Device> sensors){
        gui.clearSensors(); 
             for(Device s : sensors){
                 RegisteredSensorGUIInterface sensorView = new RegisteredSensorGUI();
                 sensorView.enableGroupListener(false);
                 RegisteredSensorPresenterInterface sensorPresenter = new RegisteredSensorPresenter(sensorView,model,s);
                 sensorView.setNickname(s.getNickname());
                 sensorView.setID(s.getDeviceID());
                 sensorView.setMoisture(s.getHumidityValue());
                 sensorView.setThreshold(s.getThreshold());
                 sensorView.setIrrigationTime(s.getIrrigationTime());
                 sensorPresenter.initView();
                 sensorView.setGroups(getGroupModel(true));
                 sensorView.setSelectedGroup(new GroupViewModel(s.getGroup()));
                 sensorView.setMeasurementTypes(getMeasurementTypeModel());
                 sensorView.setSelectedMeasurementType(new MeasurementTypeViewModel("Humidity"));
                 gui.addRegisteredSensor(sensorView);
                 sensorView.enableGroupListener(true);
                 sensorView.enableMesurementsTypeListener(true);
                 }
    }
               
    
    private DefaultComboBoxModel getGroupModel(boolean isMenu){
            DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
            if(isMenu){
               comboBoxModel.addElement(new GroupViewModel("Default")); 
            }
            comboBoxModel.addAll(model.getGroups());
            return comboBoxModel;
    }
    
    private DefaultComboBoxModel getMeasurementTypeModel(){
            DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
            comboBoxModel.addElement(new MeasurementTypeViewModel("Humidity")); 
            comboBoxModel.addElement(new MeasurementTypeViewModel("Temperature")); 
            return comboBoxModel;
    }
    
    @Override
    public void updateUnits() {
             getDevicesBasedOnGroupSelected(selectedGroup);


    }

    @Override
    public void onUnitsChange() {
        System.out.println("on units change called");
        updateUnits();
        
    }

    @Override
    public void onUpdateNotification(ArrayList<Device> registerRensors,ArrayList<LiteSensor> unregisterSensors) {
           getDevicesBasedOnGroupSelected(selectedGroup);

    }
    
    private void getDevicesBasedOnGroupSelected(GroupViewModel group){
        if(group.getGroup().equals("Default")){
           System.out.println(ConstantsList.token);
           updateDevices(model.getRegisteredDevices()); 
        }
        else{
           updateDevices(model.getDevicesInGroup(selectedGroup.getGroup())); 
        }

    }
    

    
    @Override
    public void onGroupClicked(){
        if(gui.getGroup().getGroup().equals(selectedGroup.getGroup())){
            return;
        }
        selectedGroup = gui.getGroup();
        System.out.println("Selected group is :" + selectedGroup.getGroup());
        getDevicesBasedOnGroupSelected(selectedGroup);

    }
}
