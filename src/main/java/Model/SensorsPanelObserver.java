/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import ViewModel.LiteSensor;
import ViewModel.Sensor;
import ViewModel.UnitObject;
import java.util.ArrayList;

/**
 * Implementation of behaviour when sensor is changed. Methods of this behaviour are invoked by corresponding observer manager
 * @author brune
 */
public interface SensorsPanelObserver extends Observer {
    /**
     * Method is invoked by corresponding manager when new unit is selected to show its sensors
     * @param unit_ID UnitObject, which is selected
     */
     public void onChangeNotification(UnitObject unit_ID);
     /**
      * Method is invoked by corresponding manager when there is a need to update sensors
      * @param registeredSensors Registered sensors to be updated
      * @param unregisteredSensors Not registered sensors to be updated
      */
     public void onUpdateNotification(ArrayList<Sensor> registeredSensors,ArrayList<LiteSensor> unregisteredSensors);
}
