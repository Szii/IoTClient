/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import ViewModel.Sensor;
import ChartDataBuilder.ChartBuilder;
import Constants.ConstantsList;
import com.irrigation.Messages.MessageType;
import ViewModel.Measurement;
import ViewModel.UnitObject;
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
    * Method sends the acknowledge to the server
    * @param user user, which sending the acknowledge
    */ 
    public void acknowledge(String user){
        request.acknowledge(user);
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
    public String checkLogin(String login,String password) throws InterruptedException{
        request.confirmLogin(login, password);
        return response.getAnswer(MessageType.CONFIRM_LOGIN);
    }
    
    /**
     * Method sends a request for getting user with same name as current user
     * @param user user name
     * @return returns user name , if it is same as current user
     * @throws InterruptedException Exception is thrown when connection to the server is lost
     */
    public String getUser(String user) throws InterruptedException{
        request.getUser(user);
        return response.getAnswer(MessageType.GET_USER);
    }
   /**
    * Method sends request for adding a new user
    * @param user user to be added
    * @param passwd password to be added
    * @throws InterruptedException Exception is thrown when connection to the server is lost
    */
   public void addUser(String user,String passwd) throws InterruptedException{
        request.addUser(user, passwd);
    }
   /**
    * Method sends a request for unregistering unit
    * @param unit_ID unit ID to be unregistered
    */
   public void removeUnit(String unit_ID){
       request.unregisterUnit(unit_ID);
   }
   
   /**
    * Method sends a request for getting all registered units belonging to current user
    * @param username name of user
    * @return List of all units registered under specified user
    * @throws InterruptedException Exception is thrown when connection to the server is lost
    */
   public ArrayList<UnitObject> getRegisteredUnits(String username) throws InterruptedException{
        ArrayList<UnitObject> units = new ArrayList();
        request.getRegisteredUnits(username);
        for (String unit : getMultipleResponses(MessageType.GET_REGISTERED_UNITS)){
            UnitObject unitObject = new UnitObject(unit);
            unitObject.setNickname(getUnitNickname(unit));
            units.add(unitObject);
        }
        return units;
        
   }
   /**
    * Method sends request for getting the nickname of unit
    * @param unit_ID ID of unit
    * @return nickname of unit. If unit have no nickname, return ID instead
    * @throws InterruptedException Exception is thrown when connection to the server is lost
    */
   public String getUnitNickname(String unit_ID) throws InterruptedException{
       request.getUnitNickname(unit_ID);
       return response.getAnswer(MessageType.GET_UNIT_NICKNAME);
   }
   /**
    * MEthod sends a request for setting nickname of unit
    * @param unit_ID ID of unit
    * @param nickname nickname
    */
   public void setUnitNickname(String unit_ID,String nickname){
       request.setUnitNickname(unit_ID, nickname);
   }
   /**
    * Method sends a request for registering unit under user
    * @param unit_ID ID of unit
    * @param user login of user
    */
   public void registerUnit(String unit_ID,String user){
       request.registerUnit(unit_ID, user);
       
   }
   /**
    * Method sends request for checking if unit exists
    * @param unit_ID Id of unit
    * @return true value, if unit exists
    * @throws InterruptedException Exception is thrown when connection to the server is lost
    */
   public String checkIfUnitExists(String unit_ID) throws InterruptedException{
       request.checkIfUnitExists(unit_ID);
       String value = response.getAnswer(MessageType.CHECK_IF_UNIT_EXISTS);
       return value;
   }
   /**
    * Method sends request for getting sensors registered under specific unit
    * @param unit_ID ID of unit
    * @return List of sensors
    * @throws InterruptedException Exception is thrown when connection to the server is lost
    */
   public ArrayList<Sensor> getAvailableRegisteredSensors(String unit_ID) throws InterruptedException{
       request.getAvailableRegisteredSensors(unit_ID);
       ArrayList<Sensor> sensors= new ArrayList();
       for (String sensor : getMultipleResponses(MessageType.GET_AVAILABLE_REGISTERED_SENSORS)){
           Sensor sensorUnit = new Sensor(sensor,getSensorNickname(sensor),getThresold(sensor),getMoisture(sensor),this.getIsSensorActive(sensor, unit_ID),this.getTime(sensor));
           sensors.add(sensorUnit);
       }
       return sensors;
   }
   /**
    * Method sends a request for getting a threshold of specific sensor
    * @param sensorID ID of sensor
    * @return
    * @throws InterruptedException Exception is thrown when connection to the server is lost
    */
   public String getThresold(String sensorID) throws InterruptedException{
       request.getThresold(sensorID);
       return response.getAnswer(MessageType.GET_THRESOLD);
   }
   /**
    * Method sends a request for getting current moisture of specific sensor
    * @param sensorID ID of sensor
    * @return Moisture value of sensor
    * @throws InterruptedException Exception is thrown when connection to the server is lost
    */
   public String getMoisture(String sensorID) throws InterruptedException{
       request.getMoisture(sensorID);
       System.out.println("waiting for answer");
       return response.getAnswer(MessageType.GET_MOISTURE);
   }
   /**
    * Method sends a request for getting nickname of specific sensor
    * @param sensorID ID of sensor
    * @return Sensor nickname, if sensor does not have nickname, it returns sensor ID
    * @throws InterruptedException Exception is thrown when connection to the server is lost
    */
   public String getSensorNickname(String sensorID) throws InterruptedException{
       request.getSensorNickname(sensorID);
       System.out.println("getting nickname for  " + sensorID);
       String nickname  = response.getAnswer(MessageType.GET_SENSOR_NICKNAME);
       System.out.println("nickname is: " + nickname);
       return nickname;
   }
   /**
    * Method sends a request for setting a sensor nickname
    * @param sensorID Id of sensor
    * @param nickname nickname
    */
   public void setSensorNickname(String sensorID,String nickname){
       request.setSensorNickname(sensorID,nickname);
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
    * Method sends a request for getting a irrigation time of sensor in seconds
    * @param sensorID ID of sensor
    * @return irrigation time for sensor in seconds
     * @throws  InterruptedException Exception is thrown when connection to the server is lost
    */
   public String getTime(String sensorID) throws InterruptedException{
        request.getTime(sensorID);
        return response.getAnswer(MessageType.GET_IRRIGATION_TIME);
   }
   /**
    * Method sends a request for registering a sensor under unit
    * @param sensorID ID of sensor
    * @param unitID ID of unit
    */
   public void registerSensor(String sensorID,String unitID){
       request.registerSensor(unitID, sensorID);
   }
     /**
    * Method sends a request for unregistering a sensor from  unit
    * @param sensorID ID of sensor
    * @param unitID ID of unit
    */
   public void unregisterSensor(String sensorID,String unitID){
       request.unregisterSensor(sensorID,unitID);
     
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
       Measurement measurement = new Measurement(sensorID,getSensorNickname(sensorID),measuredData);
       return measurement;
   }
   /**
    * Method sends a request for checking if unit is registered under any user
    * @param unit_ID ID of unit
    * @return true value if unit is registered under any user
    * @throws InterruptedException Exception is thrown when connection to the server is lost
    */
   public String checkIfUnitIsRegistered(String unit_ID) throws InterruptedException{
       System.out.println("is unit registered ?");
       request.checkIfUnitIsRegistered(unit_ID);
       String value = response.getAnswer(MessageType.CHECK_IF_UNIT_IS_REGISTERED);
       System.out.println("Is unit registered ? :" + value);
       return value;
   }
   /**
    * Method sends request for checking if unit is online
    * @param unit_ID ID of unit
    * @return true value, if unit is online
    * @throws InterruptedException Exception is thrown when connection to the server is lost
    */
   public boolean checkIfUnitIsOnline(String unit_ID) throws InterruptedException{
       request.isUnitOnline(unit_ID);
       System.out.println("checking if unit is online");
       String value = response.getAnswer(MessageType.IS_UNIT_ONLINE);
       System.out.println("checked, value is: " + value);
       if(value.equals("true")){
           return true; 
       }
       else{
           return false;
       }
   }
   /**
    * Method sends request for getting unregistered sensors around specific unit
    * @param unitID ID of unit
    * @return List of unregistered sensors
    * @throws InterruptedException Exception is thrown when connection to the server is lost
    */
   public ArrayList<String> getUnregisteredSensors(String unitID) throws InterruptedException{
        request.getAvailableSensors(unitID);
        return getMultipleResponses(MessageType.GET_SENSORS_IN_RANGE);
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
        Measurement measurement = new Measurement(sensorID,getSensorNickname(sensorID),measuredValues);
        return measurement;
    }
   /**
    * Method sends a request for checking if sensor is online
    * @param sensorID Id of sensor
    * @param unitID ID of unit
    * @return true value, if sensor is online
    * @throws InterruptedException Exception is thrown when connection to the server is lost
    */
   public boolean getIsSensorActive(String sensorID,String unitID) throws InterruptedException{
        System.out.println("trying to check if sensor is active");
        request.isUnitOnline(unitID);
        String value = response.getAnswer(MessageType.IS_UNIT_ONLINE);
        if(value.equals("false")){
            return false;
        }
        ArrayList<String> sensor = new ArrayList();
        sensor.add(sensorID);
        request.sendMessageToClient(ConstantsList.loggedUser, MessageType.IS_SENSOR_ACTIVE,sensor, unitID);

        String messageFromUnit = response.getAnswer(MessageType.IS_SENSOR_ACTIVE);
        System.out.println("sensor is active: " + messageFromUnit);
        if(messageFromUnit.equals("true")){
            return true;
        }
        else{
            return false;
        }

       
   }
   /**
    * Method sends request to have this output connection be registered as update server thread on client side
    * @param clientID ID of client
    */
   public void initUpdateThread(String clientID){
       request.initUpdateThread(clientID);
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
