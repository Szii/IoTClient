/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.irrigation.Messages.MessageType;
import java.util.ArrayList;

/**
 * Serves as common service for publishing the request for the data from data source
 * @author brune
 */
public interface Request {
    /**
     * Sends request for acknowledge
     * @param user name of the user
     */
    public void acknowledge(String user);
    /**
     * Send request for confirming the corectness of login 
     * @param login login name
     * @param password login password
     * @throws InterruptedException Exception is thrown when connection to the server is lost
     */
    public void confirmLogin(String login,String password) throws InterruptedException;
    /**
     * Send request for getting back a name of user
     * @param user name of user
     * @throws InterruptedException Exception is thrown when connection to the server is lost
     */
    public void getUser(String user) throws InterruptedException;
    /**
     * Send request for adding a new user into the data source
     * @param user user to be added
     * @param passwd password of the user
     * @throws InterruptedException Exception is thrown when connection to the server is lost
     */
    public void addUser(String user,String passwd) throws InterruptedException;
    /**
     *  Request for getting all units by user ID
     * @param ID ID of user
     * @throws InterruptedException Exception is thrown when connection to the server is lost
     */
    public void getUnits(String ID) throws InterruptedException;
    /**
     * Request for registering new unit under a user
     * @param unit_ID iD of unit
     * @param user ID of user
     */
    public void registerUnit(String unit_ID,String user);
    /**
     * Get registered units which are under a user
     * @param username name of user
     */
    public void getRegisteredUnits(String username);
    /**
     * Request for unregistering specific unit
     * @param unit_ID ID of unit
     */
    public void unregisterUnit(String unit_ID);
    /**
     * Request for check if sensor is active
     * @param sensorID ID of sensor
     */
    public void isSensorActive(String sensorID);
    /**
     * Sends a message to the another client
     * @param from Source of the message
     * @param type type of the message, so receiver can know how to process it
     * @param message message data content
     * @param to receiver of message
     */
    public void sendMessageToClient(String from,MessageType type, ArrayList<String> message,String to);
    /**
     * Request for registering sensor under specific unit
     * @param unit_ID Id of unit
     * @param sensor_ID Id of sensor
     */
    public void registerSensor(String unit_ID,String sensor_ID);
    /**
     * Request for unregistering the sensor which is under specific unit
     * @param sensor_ID ID of sensor
     * @param unit_ID ID of unit
     */
    public void unregisterSensor(String sensor_ID,String unit_ID);
    /**
     * Request for checking if unit is online
     * @param unit_ID ID of unit
     */
    public void isUnitOnline(String unit_ID);   
    /**
     * Request for getting all registered sensors under specific unit
     * @param unit_ID Id of unit
     */
    public void getAvailableRegisteredSensors(String unit_ID);   
    /**
     * Request for getting last measured moisture value of specific sensor
     * @param sensorName ID of sensor
     */
    public void getMoisture(String sensorName);
    /**
     * Request for getting threshold set to the specific sensor
     * @param sensorName ID of sensor
     */
    public void getThresold(String sensorName); 
    /**
     * Request for setting unit nickname
     * @param unit_ID Id of unit
     * @param nickname nickname of unit
     */
    public void setUnitNickname(String unit_ID,String nickname);
    /**
     * Request for setting sensor nickname
     * @param sensor_ID ID of sensor
     * @param nickname nickname of sensor
     */
    public void setSensorNickname(String sensor_ID,String nickname);
    /**
     * Request for getting a sensor nickname
     * @param sensor_ID Id of sensor
     */
    public void getSensorNickname(String sensor_ID);
    /**
     * Request for getting unit nickname
     * @param unit_ID ID of unit 
     */
    public void getUnitNickname(String unit_ID);
    /**
     * Request for check if unit exists
     * @param unit_ID ID of unit 
     */
    public void checkIfUnitExists(String unit_ID);
    /**
     * Request for check if unit is registered under any user
     * @param unit_ID ID of unit 
     */
    public void checkIfUnitIsRegistered(String unit_ID);
    /**
     * Request for setting threshold for the specific sensor
     * @param sensorID ID of sensor
     * @param value value between 0 - 100
     */
    public void setThresold(String sensorID,String value);
    
      /**
      * Method sends a request for irrigation time of sensor
      * @param sensor_ID ID of sensor
      */
     public void getTime(String sensor_ID);
   
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
    public void getAvailableSensors(String unitID);
    /**
     * Request for setting current output thread as update thread on server side
     * @param clientID 
     */
    public void initUpdateThread(String clientID);
    /**
     * Request for all measured values for sensor
     * @param sensorID ID of sensor
     */
    public void getMeasurementValues(String sensorID);
    /**
     * Request for all measured values for sensor in between two time intervals
     * @param sensorID ID of sensor
     * @param from lower bound
     * @param to upper bound
     */
    public void getMeasurementValuesInRange(String sensorID,String from,String to);
}
