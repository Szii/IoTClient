/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UnregisteredSensor;

import RegisteredSensor.*;
import Base.BaseGUIInterface;

/**
 * Contains a functionalities for unregistered sensor GUI component
 * @author brune
 */
public interface UnregisteredSensorGUIInterface extends BaseGUIInterface{
    /**
     * Sets a ID of sensor to be shown
     * @param ID sensor ID
     */
    public void setID(String ID);
}
