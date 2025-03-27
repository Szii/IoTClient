/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

/**
 *
 * @author brune
 */
public class MeasurementTypeViewModel {
   String measurementType;

    public MeasurementTypeViewModel(String measurementType) {
        this.measurementType = measurementType;
    }

    
    public String getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(String measurementType) {
        this.measurementType = measurementType;
    }
    
    
    @Override
    public String toString(){
        return measurementType;
    }
    
}
