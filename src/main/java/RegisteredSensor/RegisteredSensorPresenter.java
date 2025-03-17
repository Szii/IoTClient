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
import ViewModel.MeasurementViewModel;
import ViewModel.SensorViewModel;
import ViewModel.UnitObject;
import com.irrigation.Messages.MessageData.Device;
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
    UnitObject unit;
    GroupViewModel selectedGroup = new GroupViewModel("");
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
                model.getSensorsManager().fireNotification("sensorsChange", unit);
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
        model.getSensorsManager().fireNotification("sensorsChange", unit);
    }
    
    @Override
    public void onRemoveClicked(){
        model.unregisterDevice(sensor.getDeviceID(),ConstantsList.loggedUser);
        model.getSensorsManager().fireNotification("sensorsChange", unit);
     }
    @Override
    public void onShowGraphClicked(){
        GraphGUIInterface gui = new GraphGUI();
        GraphControls graphPresenter = new GraphPresenter(model,gui);
        gui.setGraphInitiator(sensor);
        MeasurementViewModel measurement = null;
        measurement = model.getMeasurementValues(sensor.getDeviceID(),"TYPE_HUMIDITY");
        System.out.println("got measured values for sensor" + sensor.getDeviceID() + "values: " + measurement.getMeasuredData());

        ArrayList<MeasurementViewModel> data = new ArrayList();
        data.add(measurement);
        System.out.println("got measured data for sensor" + sensor.getDeviceID() + "values: " + data);
        graphPresenter.createChart(ChartType.SENSOR_MEASUREMENT_SINGLE, Size.HOURLY, data);     
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
                model.getSensorsManager().fireNotification("sensorsChange", unit);
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
            model.getSensorsManager().fireNotification("sensorsChange", unit);
        } catch (InterruptedException ex) {
            Logger.getLogger(RegisteredSensorPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
