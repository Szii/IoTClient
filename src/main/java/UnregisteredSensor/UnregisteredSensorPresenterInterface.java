/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UnregisteredSensor;

import RegisteredSensor.*;
import Base.BasePresenterInterface;

/**
 * Used in implementation of unregistered sensor GUI controls
 * @author brune
 */
public interface UnregisteredSensorPresenterInterface extends BasePresenterInterface{
    /**
     * Contains logic of what should happen when register component fires its event
     */
     public void onRegisterSensorClicked();
}
