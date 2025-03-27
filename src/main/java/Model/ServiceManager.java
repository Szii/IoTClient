/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import ChartDataBuilder.ChartBuilder;
import ViewModel.GroupViewModel;
import ViewModel.MeasurementTypeViewModel;
import com.irrigation.Messages.MessageFormat.MessageType;
import ViewModel.MeasurementViewModel;
import com.irrigation.Messages.MessageData.Device;
import com.irrigation.Messages.MessageFormat.Code;
import com.irrigation.Messages.MessageData.Measurement;
import com.irrigation.Messages.MessageFormat.Payload;
import java.util.ArrayList;


/**
 * Wrapper for services which are available to the controlling components. Serves as layer between controls and application logic.
 * @author brune
 */
public class ServiceManager {
    ManagerInterface sensorsManager;
    ManagerInterface unitsManager;
    ChartBuilder chartBuilder;
    HttpClient httpClient;
    /**
     * Creates new service manager instance
     * @param request Implementation of request for the data
     * @param response Implementation of response which can sent data to this client
     */
    public ServiceManager(){
        sensorsManager = new SensorsManager();
        httpClient = new  HttpClient();
        
    }
    
    /**
     * Sets new chart builder implementation as usable chart builder service
     * @param chartBuilder chart builder to be set
     */
    public void setChartBuilder(ChartBuilder chartBuilder) {
        this.chartBuilder = chartBuilder;
    }
    /**
     * Gets current chart builder service
     * @return current chart builder service
     */
    public ChartBuilder getChartBuilder() {
        return chartBuilder;
    }
    
    /**
     * Gets current units manager service
     * @return current units manager service
     */
    /**
     * Gets current sensors manager service
     * @return sensors manager service
     */
    public SensorsManager getSensorsManager(){
        return (SensorsManager) sensorsManager;
    }
    /**
     * Method sends request for checking the login and returns a response to it.
     * @param login user login
     * @param password user password
     * @return returns true if login credentials were correct
     * @throws InterruptedException Exception is thrown when connection to the server is lost
     */
    public Payload checkLogin(String login,String password){
        return httpClient.login(login, password);
    }

   /**
    * Method sends request for adding a new user
    * @param user user to be added
    * @param passwd password to be added
    * @return returns SUCCESS  , if registration complete
    */
   public Code addUser(String user,String passwd){
        return httpClient.register(user, passwd).getCode();
    }
   


   /**
    * Method sends request for getting sensors registered under specific unit
    * @param username name of user
    * @return List of sensors
    */
   public ArrayList<Device> getRegisteredDevices(){
       /*
       request.getRegisteredDevices(username);
       ArrayList<Device> sensors= (ArrayList<Device>) response.getPayload(MessageType.GET_AVAILABLE_REGISTERED_DEVICES).getObject();
       */
       System.out.println("Returning sensors");
       return httpClient.getAllDevices();
       //return sensors;
   }

   /**
    * Method sends a request for setting a sensor nickname
    * @param sensorID Id of sensor
    * @param nickname nickname
    */
   public void setDeviceNickname(String sensorID,String nickname){
      // request.setDeviceNickname(sensorID,nickname);
      httpClient.updateDevice(sensorID, nickname, DeviceUpdateField.NICKNAME);
   }
    /**
    * Method sends a request for setting a threshold of sensor
    * @param sensorID Id of sensor
    * @param value threshold value
    */
   public void setThresold(String sensorID,String value){
       // request.setThresold(sensorID, value);
        httpClient.updateDevice(sensorID, value, DeviceUpdateField.THRESHOLD);
   }
   /**
    * Method sends a request for setting a irrigation time of sensor in seconds
    * @param sensorID ID of sensor
    * @param value irrigation time in seconds
    */
   public void setTime(String sensorID,String value){
       //request.setTime(sensorID, value);
        httpClient.updateDevice(sensorID, value, DeviceUpdateField.IRRIGATION_TIME);
   }
   
   /**
    * Method sends a request for registering a sensor under unit
    * @param deviceID ID of sensor
    * @param username name of user
    * @throws InterruptedException
    * @return SUCCESS if registration complete
    */
   public Code registerDevice(String deviceID){
       /*
       request.registerDevice(deviceID, username);
       return response.getPayload(MessageType.REGISTER_DEVICE).getCode();
       */
       return  httpClient.registerDevice(deviceID);
       
   }
     /**
    * Method sends a request for unregistering a sensor from  unit
    * @param sensorID ID of sensor
    * @param unitID ID of unit
    */
   public void unregisterDevice(String sensorID,String unitID){
       //request.unregisterDevice(sensorID,unitID);
       httpClient.removeDevice(sensorID);
     
   }
   
 /**
  * Method sends a request for getting measured values by sensor
  * @param sensorID ID of sensor
  * @param type type of measurement
  * @return Measured values by sensor
  */
    public MeasurementViewModel getMeasurementValues(String sensorID, String type){
     //  request.getMeasurementValues(sensorID);
       ArrayList<Measurement> measuredData = httpClient.getMeasurements(sensorID,type);
       System.out.println(measuredData);
       MeasurementViewModel measurement = new MeasurementViewModel(sensorID,measuredData);
       System.out.println(measurement);
       return measurement;
   }

   /**
    * Method sends a request for getting measured values in range by sensor
    * @param sensorID Id of sensor
    * @param from lower bound
    * @param to upper bound
    * @param type type of measurement
    * @return List of measured values
    */
    public MeasurementViewModel getMeasurementValuesInRange(String sensorID, String from, String to, String type){
       // request.getMeasurementValuesInRange(sensorID, from, to);
        ArrayList<Measurement> measuredValues = httpClient.getMeasurements(sensorID, from, to,type);
        MeasurementViewModel measurement = new MeasurementViewModel(sensorID,measuredValues);
        return measurement;
    }
    
    
    public ArrayList<GroupViewModel> getGroups(){
        //request.getGroups(username);
        ArrayList<GroupViewModel> groups = new ArrayList();
        for (String group :  httpClient.getGroups()){
            groups.add(new GroupViewModel(group));
        }
        return groups;
        
    }
    
    public boolean checkIfGroupsContainGroup(GroupViewModel groupTocheck){
        //request.getGroups(ConstantsList.loggedUser);
        for (String group :  httpClient.getGroups()){
            if(groupTocheck.getGroup().equals(group)){
                return true;
            }
        }
        return false;
        
    }
    
    public ArrayList<MeasurementTypeViewModel> getMeasurementViewModels(){
        //request.getGroups(username);
        ArrayList<MeasurementTypeViewModel> measurementTypes = new ArrayList();
        measurementTypes.add(new MeasurementTypeViewModel("Humidity"));
        measurementTypes.add(new MeasurementTypeViewModel("Temperature"));
        
        return measurementTypes;
    }
    
    public ArrayList<Device> getDevicesInGroup(String group){
        //request.getDevicesInGroup(username, group);
         return httpClient.getAllDevicesInGroup(group);
    }
    
    public Code changeGroupName(String oldGroup,String newGroup){
      //  request.changeGroupName(username, oldGroup, newGroup);
        return httpClient.renameGroup(oldGroup, newGroup);
    }
    
    public Code changeDeviceGroup(String devioe, String group) throws InterruptedException{
        //request.changeDeviceGroup(devioe, group);
        return httpClient.updateDevice(devioe, group, DeviceUpdateField.GROUP);
    }
    
    public Code createNewGroup(String groupname){
       // request.createGroup(username, groupname);
        return httpClient.addGroup(groupname);
    }
    
    public Code deleteGroup(String group){
       // request.deleteGroup(username, group);
         return httpClient.removeGroup(group);
    }
         
}
