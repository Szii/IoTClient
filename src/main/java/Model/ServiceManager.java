/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import ViewModel.Sensor;
import ChartDataBuilder.ChartBuilder;
import Constants.ConstantsList;
import com.irrigation.Messages.MessageFormat.MessageType;
import ViewModel.Measurement;
import ViewModel.UnitObject;
import com.irrigation.Messages.MessageData.Device;
import com.irrigation.Messages.MessageFormat.Code;
import com.irrigation.Messages.MessageFormat.Payload;
import java.util.ArrayList;

/**
 * Wrapper for services which are available to the controlling components. Serves as layer between controls and application logic.
 * @author brune
 */
public class ServiceManager {
     Request request;
    Response  response;
    ManagerInterface sensorsManager;
    ManagerInterface unitsManager;
    ChartBuilder chartBuilder;
    /**
     * Creates new service manager instance
     * @param request Implementation of request for the data
     * @param response Implementation of response which can sent data to this client
     */
    public ServiceManager(Request request,Response response){
        this.request = request;
        this.response = response;
        sensorsManager = new SensorsManager();
        unitsManager = new UnitsManager();
        
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
    public UnitsManager getUnitsManager(){
        return (UnitsManager) unitsManager;
    }
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
    public Payload checkLogin(String login,String password) throws InterruptedException{
        request.confirmLogin(login, password);
        return response.getPayload(MessageType.CONFIRM_LOGIN);
    }
    
    /**
     * Method sends a request for getting user with same name as current user
     * @param user user name
     * @return returns SUCCESS  , if it is same as current user
     * @throws InterruptedException Exception is thrown when connection to the server is lost
     */
    public Code doesUserExist(String user) throws InterruptedException{
        System.out.println("does user exist?");
        request.getUser(user);
        return response.getPayload(MessageType.GET_USER).getCode();
    }
   /**
    * Method sends request for adding a new user
    * @param user user to be added
    * @param passwd password to be added
    * @return returns SUCCESS  , if registration complete
    * @throws InterruptedException Exception is thrown when connection to the server is lost
    */
   public Code addUser(String user,String passwd) throws InterruptedException{
       System.out.println(" add user");
        request.addUser(user, passwd);
        return response.getPayload(MessageType.ADD_USER).getCode();
    }
   


   /**
    * Method sends request for getting sensors registered under specific unit
    * @param username name of user
    * @return List of sensors
    * @throws InterruptedException Exception is thrown when connection to the server is lost
    */
   public ArrayList<Device> getRegisteredDevices(String username) throws InterruptedException{
       request.getRegisteredDevices(username);
       ArrayList<Device> sensors= (ArrayList<Device>) response.getPayload(MessageType.GET_AVAILABLE_REGISTERED_DEVICES).getCarriedObject();
       System.out.println("Returning sensors");
       return sensors;
   }

   /**
    * Method sends a request for setting a sensor nickname
    * @param sensorID Id of sensor
    * @param nickname nickname
    */
   public void setDeviceNickname(String sensorID,String nickname){
       request.setDeviceNickname(sensorID,nickname);
   }
    /**
    * Method sends a request for setting a threshold of sensor
    * @param sensorID Id of sensor
    * @param value threshold value
    */
   public void setThresold(String sensorID,String value){
        request.setThresold(sensorID, value);
   }
   /**
    * Method sends a request for setting a irrigation time of sensor in seconds
    * @param sensorID ID of sensor
    * @param value irrigation time in seconds
    */
   public void setTime(String sensorID,String value){
       request.setTime(sensorID, value);
   }
   
   /**
    * Method sends a request for registering a sensor under unit
    * @param deviceID ID of sensor
    * @param username name of user
    * @throws InterruptedException
    * @return SUCCESS if registration complete
    */
   public Code registerDevice(String deviceID,String username) throws InterruptedException{
       request.registerDevice(deviceID, username);
       return response.getPayload(MessageType.REGISTER_DEVICE).getCode();
   }
     /**
    * Method sends a request for unregistering a sensor from  unit
    * @param sensorID ID of sensor
    * @param unitID ID of unit
    */
   public void unregisterDevice(String sensorID,String unitID){
       request.unregisterDevice(sensorID,unitID);
     
   }
   
 /**
  * Method sends a request for getting measured values by sensor
  * @param sensorID ID of sensor
  * @return Measured values by sensor
  * @throws InterruptedException Exception is thrown when connection to the server is lost
  */
    public Measurement getMeasurementValues(String sensorID) throws InterruptedException{
       request.getMeasurementValues(sensorID);
       ArrayList<String> measuredData = getMultipleResponses(MessageType.GET_MEASUREMENT_DATA);
       Measurement measurement = new Measurement(sensorID,measuredData);
       return measurement;
   }

   /**
    * Method sends a request for getting measured values in range by sensor
    * @param sensorID Id of sensor
    * @param from lower bound
    * @param to upper bound
    * @return List of measured values
    * @throws InterruptedException Exception is thrown when connection to the server is lost
    */
    public Measurement getMeasurementValuesInRange(String sensorID, String from, String to) throws InterruptedException {
        request.getMeasurementValuesInRange(sensorID, from, to);
        ArrayList<String> measuredValues = getMultipleResponses(MessageType.GET_MEASUREMENT_DATA_IN_RANGE);
        Measurement measurement = new Measurement(sensorID,measuredValues);
        return measurement;
    }
    

   private ArrayList<String> getMultipleResponses(MessageType messageType) throws InterruptedException{

        return (ArrayList<String>) response.getComplexAnswer(messageType);
   }
   
   /**
    * Method gets a request implementation
    * @return request implementation
    */
   public Request getRequester(){
       return request;
   }
    /**
    * Method gets a response implementation
    * @return response implementation
    */
   public Response getResponder(){
       return response;
   }
}
