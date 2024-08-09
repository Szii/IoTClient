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
import ViewModel.UnitObject;
import com.irrigation.Messages.MessageData.Device;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
          try {
         
             gui.clearSensors();
           
             ArrayList<Device> sensors = model.getRegisteredSensors(ConstantsList.loggedUser);
              System.out.println("Sensors: " + sensors.size()); 
             for(Device s : sensors){
                RegisteredSensorGUIInterface sensorView = new RegisteredSensorGUI();
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
                gui.addRegisteredSensor(sensorView);
            }
             /*
             if(!model.checkIfUnitIsOnline(unit_ID.getID())){
                 JOptionPane.showMessageDialog(null, "Unit is offline, only registered sensors will be shown.");
                  return;
             }
            */
          
       

         } catch (InterruptedException ex) {
             Logger.getLogger(MainPanelPresenter.class.getName()).log(Level.SEVERE, null, ex);
         }

    }
    
    @Override
    public void updateUnits() {
        gui.clearSensors();
        
        
        
         try {
             ArrayList<Device> sensors = model.getRegisteredSensors(ConstantsList.loggedUser);
             
             for(Device s : sensors){
                 RegisteredSensorGUIInterface sensorView = new RegisteredSensorGUI();
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
                 gui.addRegisteredSensor(sensorView);
             }
             
             
             
             
             /*
             try {
             gui.clearSensors();
             ArrayList<UnitObject> units = model.getRegisteredUnits(ConstantsList.loggedUser);
             gui.clearUnits();
             System.out.println("Units size is: " + units.size());
             for(UnitObject s : units){
             UnitGUIInterface unitView = new UnitGUI();
             UnitPresenterInterface unitPresenter = new UnitPresenter(unitView,model,s);
             unitView.setUnitName(s.getNickname());
             unitView.setID(s.getID());
             boolean  status = model.checkIfUnitIsOnline(s.getID());
             if(status){
             unitView.setStatus("greenCircle.png");
             }
             else{
             unitView.setStatus("redCircle.png");
             }
             unitPresenter.initView();
             gui.addUnit(unitView);
             
             }
             
             
             } catch (InterruptedException ex) {
             //Logger.getLogger(UnitsHolderPresenter.class.getName()).log(Level.SEVERE, null, ex);
             }
         */       } catch (InterruptedException ex) {
             Logger.getLogger(MainPanelPresenter.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    public void onUnitsChange() {
        System.out.println("on units change called");
        updateUnits();
        
    }

    @Override
    public void onUpdateNotification(ArrayList<Device> registerRensors,ArrayList<LiteSensor> unregisterSensors) {
             gui.clearSensors();

             for(Device s : registerRensors){
                RegisteredSensorGUIInterface sensorView = new RegisteredSensorGUI();
                RegisteredSensorPresenterInterface sensorPresenter = new RegisteredSensorPresenter(sensorView,model,s);
                System.out.println("Sensor name : " + s);
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
                sensorView.enableControls(true);

                gui.addRegisteredSensor(sensorView);
            }
             gui.refresh();
   
    }
}
