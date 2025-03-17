/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.irrigation.Messages.MessageData;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.io.Serializable;

/**
 *
 * @author brune
 */
@JsonDeserialize(builder = Device.DeviceBuilder.class)
public class Device implements Serializable {

    private final String deviceID;

    private final String nickname;

    private final String group;
    
    private final String irrigationTime;

    private final String threshold;

    private final String humidityDate;

    private final String temperatureDate;

    private final String temperatureValue;

    private final String humidityValue;

    private Device(DeviceBuilder builder){
        this.deviceID = builder.deviceID;
        this.nickname = builder.nickname;
        this.group = builder.group;
        this.irrigationTime = builder.irrigationTime;
        this.threshold = builder.threshold;
        this.humidityDate = builder.humidityDate;
        this.temperatureDate = builder.temperatureDate;
        this.temperatureValue = builder.temperatureValue;
        this.humidityValue = builder.humidityValue;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public String getNickname() {
        return nickname;
    }

    public String getGroup() {
        return group;
    }

    public String getIrrigationTime() {
        return irrigationTime;
    }

    public String getThreshold() {
        return threshold;
    }

    public String getHumidityDate() {
        return humidityDate;
    }

    public String getTemperatureDate() {
        return temperatureDate;
    }

    public String getTemperatureValue() {
        return temperatureValue;
    }

    public String getHumidityValue() {
        return humidityValue;
    }

    @Override
    public String toString() {
        return nickname == null ? deviceID : nickname;
    }
    
    
   @JsonPOJOBuilder(withPrefix = "set")
   public static class DeviceBuilder{
       
    private String deviceID;
    private String nickname;
    private String group;
    private String irrigationTime;
    private String threshold;
    private String humidityDate;
    private String temperatureDate;
    private String temperatureValue;
    private String humidityValue;

        public DeviceBuilder setDeviceID(String deviceID) {
            this.deviceID = deviceID;
            return this;
        }

        public DeviceBuilder setNickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public DeviceBuilder setHumidityValue(String lastMeasuredValue) {
            this.humidityValue = lastMeasuredValue;
            return this;
        }
        
        public DeviceBuilder setTemperatureValue(String lastMeasuredValue) {
            this.temperatureValue = lastMeasuredValue;
            return this;
        }

        public DeviceBuilder setGroup(String group) {
            this.group = group;
            return this;
        }

        public DeviceBuilder setIrrigationTime(String irrigationTime) {
            this.irrigationTime = irrigationTime;
            return this;
        }

        public DeviceBuilder setThreshold(String threshold) {
            this.threshold = threshold;
            return this;
        }

        public DeviceBuilder setHumidityDate(String date) {
            this.humidityDate = date;
            return this;
        }
        
        public DeviceBuilder setTemperatureDate(String date) {
            this.temperatureDate = date;
            return this;
        }

    public Device build(){
        return new Device(this);
    }
       
       
   }
     
}
