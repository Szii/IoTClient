/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RegisteredSensor;

import ChartDataBuilder.ChartType;
import ChartDataBuilder.Size;
import Constants.ConstantsList;
import Graph.GraphControls;
import Graph.GraphGUI;
import Graph.GraphGUIInterface;
import Graph.GraphPresenter;
import Model.ServiceManager;
import ViewModel.GroupViewModel;
import ViewModel.MeasurementTypeViewModel;
import ViewModel.MeasurementViewModel;
import com.irrigation.Messages.MessageData.Device;
import com.irrigation.Messages.MessageData.Measurement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 * Implementation of registered sensor controls
 * @author brune
 */
public class RegisteredSensorPresenter implements RegisteredSensorPresenterInterface {
    RegisteredSensorGUIInterface gui;
    ServiceManager model;
    Device sensor;
    GroupViewModel selectedGroup = new GroupViewModel("");
    MeasurementTypeViewModel selectedMeasurementType = new MeasurementTypeViewModel("");
    /**
     * Creates new controls for registered sensor component
     * @param gui GUI component to be controlled
     * @param model service manager
     * @param sensor sensor ID
     * @param unit unit ID under sensor in registered
     */
    public RegisteredSensorPresenter(RegisteredSensorGUIInterface gui, ServiceManager model,Device sensor){
        this.gui = gui;
        gui.setPresenter(this);
        this.model = model;
        this.sensor = sensor;
        
    }

    @Override
    public void initView() {
        gui.initView();
    }

    @Override
    public void onSendValueClicked() {
        String value = gui.getThresholdText();
        int numberValue;
        
        try {
            numberValue = Integer.parseInt(value);
                if(numberValue > 100 || numberValue < 0){
                    JOptionPane.showMessageDialog(null, "Enter valid value between 0 and 100");
                    return;
                }
                model.setThresold(sensor.getDeviceID(),value);
                model.getSensorsManager().fireNotification("sensorsChange");
        } 
        catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Enter valid value between 0 and 100");
        }
       
    }

    @Override
    public void onChangeNameClicked() {
        String text = JOptionPane.showInputDialog("Write new name for sensor");
        if(text == null){
            return;
        }
        model.setDeviceNickname(sensor.getDeviceID(), text);
        model.getSensorsManager().fireNotification("sensorsChange");
    }
    
    @Override
    public void onRemoveClicked(){
        model.unregisterDevice(sensor.getDeviceID(),ConstantsList.loggedUser);
        model.getSensorsManager().fireNotification("sensorsChange");
     }
    @Override
    public void onShowHumidityGraphClicked(){
        String type = "TYPE_HUMIDITY";
        GraphGUIInterface gui = new GraphGUI();
        GraphControls graphPresenter = new GraphPresenter(model,gui, type);
        gui.setGraphInitiator(sensor);
        graphPresenter.createChart(ChartType.SENSOR_MEASUREMENT_SINGLE, Size.HOURLY, getMeasurementsForGraph(sensor.getDeviceID(), type));     
    }
    
    @Override
    public void onShowTemperatureGraphClicked() {
        String type = "TYPE_TEMPERATURE";
        GraphGUIInterface gui = new GraphGUI();
        GraphControls graphPresenter = new GraphPresenter(model,gui, type);
        gui.setGraphInitiator(sensor);
        graphPresenter.createChart(ChartType.SENSOR_MEASUREMENT_SINGLE, Size.HOURLY, getMeasurementsForGraph(sensor.getDeviceID(), type));     
    }
    
    private ArrayList<MeasurementViewModel> getMeasurementsForGraph(String deviceId, String type){
         MeasurementViewModel measurementTemperature = null;
        measurementTemperature = model.getMeasurementValues(deviceId,type);
        System.out.println("got measured values for sensor" + deviceId + "values: " + measurementTemperature.getMeasuredData());

        ArrayList<MeasurementViewModel> data = new ArrayList();
        data.add(measurementTemperature);
        return data;
    }

    @Override
    public void onSendTimeClicked() {
         String value = gui.getTimeText();
        int numberValue;
        
        try {
            numberValue = Integer.parseInt(value);
                if(numberValue < 0){
                    JOptionPane.showMessageDialog(null, "Enter valid seconds value");
                    return;
                }
                model.setTime(sensor.getDeviceID(),value);
                model.getSensorsManager().fireNotification("sensorsChange");
        } 
        catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Enter valid seconds value");
        }
    }

    @Override
    public void onGroupClicked() {
        try {
            selectedGroup = gui.getGroup();
            model.changeDeviceGroup(sensor.getDeviceID(),gui.getGroup().getGroup());
            model.getSensorsManager().fireNotification("sensorsChange");
        } catch (InterruptedException ex) {
            Logger.getLogger(RegisteredSensorPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onMeasurementTypeClicked() {
            selectedMeasurementType = gui.getMeasurementType();
            gui.setSelectedMeasurementType(selectedMeasurementType);
            System.out.println(selectedMeasurementType.getMeasurementType());
            if(selectedMeasurementType.getMeasurementType().equals("Humidity")){
                ArrayList<Measurement> data = model.getMeasurementValues(sensor.getDeviceID(),"TYPE_HUMIDITY").getMeasuredData();
                if(data.isEmpty()){
                    gui.setMoisture("");
                    return;
                }
               gui.setMoisture(data.getLast().getValue());
               gui.setSelectedMeasurementType(selectedMeasurementType);
            }
            
            if(selectedMeasurementType.getMeasurementType().equals("Temperature")){
                ArrayList<Measurement> data = model.getMeasurementValues(sensor.getDeviceID(),"TYPE_TEMPERATURE").getMeasuredData();

                if(data.isEmpty()){
                    gui.setTemperature("");
                    return;
                }
                gui.setTemperature(data.getLast().getValue());             
            }

    }
}
