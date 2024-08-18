/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import Base.BasePresenterInterface;
import ViewModel.Group;
import ViewModel.Sensor;
import ViewModel.UnitObject;
import com.irrigation.Messages.MessageData.Device;


/**
 * Contains functionalities which can be used by GUI component controlled by implementation of this class
 * @author brune
 */
public interface GraphPresenterInterface extends BasePresenterInterface{
    
    /**
     * Contains logic of what should happen when sensor is selected
     * @param sensor  Selected sensor
     */
    public void onSensorSelected(Device sensor);
    /**
     * Contains logic of what should happen when unit is selected
     * @param group Selected group
     */
    public void onUnitSelected(Group group);
    /**
     * Contains logic of what should happen when range is changed to minutes
     */
    public void onMinutesRangeChange();
    /**
     * Contains logic of what should happen when range is changed to hours
     */
    public void onHoursRangeChange();
    /**
     * Contains logic of what should happen when range is changed to days
     */
    public void onDaysRangeChange();
    /**
     * Contains logic of what should happen when date is selected
     */
    public void onDateSelected();
    /**
     * Contains logic of what should happen when range between two dates is selected
     */
    public void onDateRangeSelected();
    /**
     * Contains logic of what should happen when range is reseted to its inital state
     */
    public void onDefaultRangeSelected();
    
    
    
   
    
   
    
    
    
    
}
