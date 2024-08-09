/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UnregisteredSensor;

import Model.ServiceManager;
import RegisteredSensor.*;
import ViewModel.UnitObject;

/**
 * Implementation of unregistered sensor controls
 * @author brune
 */
public class UnregisteredSensorPresenter implements UnregisteredSensorPresenterInterface {
    UnregisteredSensorGUIInterface gui;
    ServiceManager model;
    UnitObject unitID;
    String sensorID;
    /**
     * Creates new controls for unregistered sensor
     * @param gui GUI to be controlled
     * @param model service manager
     * @param sensorID ID of sensor
     * @param unitID unit ID, which currently see this sensor
     */
    public UnregisteredSensorPresenter(UnregisteredSensorGUIInterface gui, ServiceManager model,String sensorID,UnitObject unitID){
        this.gui = gui;
        gui.setPresenter(this);
        this.model = model;
        this.sensorID = sensorID;
        this.unitID = unitID;
        
    }

    @Override
    public void initView() {
        gui.initView();
    }

    @Override
    public void onRegisterSensorClicked() {
      throw new UnsupportedOperationException("registring sensor via unregistered device is not supported");
    }
    
}
