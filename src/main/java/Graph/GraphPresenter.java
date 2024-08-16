/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import ChartDataBuilder.ChartType;
import ChartDataBuilder.Series;
import ChartDataBuilder.Size;
import Constants.ConstantsList;
import DatePicker.DatePickerGUI;
import DatePicker.DatePickerGUIInterface;
import DatePicker.DatePickerPresenter;
import DatePicker.DatePickerPresenterInterface;
import DateRangePicker.DateRangePickerGUI;
import DateRangePicker.DateRangePickerGUIInterface;
import DateRangePicker.DateRangePickerPresenter;
import DateRangePicker.DateRangePickerPresenterInterface;
import ViewModel.Sensor;
import Model.ServiceManager;
import ViewModel.Measurement;
import ViewModel.UnitObject;
import com.irrigation.Messages.MessageData.Device;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartPanel;


/**
 * Class server as controller of component showing graph. It is able to controls the GUi and graph itself.
 * @author brune
 */
public class GraphPresenter implements GraphPresenterInterface,GraphControls {
    
    
    ServiceManager model;
    GraphGUIInterface gui;
    ArrayList<Measurement> data;
    Size size;
    ChartType type;

    
    /**
     * Creates new controls for graph component
     * @param model service manager
     * @param gui Graph to be controlled 
     */
    public GraphPresenter(ServiceManager model, GraphGUIInterface gui) {
        this.model = model;
        this.gui = gui;
        gui.setPresenter(this);
        initView();
    }
 
    @Override
    public void initView() {
        /*
         try {
             ArrayList<UnitObject> units = new ArrayList();
             units.add(new UnitObject(null));
             units.addAll(model.getRegisteredUnits(ConstantsList.loggedUser));
             gui.setUnitsComboBoxModel(units);
        } catch (InterruptedException ex) {
            Logger.getLogger(GraphPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
*/
        gui.initView();
       
    }
    
    private void setChart(ChartPanel data) {
         gui.setGraph(data);
    }
    @Override
    public void createChart(ChartType chartType,Size size,ArrayList<Measurement> data){
       this.data = data;
       this.type = chartType;
       this.size = size;
       model.getChartBuilder().clearData();
       gui.setGraph(model.getChartBuilder().build(chartType,size,createSeries(data))); 
       
    }
 
    private ArrayList<Series> createSeries(ArrayList<Measurement> measurements){
        ArrayList<Series> seriesList = new ArrayList();
        for(Measurement dataInstance : measurements){
              Series series = new Series(dataInstance.getNickname(),dataInstance.getMeasuredData());
              seriesList.add(series);
       }
        return seriesList;
    }

    @Override
    public void onMinutesRangeChange() {
       System.out.println("cliked");
       model.getChartBuilder().clearData();
       gui.setGraph(model.getChartBuilder().build(type, Size.MINUTELY, createSeries(data))); 
    }

    @Override
    public void onHoursRangeChange() {
        model.getChartBuilder().clearData();
        gui.setGraph(model.getChartBuilder().build(type, Size.HOURLY, createSeries(data))); 

        
    }

    @Override
    public void onDaysRangeChange() {
        model.getChartBuilder().clearData();
        gui.setGraph(model.getChartBuilder().build(type, Size.DAILY, createSeries(data))); 
    }

    @Override
    public void onDateSelected() {
        DatePickerGUIInterface datePickerGUI = new DatePickerGUI();
        DatePickerPresenterInterface presenter = new DatePickerPresenter(datePickerGUI,model);
        presenter.setConfirmListener(new ConfirmListener(){
            @Override
            public void onConfirmClicked(){
                 String firstDate = datePickerGUI.getDate() + " " + "00:00:00";
                 String secondDate = datePickerGUI.getDate() + " " + "23:59:59";
                 datePickerGUI.close();
                 onRangeSet(firstDate,secondDate);
            }
            
        });
        presenter.initView(); 
    }
    
    @Override
    public void onDateRangeSelected(){
        DateRangePickerGUIInterface datePickerGUI = new DateRangePickerGUI();
        DateRangePickerPresenterInterface presenter = new DateRangePickerPresenter(datePickerGUI,model);
        
        presenter.setConfirmListener(new ConfirmListener(){
            @Override
            public void onConfirmClicked(){
                 String firstDate = datePickerGUI.getFirstDate() + " " + datePickerGUI.getFirstTime() + ":00";
                 String secondDate = datePickerGUI.getSecondDate() + " " + datePickerGUI.getSecondTime() + ":00";
                 datePickerGUI.close();
                 onRangeSet(firstDate,secondDate);
            }
            
        });
        presenter.initView(); 
    }

    @Override
    public void onSensorSelected(Sensor sensor) {
        try {
            if(data.size() >= 2){
                data.remove(data.size() - 1);
            }
            if(sensor.getID().equals("")){
                createChart(ChartType.SENSOR_MEASUREMENT_SINGLE,size,data);
                return;
            }
            if(gui.getFromPeriod().equals("") || gui.getToPeriod().equals("")){
                data.add(model.getMeasurementValues(sensor.getID())); 
            }
            else{
                data.add(model.getMeasurementValuesInRange(sensor.getID(),gui.getFromPeriod(),gui.getToPeriod())); 
            }
           
            createChart(ChartType.SENSOR_MEASUREMENT_MULTIPLE,size,data);
        } catch (InterruptedException ex) {
            Logger.getLogger(GraphPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onUnitSelected(UnitObject unit) {
        ArrayList<Device> sensors = new ArrayList();
        sensors.add(new Device.DeviceBuilder().build());
        sensors.addAll(model.getRegisteredDevices(ConstantsList.loggedUser));
        gui.setSensorsComboBoxModel(sensors);
    }

    @Override
    public void onDefaultRangeSelected() {
        try {
            
             ArrayList<Measurement> newData = new ArrayList();
             for (Measurement measurement : data){
                newData.add(model.getMeasurementValues(measurement.getSensorID()));
             }
             if(data.size() > 1){
                createChart(ChartType.SENSOR_MEASUREMENT_MULTIPLE, Size.HOURLY, newData);
             }
             else{
                createChart(ChartType.SENSOR_MEASUREMENT_SINGLE, Size.HOURLY, newData);
             }
             data = newData;
             gui.setPeriodLabel("", "");
        } catch (InterruptedException ex) {
            Logger.getLogger(GraphPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void onRangeSet(String from, String to) {
        gui.setPeriodLabel(from, to);
        ArrayList<Measurement> newData = new ArrayList();
        for (Measurement measurement : data){
            newData.add(model.getMeasurementValuesInRange(measurement.getSensorID(), from, to));
        }
       createChart(ChartType.SENSOR_MEASUREMENT_MULTIPLE, Size.HOURLY, newData);
    }
}
