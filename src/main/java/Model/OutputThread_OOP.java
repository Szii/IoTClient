/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author brune
 */
import Constants.ConstantsList;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import com.irrigation.Messages.*;
import java.io.ObjectOutputStream;



/**
 *Implementation of request interface. It works as wrapper for requesting the data.
 * @author brune
 */
public class OutputThread_OOP extends Thread implements Request{
 
 //PrintWriter slouží k odesílání požadavků na výstupní proud směrem na server..
 PrintWriter out;
 //BufferedOutputStream se používá při nahrávání souboru na server, na výstupní proud dává binární data
 BufferedOutputStream outbuf;
 //FileInutStream příjmá uživatel vybraný soubor
 FileInputStream fis;
 //BufferedInputStream se používá při nahrávání souboru na server, na vstupním proudu příjmá data ze souboru.
 BufferedInputStream bis;
 ObjectOutputStream objectOutput;
 Response input;

 //Sdílený klientský socket
 Socket clientSocket;
 //globální proměnná pro kód, definující požadavek uživatele.
 String code;
 ArrayList<String> content;
 
 //Slouží k určení maximální velikosti právě jednoho nahraného souboru.
    int maxFileSize = 104857600;
 /**
  * Creates new implementation of request. Used to send requests to the server via socket.
  * @param clientSocket Socket on which requests are sent
  * @throws IOException Exception is thrown when connection on the socket cannot be estabilished
  */
  public OutputThread_OOP(Socket clientSocket) throws IOException{
        this.clientSocket = clientSocket;
        objectOutput = new ObjectOutputStream(clientSocket.getOutputStream());
  } 


     
 @Override
     public void getUser(String user){
        content = new ArrayList();
        content.add(user);
        sendPayload(MessageType.GET_USER,content);
     }

       
 @Override
     public void confirmLogin(String user,String password){
        content = new ArrayList();
        content.add(user);
        content.add(password);
        sendPayload(MessageType.CONFIRM_LOGIN,content);
     }

      @Override
      public void registerSensor(String unit_ID,String sensor_ID){
           throw new UnsupportedOperationException("Registering sewnsors is not supported");
       // content = new ArrayList();
      //  content.add(sensor_ID);
      //  content.add(unit_ID);
      
        //sendPayload(MessageType.REGISTER_SENSOR,content);
        
       //  sendMessageToClient(ConstantsList.loggedUser,MessageType.REGISTER_SENSOR,content,unit_ID);
       
      }
      @Override
      public void unregisterSensor(String sensor_ID,String unit_ID){
          
                 throw new UnsupportedOperationException("Unregistering sewnsors is not supported");
       //  content = new ArrayList();
       //  content.add(sensor_ID);
        //content.add(unit_ID);
  

      //  sendPayload(MessageType.UNREGISTER_SENSOR,content);
          
     //   sendMessageToClient(ConstantsList.loggedUser,MessageType.UNREGISTER_SENSOR,content,unit_ID);
        
      }

       @Override
       public void getAvailableRegisteredSensors(String unit_ID){
        content = new ArrayList();
        content.add(unit_ID);
        sendPayload(MessageType.GET_AVAILABLE_REGISTERED_SENSORS,content);
       }
       
       @Override 
       public void getMoisture(String sensorName){
        content = new ArrayList();
        content.add(sensorName);
  

        sendPayload(MessageType.GET_MOISTURE,content);
       }
      
      @Override 
      public void getThresold(String sensorName){
          content = new ArrayList();
        content.add(sensorName);
  

        sendPayload(MessageType.GET_THRESOLD,content);
   
      }
      @Override
      public void setUnitNickname(String unit_ID,String nickname){
          content = new ArrayList();
          content.add(unit_ID);
        content.add(nickname);
  

        sendPayload(MessageType.SET_UNIT_NICKNAME,content);
      }
      

      
      @Override
      public void setSensorNickname(String sensor_ID,String nickname){
        content = new ArrayList();
        content.add(sensor_ID);
        content.add(nickname);
 
        sendPayload(MessageType.SET_SENSOR_NICKNAME,content);
      }
      @Override
      public void getSensorNickname(String sensor_ID){
        content = new ArrayList();
        content.add(sensor_ID);
 
        sendPayload(MessageType.GET_SENSOR_NICKNAME,content);
      }
      @Override
      public void setThresold(String sensorID,String value){
       content = new ArrayList();
       content.add(sensorID);
       content.add(value);
 
        sendPayload(MessageType.SET_THRESOLD,content);
      }

    @Override
    public void getAvailableSensors(String unitID) {
      
       throw new UnsupportedOperationException("Geting available sensors is not supported");
      // content = new ArrayList();
      // sendMessageToClient(ConstantsList.loggedUser,MessageType.GET_SENSORS_IN_RANGE,content,unitID);
    }
        @Override
       public void getMeasurementValues(String sensorID){
        content = new ArrayList();
       content.add(sensorID);
       sendPayload(MessageType.GET_MEASUREMENT_DATA,content);
       }

    @Override
    public void getMeasurementValuesInRange(String sensorID, String from, String to) {
       content = new ArrayList();
       content.add(sensorID);
       content.add(from);
       content.add(to);
       sendPayload(MessageType.GET_MEASUREMENT_DATA_IN_RANGE,content);
    }
    
    
    
    private void sendPayload(MessageType typeOfMessage,ArrayList<String> messages){
        Payload payload = new Payload.PayloadBuilder(Code.SUCCESS)
                .setContent(messages)
                .setType(typeOfMessage)
                .setToken(ConstantsList.token)
                .build();

        sendMessageToServer(payload);
    }
    

      
     private void sendMessageToServer(Payload message){
     try {
       objectOutput.writeObject(message);
       objectOutput.flush();
     } catch (IOException ex) {}
    }

    @Override
    public void isSensorActive(String sensorID) {
         throw new UnsupportedOperationException("Checking if sensor is online is not supported"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getTime(String sensor_ID) {
       content = new ArrayList();
       content.add(sensor_ID);

       sendPayload(MessageType.GET_IRRIGATION_TIME,content);
    }

    @Override
    public void setTime(String sensor_ID, String value) {
       content = new ArrayList();
       content.add(sensor_ID);
       content.add(value);

       sendPayload(MessageType.SET_IRRIGATION_TIME,content);
    }

    @Override
    public void addUser(String user, String passwd) throws InterruptedException {
       content = new ArrayList();
       content.add(user);
       content.add(passwd);
       System.out.println("sending add user request");

       sendPayload(MessageType.ADD_USER,content); 
    }
   
    
 
      
      
      
      
      
  
 
    
}
