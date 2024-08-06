/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import Base.BasePresenterInterface;
import ViewModel.Sensor;
import ViewModel.UnitObject;


/**
 * Contains functionalities which can be used by GUI component controlled by implementation of this class
 * @author brune
 */
public interface GraphPresenterInterface extends BasePresenterInterface{
    
    /**
     * Contains logic of what should happen when sensor is selected
     * @param sensor  Selected sensor
     */
    public void onSensorSelected(Sensor sensor);
    /**
     * Contains logic of what should happen when unit is selected
     * @param unit Selected unit
     */
    public void onUnitSelected(UnitObject unit);
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
