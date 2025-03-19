/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPanel;

import Base.BaseGUIInterface;
import RegisteredSensor.RegisteredSensorGUIInterface;
import UnregisteredSensor.UnregisteredSensorGUIInterface;
import ViewModel.GroupViewModel;
import ViewModel.UnitObject;
import java.awt.Component;
import javax.swing.DefaultComboBoxModel;

/**
 * Contains functionalities of main panel GUI which can be invoked outside of class
 * @author brune
 */
public interface MainPanelGUIInterface extends BaseGUIInterface{
    /**
     * Adds new component to the main panel
     * @param comp component to be added
     */
     public void addComponent(Component comp);
    /**
     * Removes all sensors
     */
    public void clearSensors();
    /**
     * Adds new registered sensor and shows it
     * @param sensor Sensor GUI component to be added
     */
    public void addRegisteredSensor(RegisteredSensorGUIInterface sensor);
    /**
     * Adds new unregistered sensor and shows it
     * @param sensor Sensor GUI component to be added
     */
    public void addUnregisteredSensor(UnregisteredSensorGUIInterface sensor);
    /**
     * Update the components on the screen
     */
   public void refresh();
   
   public void setGroups(DefaultComboBoxModel groups);

    public GroupViewModel getGroup();


    public void clearGroups();
    
    public void setSelectedGroup(GroupViewModel group);
    
    public void addGroup(GroupViewModel group);
    
    public void enableGroupListener(boolean enabled);
  
}
