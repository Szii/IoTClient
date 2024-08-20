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
import com.irrigation.Messages.MessageFormat.MessageType;
import com.irrigation.Messages.MessageFormat.Code;
import com.irrigation.Messages.MessageFormat.Payload;
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
      public void registerDevice(String sensor_ID,String username){
           content = new ArrayList();
           content.add(sensor_ID);
           content.add(username);
      
          sendPayload(MessageType.REGISTER_DEVICE,content);
        
       //  sendMessageToClient(ConstantsList.loggedUser,MessageType.REGISTER_SENSOR,content,unit_ID);
       
      }
      @Override
      public void unregisterDevice(String sensor_ID,String username){
          
        content = new ArrayList();
        content.add(sensor_ID);
        content.add(username);
  

        sendPayload(MessageType.UNREGISTER_DEVICE,content);
          
     //   sendMessageToClient(ConstantsList.loggedUser,MessageType.UNREGISTER_SENSOR,content,unit_ID);
        
      }

       @Override
       public void getRegisteredDevices(String username){
        content = new ArrayList();
        content.add(username);
        sendPayload(MessageType.GET_AVAILABLE_REGISTERED_DEVICES,content);
       }

      @Override
      public void setDeviceNickname(String sensor_ID,String nickname){
        content = new ArrayList();
        content.add(sensor_ID);
        content.add(nickname);
 
        sendPayload(MessageType.SET_DEVICE_NICKNAME,content);
      }

      @Override
      public void setThresold(String sensorID,String value){
       content = new ArrayList();
       content.add(sensorID);
       content.add(value);
 
        sendPayload(MessageType.SET_THRESOLD,content);
      }

    @Override
    public void getAvailableDevices(String unitID) {
      
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
    public void setTime(String sensor_ID, String value) {
       content = new ArrayList();
       content.add(sensor_ID);
       content.add(value);

       sendPayload(MessageType.SET_IRRIGATION_TIME,content);
    }

    @Override
    public void addUser(String user, String passwd){
       content = new ArrayList();
       content.add(user);
       content.add(passwd);
       System.out.println("sending add user request");

       sendPayload(MessageType.ADD_USER,content); 
    }

    @Override
    public void getGroups(String username) {
       content = new ArrayList();
       content.add(username);
       System.out.println("sending get all groups request");

       sendPayload(MessageType.GET_GROUPS,content); 
    }

    @Override
    public void getDevicesInGroup(String username, String group) {
       content = new ArrayList();
       content.add(username);
       content.add(group);
       System.out.println("sending get all dewvices in group request");

       sendPayload(MessageType.GET_DEVICES_IN_GROUP,content); 
    }

    @Override
    public void changeGroupName(String username, String oldGroup, String newGroup) {
       content = new ArrayList();
       content.add(username);
       content.add(oldGroup);
       content.add(newGroup);
       System.out.println("sending change group name request");

       sendPayload(MessageType.CHANGE_GROUP_NAME,content); 
    }

    @Override
    public void changeDeviceGroup(String device, String group) {
       content = new ArrayList();
       content.add(ConstantsList.loggedUser);
       content.add(device);
       content.add(group);
       System.out.println("sending change device group request" + device + " " + group);

       sendPayload(MessageType.CHANGE_DEVICE_GROUP,content); 
    }

    @Override
    public void deleteGroup(String username, String group) {
       content = new ArrayList();
       content.add(username);
       content.add(group);
       System.out.println("sending remove group request");

       sendPayload(MessageType.DELETE_GROUP,content); 
    }

    @Override
    public void createGroup(String username, String group) {
       content = new ArrayList();
       content.add(username);
       content.add(group);
       System.out.println("sending add group request");

       sendPayload(MessageType.CREATE_GROUP,content); 
    }

    
}
