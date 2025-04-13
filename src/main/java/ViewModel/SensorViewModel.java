/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModel;

import ViewModel.MeasurementViewModel;

/**
 * Data holder class for sensor
 * @author brune
 */
public class SensorViewModel {
    String ID;
    String nickname;
    String threshold;
    String moisture;
    String time;
    MeasurementViewModel measurement;
    /**
     * Gets measurement saved in sensor data holder
     * @return sensor measurements
     */
    public MeasurementViewModel getMeasurement() {
        return measurement;
    }
     /**
      * Sets measurement saved in sensor data holder
      * @param measurement measurements to be saved in
      */
    public void setMeasurement(MeasurementViewModel measurement) {
        this.measurement = measurement;
    }
    boolean status;
    /**
     * Creates new data holder for sensor
     * @param ID ID of sensor
     * @param nickname nickname of sensor
     * @param threshold threshold value of sensor
     * @param moisture current moisture of sensor
     * @param time time of irrigation in seconds
     */
    public SensorViewModel(String ID, String nickname, String threshold, String moisture,String time) {
        this.ID = ID;
        this.nickname = nickname;
        this.threshold = threshold;
        this.moisture = moisture;
        this.time = time;
    }
    /**
     * Gets a ID of sensor
     * @return Id of sensor
     */
    public String getID() {
        return ID;
    }
    /**
     * Gets a nickname of sensor
     * @return nickname of sensor
     */
    public String getNickname() {
        return nickname;
    }
    /**
     * Gets threshold of sensor
     * @return threshold of sensor
     */
    public String getThreshold() {
        return threshold;
    }
    /**
     * Gets a moisture value of sensor
     * @return moisture value
     */
    public String getMoisture() {
        return moisture;
    }
    /**
     * Gets a irrigation time value of sensor
     * @return stored irrigation time
     */
    public String getTime() {
        return time;
    }
    
    @Override
    public String toString(){
        String name = nickname;
        if(ID.equals(nickname)){
            return name;
        }
        return nickname +" (" + ID + ")";
    }
    
    
    
}
