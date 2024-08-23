/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Constants.ConstantsList;
import com.irrigation.Messages.MessageData.Device;
import com.irrigation.Messages.MessageFormat.Code;
import com.irrigation.Messages.MessageFormat.CredentialsRequest;
import com.irrigation.Messages.MessageFormat.DeviceRequest;
import com.irrigation.Messages.MessageFormat.GroupRequest;
import com.irrigation.Messages.MessageFormat.MeasurementRequest;
import com.irrigation.Messages.MessageFormat.Payload;
import java.util.ArrayList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author brune
 */
public class HttpClient {
    
    
    
       public ArrayList<Device> getAllDevices(){
         RestTemplate restTemplate = new RestTemplate();

        // Define the URL of the endpoint
        String url = "http://localhost:9090/api/devices/getAll";
        DeviceRequest payload = new DeviceRequest();
        payload.setToken(ConstantsList.token);
                

        // Send the request and receive the response as LoginResponse object
        ResponseEntity<Payload> response = restTemplate.postForEntity(url, payload, Payload.class);

        // Extract the response body
        Payload loginResponse = response.getBody();
        System.out.println("got response" + loginResponse.getCode());
        System.out.println("got data" + loginResponse.getObject());
        return  loginResponse.getObject();
        
   }
   
    
   public ArrayList<Device> getAllDevicesInGroup(String group){
         RestTemplate restTemplate = new RestTemplate();

        // Define the URL of the endpoint
        String url = "http://localhost:9090/api/devices/getAllInGroup";
        DeviceRequest payload = new DeviceRequest();
        payload.setToken(ConstantsList.token);
        payload.setNewGroup(group);
                

        // Send the request and receive the response as LoginResponse object
        ResponseEntity<Payload> response = restTemplate.postForEntity(url, payload, Payload.class);

        // Extract the response body
        Payload loginResponse = response.getBody();
        return loginResponse.getObject();
        
   }
   
   public Payload loginTest(String username, String password){
       
        RestTemplate restTemplate = new RestTemplate();

        // Define the URL of the endpoint
        String url = "http://localhost:9090/api/login";
        CredentialsRequest payload = new CredentialsRequest(username, password);
                

        // Send the request and receive the response as LoginResponse object
        ResponseEntity<Payload> response = restTemplate.postForEntity(url, payload, Payload.class);

        // Extract the response body
        Payload loginResponse = response.getBody();
        return loginResponse;
       
   }
   
     public Payload registeTest(String username, String password){
       
        RestTemplate restTemplate = new RestTemplate();

        // Define the URL of the endpoint
        String url = "http://localhost:9090/api/register";
        CredentialsRequest payload = new CredentialsRequest(username, password);
                

        // Send the request and receive the response as LoginResponse object
        ResponseEntity<Payload> response = restTemplate.postForEntity(url, payload, Payload.class);

        // Extract the response body
        Payload loginResponse = response.getBody();
        return loginResponse;
       
   }
     
     public Code updateDeviceThresold(String device,String thresold){
               RestTemplate restTemplate = new RestTemplate();

        // Define the URL of the endpoint
        String url = "http://localhost:9090/api/devices/update";
        DeviceRequest payload = new DeviceRequest();
         payload.setDevice(device);
        payload.setTreshold(thresold);
        payload.setToken(ConstantsList.token);
                

        // Send the request and receive the response as LoginResponse object
        ResponseEntity<Payload> response = restTemplate.postForEntity(url, payload, Payload.class);

        // Extract the response body
        Payload loginResponse = response.getBody();
        return loginResponse.getCode();
         
     }
     
     public Code updateDeviceGroup(String device, String group){
        RestTemplate restTemplate = new RestTemplate();

        // Define the URL of the endpoint
        String url = "http://localhost:9090/api/devices/update";
        DeviceRequest payload = new DeviceRequest();
        payload.setDevice(device);
        payload.setNewGroup(group);
        payload.setToken(ConstantsList.token);
        
        payload.setToken(ConstantsList.token);
                

        // Send the request and receive the response as LoginResponse object
        ResponseEntity<Payload> response = restTemplate.postForEntity(url, payload, Payload.class);

        // Extract the response body
        Payload loginResponse = response.getBody();
        return loginResponse.getCode();
         
     }
     
     public Code updateDeviceName(String device, String newName){
         RestTemplate restTemplate = new RestTemplate();

        // Define the URL of the endpoint
        String url = "http://localhost:9090/api/devices/update";
        DeviceRequest payload = new DeviceRequest();
        payload.setDevice(device);
        payload.setDeviceNickname(newName);
        payload.setToken(ConstantsList.token);
                

        // Send the request and receive the response as LoginResponse object
        ResponseEntity<Payload> response = restTemplate.postForEntity(url, payload, Payload.class);

        // Extract the response body
        Payload loginResponse = response.getBody();
        return loginResponse.getCode();
     }
     
     public Code updateDeviceIrrigationTime(String device, String time){
          RestTemplate restTemplate = new RestTemplate();

        // Define the URL of the endpoint
        String url = "http://localhost:9090/api/devices/update";
        DeviceRequest payload = new DeviceRequest();
        payload.setDevice(device);
        payload.setIrrigationTime(time);
        payload.setToken(ConstantsList.token);
                

        // Send the request and receive the response as LoginResponse object
        ResponseEntity<Payload> response = restTemplate.postForEntity(url, payload, Payload.class);

        // Extract the response body
        Payload loginResponse = response.getBody();
        return loginResponse.getCode();
     }
     
     public ArrayList<String> getGroups(){
                   RestTemplate restTemplate = new RestTemplate();

        // Define the URL of the endpoint
        String url = "http://localhost:9090/api/groups/get";
        GroupRequest payload = new GroupRequest();
        payload.setToken(ConstantsList.token);
                

        // Send the request and receive the response as LoginResponse object
        ResponseEntity<Payload> response = restTemplate.postForEntity(url, payload, Payload.class);

        // Extract the response body
        Payload loginResponse = response.getBody();
        return     (ArrayList<String>) loginResponse.getContent();
     }
     
     public Code renameGroup(String group, String newGroup){
              RestTemplate restTemplate = new RestTemplate();

        // Define the URL of the endpoint
        String url = "http://localhost:9090/api/groups/rename";
        GroupRequest payload = new GroupRequest();
        payload.setGroup(group);
        payload.setGroupNewName(newGroup);
        payload.setToken(ConstantsList.token);
                

        // Send the request and receive the response as LoginResponse object
        ResponseEntity<Payload> response = restTemplate.postForEntity(url, payload, Payload.class);

        // Extract the response body
        Payload loginResponse = response.getBody();
        return loginResponse.getCode();
     }
     
     public Code removeGroup(String group){
                       RestTemplate restTemplate = new RestTemplate();

        // Define the URL of the endpoint
        String url = "http://localhost:9090/api/groups/remove";
        GroupRequest payload = new GroupRequest();
        payload.setGroup(group);
        payload.setToken(ConstantsList.token);
                

        // Send the request and receive the response as LoginResponse object
        ResponseEntity<Payload> response = restTemplate.postForEntity(url, payload, Payload.class);

        // Extract the response body
        Payload loginResponse = response.getBody();
        return loginResponse.getCode();
         
     }
     
      public Code addGroup(String group){
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("CREATING GROUP " + group);
        // Define the URL of the endpoint
        String url = "http://localhost:9090/api/groups/add";
        GroupRequest payload = new GroupRequest();
        payload.setGroup(group);
        payload.setToken(ConstantsList.token);
                

        // Send the request and receive the response as LoginResponse object
        ResponseEntity<Payload> response = restTemplate.postForEntity(url, payload, Payload.class);

        // Extract the response body
        Payload loginResponse = response.getBody();
        return loginResponse.getCode();
         
     }
      
     public Code registerDevice(String device){
                       RestTemplate restTemplate = new RestTemplate();

        // Define the URL of the endpoint
        String url = "http://localhost:9090/api/devices/register";
        DeviceRequest payload = new DeviceRequest();
        payload.setDevice(device);
        payload.setToken(ConstantsList.token);
                

        // Send the request and receive the response as LoginResponse object
        ResponseEntity<Payload> response = restTemplate.postForEntity(url, payload, Payload.class);

        // Extract the response body
        Payload loginResponse = response.getBody();
        return loginResponse.getCode();
         
     }
     
     public Code removeDevice(String device){
        RestTemplate restTemplate = new RestTemplate();

        // Define the URL of the endpoint
        String url = "http://localhost:9090/api/devices/unregister";
        DeviceRequest payload = new DeviceRequest();
        payload.setDevice(device);
        payload.setToken(ConstantsList.token);
                

        // Send the request and receive the response as LoginResponse object
        ResponseEntity<Payload> response = restTemplate.postForEntity(url, payload, Payload.class);

        // Extract the response body
        Payload loginResponse = response.getBody();
        return loginResponse.getCode();
     }
     
     public ArrayList<String> getMeasurements(String device){
         RestTemplate restTemplate = new RestTemplate();

        // Define the URL of the endpoint
        String url = "http://localhost:9090/api/measurement/get";
        MeasurementRequest payload = new MeasurementRequest();
        payload.setDevice(device);
        payload.setToken(ConstantsList.token);
                

        // Send the request and receive the response as LoginResponse object
        ResponseEntity<Payload> response = restTemplate.postForEntity(url, payload, Payload.class);

        // Extract the response body
        Payload loginResponse = response.getBody();
        return (ArrayList<String>) loginResponse.getContent();
     }
     
       public ArrayList<String> getMeasurements(String device,String from, String to){
         RestTemplate restTemplate = new RestTemplate();

        // Define the URL of the endpoint
        String url = "http://localhost:9090/api/measurement/get";
        MeasurementRequest payload = new MeasurementRequest();
        payload.setDevice(device);
        payload.setFrom(from);
        payload.setTo(to);
        payload.setToken(ConstantsList.token);
                

        // Send the request and receive the response as LoginResponse object
        ResponseEntity<Payload> response = restTemplate.postForEntity(url, payload, Payload.class);

        // Extract the response body
        Payload loginResponse = response.getBody();
        return (ArrayList<String>) loginResponse.getContent();
     }
}
