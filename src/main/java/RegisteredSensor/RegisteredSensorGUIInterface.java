/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RegisteredSensor;

import Base.BaseGUIInterface;
import ViewModel.GroupViewModel;
import ViewModel.MeasurementTypeViewModel;
import javax.swing.DefaultComboBoxModel;

/**
 * Contains functionalities of registered sensor GUI component which can be invoked outside of class
 * @author brune
 */
public interface RegisteredSensorGUIInterface extends BaseGUIInterface{
    /**
    * Gets a threshold value inserted by user
    * @return Threshold value 
    */
    public String getThresholdText();
    /**
    * Gets a time value inserted by user
    * @return time value in seconds 
    */
    public String getTimeText();
    /**
     * Sets a ID of sensor
     * @param ID ID of sensor
     */
    public void setID(String ID);
    /**
     * Sets a nickname of sensor
     * @param nickname nickname of sensor
     */
    public void setNickname(String nickname);
    /**
     * Sets a current threshold value of sensor
     * @param threshold Threshold value
     */
    public void setThreshold(String threshold);
    
      /**
     * Sets a current irrigation time value of sensor
     * @param time irrigation time
     */
    public void setIrrigationTime(String time);
        
    /**
     * Sets a current moisture value of sensor
     * @param moisture Current moisture
     */
    public void setMoisture(String moisture);
    /**
     * Set status of the sensor
     * @param status Status of the sensor
     */
    public void setStatus(String status);
    /**
     * Enables / Disables the controls of sensor
     * @param controlsEnabled true == controls enabled
     */
    public void enableControls(boolean controlsEnabled);
    
    public void setGroups(DefaultComboBoxModel groups);

    public GroupViewModel getGroup();


    public void clearGroups();
    
    public void setSelectedGroup(GroupViewModel group);
    
    public void addGroup(GroupViewModel group);
    
    public void enableGroupListener(boolean enabled);
    
    public void setSelectedMeasurementType(MeasurementTypeViewModel measurementTypeViewModel);
    
    public void addMeasurementType(MeasurementTypeViewModel measurementTypeViewModel);
    
    public void enableMesurementsTypeListener(boolean enabled);
    
    public MeasurementTypeViewModel getMeasurementType();
    
    public void setMeasurementTypes(DefaultComboBoxModel measurementTypes);
    
    public void clearMeasurementTypes();
    
    public void setTemperature(String temperature);
        
    
    
    

        
}
