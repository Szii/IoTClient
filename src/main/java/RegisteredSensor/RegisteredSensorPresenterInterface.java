/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RegisteredSensor;

import Base.BasePresenterInterface;

/**
 * Contains functionalities which can be used by GUI component controlled by implementation of this class
 * @author brune
 */
public interface RegisteredSensorPresenterInterface extends BasePresenterInterface{
    /**
    * What should happen when send value action occurs
    */
    public void onSendValueClicked();
    /**
    * What should happen when change name action occurs
    */
    public void onChangeNameClicked();
     /**
    * What should happen when remove action occurs
    */
    public void onRemoveClicked();
    /**
    * What should happen when show graph action occurs
    */
    public void onShowHumidityGraphClicked();
    
    public void onShowTemperatureGraphClicked();
    
    /**
     * What should happen when send time action occurs
     */
    public void onSendTimeClicked();
    
    public void onGroupClicked();
    
    public void onMeasurementTypeClicked();
}
