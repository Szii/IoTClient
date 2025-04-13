/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModel;

import com.irrigation.Messages.MessageData.Measurement;
import java.util.ArrayList;

/**
 * Data holder class containing measured values by sensor
 * @author brune
 */
public class MeasurementViewModel {
    
    String sensorID;
    String nickname;
    ArrayList<Measurement> measuredData;
    String dataType;

    public MeasurementViewModel(String sensorID, String nickname, ArrayList<Measurement> measuredData, String dataType) {
        this.sensorID = sensorID;
        this.nickname = nickname;
        this.measuredData = measuredData;
        this.dataType = dataType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
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
    public MeasurementViewModel(String sensorID, ArrayList<Measurement> measuredData, String dataType) {
        this.sensorID = sensorID;
        this.measuredData = measuredData;
        this.dataType = dataType;
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
    public ArrayList<Measurement> getMeasuredData() {
        return measuredData;
    }
    
    @Override
    public String toString(){
      return nickname == null ? sensorID :  nickname;
    }
    
    
}
