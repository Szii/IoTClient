/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.irrigation.Messages.MessageFormat.MessageType;
import java.util.ArrayList;

/**
 * Serves as common service for publishing the request for the data from data source
 * @author brune
 */
public interface Request {

    /**
     * Send request for confirming the corectness of login 
     * @param login login name
     * @param password login password
     * @throws InterruptedException Exception is thrown when connection to the server is lost
     */
    public void confirmLogin(String login,String password);
    /**
     * Send request for getting back a name of user
     * @param user name of user
     */
    public void getUser(String user);
    /**
     * Send request for adding a new user into the data source
     * @param user user to be added
     * @param passwd password of the user
     */
    public void addUser(String user,String passwd);

    /**
     * Request for registering sensor under specific unit
     * @param unit_ID Id of unit
     * @param sensor_ID Id of sensor
     */
    public void registerDevice(String unit_ID,String sensor_ID);
    /**
     * Request for unregistering the sensor which is under specific unit
     * @param sensor_ID ID of sensor
     * @param unit_ID ID of unit
     */
    public void unregisterDevice(String sensor_ID,String unit_ID);
    /**
     * Request for checking if unit is online
     * @param username
     */
   
    public void getRegisteredDevices(String username);   

    /**
     * Request for setting sensor nickname
     * @param sensor_ID ID of sensor
     * @param nickname nickname of sensor
     */
    public void setDeviceNickname(String sensor_ID,String nickname);
  
    /**
     * Request for setting threshold for the specific sensor
     * @param sensorID ID of sensor
     * @param value value between 0 - 100
     */
    public void setThresold(String sensorID,String value);
   
     /**
      * Method sends a request for setting irrigation time of a sensor
      * @param sensor_ID ID of sensor
      * @param value irrigation time in seconds
      */
     public void setTime(String sensor_ID, String value);
    
    
    /**
     * Request for all available sensors, which are able to be registered by unit
     * @param unitID ID of unit
     */
    public void getAvailableDevices(String unitID);
    /**
     * Request for setting current output thread as update thread on server side
     * @param clientID 
     */
    public void getMeasurementValues(String sensorID);
    /**
     * Request for all measured values for sensor in between two time intervals
     * @param sensorID ID of sensor
     * @param from lower bound
     * @param to upper bound
     */
    public void getMeasurementValuesInRange(String sensorID,String from,String to);
    
    public void getGroups(String username);
    
    public void getDevicesInGroup(String username,String group);
    
    public void changeGroupName(String username,String oldGroup,String newGroup);
    
    public void changeDeviceGroup(String username, String device);
    
    public void deleteGroup(String username,String group);
    
    public void createGroup(String username, String group);
       
}
