/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModel;

import java.util.ArrayList;

/**
 * Data holder class containing measured values by sensor
 * @author brune
 */
public class Measurement {
    
    String sensorID;
    String nickname;
    ArrayList<String> measuredData;
    /**
     * Creates new measurement data holder 
     * @param sensorID ID of sensor
     * @param nickname name of measurement
     * @param measuredData measured data
     */
    public Measurement(String sensorID, String nickname, ArrayList<String> measuredData) {
        this.sensorID = sensorID;
        this.nickname = nickname;
        this.measuredData = measuredData;
    }
    
    /**
     * Gets a name of measurement
     * @return name of measurement
     */
    public String getNickname() {
        return nickname == null ? sensorID :  nickname;
    }
    /**
     * Creates new measurement data holder
     * @param sensorID ID of sensor
     * @param measuredData measured data
     */
    public Measurement(String sensorID, ArrayList<String> measuredData) {
        this.sensorID = sensorID;
        this.measuredData = measuredData;
    }
    /**
     * Gets ID of sensor
     * @return Id of sensor
     */
    public String getSensorID() {
        return sensorID;
    }
    /**
     * Gets measured data
     * @return measured data
     */
    public ArrayList<String> getMeasuredData() {
        return measuredData;
    }
    
    @Override
    public String toString(){
      return nickname == null ? sensorID :  nickname;
    }
    
    
}
