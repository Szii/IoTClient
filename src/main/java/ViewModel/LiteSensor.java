/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModel;

/**
 * Data class for unregistered sensor
 * @author brune
 */
public class LiteSensor {
    String ID;
    /**
     * Creates new unregistered sensor data holder
     * @param ID ID of sensor
     */
    public LiteSensor(String ID){
        this.ID = ID;
    }
    /**
     * Gets  ID of sensor
     * @return ID of sensor
     */
    public String getID(){
        return ID;
    }
}
