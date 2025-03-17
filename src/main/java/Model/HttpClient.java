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
import com.irrigation.Messages.MessageData.Measurement;
import com.irrigation.Messages.MessageFormat.MeasurementRequest;
import com.irrigation.Messages.MessageFormat.Payload;
import java.util.ArrayList;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author brune
 */
public class HttpClient {
    
      private HttpHeaders setToken(){
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + ConstantsList.token.trim());
            return headers;
      }
    
    
    
       public ArrayList<Device> getAllDevices(){
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:9090/api/devices/getAll";
            HttpEntity<DeviceRequest> entity = new HttpEntity<>( setToken());
            ResponseEntity<Payload> response = restTemplate.exchange(url, HttpMethod.GET, entity, Payload.class);
            Payload deviceResponse = response.getBody();
            return (ArrayList<Device>) deviceResponse.getDevices(); 
        }
   
    
   public ArrayList<Device> getAllDevicesInGroup(String group){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:9090/api/devices/getAllInGroup";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("groupName", group);  // Add any query parameters here
        DeviceRequest payload = new DeviceRequest();
        payload.setNewGroup(group);
        
        HttpEntity<DeviceRequest> entity = new HttpEntity<>(payload, setToken());
        ResponseEntity<Payload> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, Payload.class);
        Payload deviceResponse = response.getBody();
        return (ArrayList<Device>) deviceResponse.getDevices(); 
        
   }
   
   public Payload loginTest(String username, String password){
       
        RestTemplate restTemplate = new RestTemplate();

        // Define the URL of the endpoint
        String url = "http://localhost:9090/api/user/login";
        CredentialsRequest payload = new CredentialsRequest(username, password);
                

        HttpEntity<CredentialsRequest> entity = new HttpEntity<>(payload, setToken());
        ResponseEntity<Payload> response = restTemplate.exchange(url, HttpMethod.POST, entity, Payload.class);

        // Extract the response body
        Payload loginResponse = response.getBody();
        return loginResponse;
       
   }
   
     public Payload registeTest(String username, String password){
       
        RestTemplate restTemplate = new RestTemplate();

        // Define the URL of the endpoint
        String url = "http://localhost:9090/api/user/register";
        CredentialsRequest payload = new CredentialsRequest(username, password);
                

        HttpEntity<CredentialsRequest> entity = new HttpEntity<>(payload, setToken());
        ResponseEntity<Payload> response = restTemplate.exchange(url, HttpMethod.POST, entity, Payload.class);

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
                
        HttpEntity<DeviceRequest> entity = new HttpEntity<>(payload, setToken());
        ResponseEntity<Payload> response = restTemplate.exchange(url, HttpMethod.POST, entity, Payload.class);

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
        
        HttpEntity<DeviceRequest> entity = new HttpEntity<>(payload, setToken());
        ResponseEntity<Payload> response = restTemplate.exchange(url, HttpMethod.POST, entity, Payload.class);
                

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
                
        HttpEntity<DeviceRequest> entity = new HttpEntity<>(payload, setToken());
        ResponseEntity<Payload> response = restTemplate.exchange(url, HttpMethod.POST, entity, Payload.class);

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
        
        HttpEntity<DeviceRequest> entity = new HttpEntity<>(payload, setToken());
        ResponseEntity<Payload> response = restTemplate.exchange(url, HttpMethod.POST, entity, Payload.class);

        // Extract the response body
        Payload loginResponse = response.getBody();
        return loginResponse.getCode();
     }
     
     public ArrayList<String> getGroups(){
       RestTemplate restTemplate = new RestTemplate();

        // Define the URL of the endpoint
        String url = "http://localhost:9090/api/groups/get";
               
        HttpEntity<GroupRequest> entity = new HttpEntity<>(setToken());
        ResponseEntity<Payload> response = restTemplate.exchange(url, HttpMethod.GET, entity, Payload.class);

        // Extract the response body
        Payload deviceResponse = response.getBody();
        return (ArrayList<String>) deviceResponse.getGroups(); 
     }
     
     public Code renameGroup(String group, String newGroup){
              RestTemplate restTemplate = new RestTemplate();

        // Define the URL of the endpoint
        String url = "http://localhost:9090/api/groups/rename";
        GroupRequest payload = new GroupRequest();
        payload.setGroup(group);
        payload.setGroupNewName(newGroup);
                
        HttpEntity<GroupRequest> entity = new HttpEntity<>(payload, setToken());
        ResponseEntity<Payload> response = restTemplate.exchange(url, HttpMethod.POST, entity, Payload.class);

        // Extract the response body
        Payload loginResponse = response.getBody();
        return loginResponse.getCode();
     }
     
     public Code removeGroup(String group){
                       RestTemplate restTemplate = new RestTemplate();

        // Define the URL of the endpoint
        String url = "http://localhost:9090/api/groups/remove";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("group", group);
 
                
        HttpEntity<GroupRequest> entity = new HttpEntity<>(setToken());
        ResponseEntity<Payload> response = restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE, entity, Payload.class);

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
                
        HttpEntity<GroupRequest> entity = new HttpEntity<>(payload, setToken());
        ResponseEntity<Payload> response = restTemplate.exchange(url, HttpMethod.POST, entity, Payload.class);

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

        HttpEntity<DeviceRequest> entity = new HttpEntity<>(payload, setToken());
        ResponseEntity<Payload> response = restTemplate.exchange(url, HttpMethod.POST, entity, Payload.class);

        // Extract the response body
        Payload loginResponse = response.getBody();
        return loginResponse.getCode();
         
     }
     
     public Code removeDevice(String device){
        RestTemplate restTemplate = new RestTemplate();

        // Define the URL of the endpoint
        String url = "http://localhost:9090/api/devices/unregister";
        DeviceRequest payload = new DeviceRequest();

                

        HttpEntity<DeviceRequest> entity = new HttpEntity<>(payload, setToken());
        ResponseEntity<Payload> response = restTemplate.exchange(url, HttpMethod.POST, entity, Payload.class);
        // Extract the response body
        Payload loginResponse = response.getBody();
        return loginResponse.getCode();
     }
     
     public ArrayList<Measurement> getMeasurements(String device,String type){
         RestTemplate restTemplate = new RestTemplate();

        // Define the URL of the endpoint
        String url = "http://localhost:9090/api/measurement/get";

        MeasurementRequest payload = new MeasurementRequest(device,null,null,type);
        HttpEntity<MeasurementRequest> entity = new HttpEntity<>(payload, setToken());
  
        ResponseEntity<Payload> response = restTemplate.exchange(url, HttpMethod.POST, entity, Payload.class);

        // Extract the response body
        Payload measurementResponse = response.getBody();
        System.out.println("Returning measurements" + measurementResponse.getMeasurements());
        return (ArrayList<Measurement>) measurementResponse.getMeasurements();
     }
     
       public ArrayList<Measurement> getMeasurements(String device,String from, String to,String type){
         RestTemplate restTemplate = new RestTemplate();

        // Define the URL of the endpoint
        String url = "http://localhost:9090/api/measurement/get";
        MeasurementRequest payload = new MeasurementRequest(device,from,to,type);
        HttpEntity<MeasurementRequest> entity = new HttpEntity<>(payload, setToken());
  
        ResponseEntity<Payload> response = restTemplate.exchange(url, HttpMethod.POST, entity, Payload.class);  


        // Extract the response body
        Payload measurementResponse = response.getBody();
        return (ArrayList<Measurement>) measurementResponse.getMeasurements();
     }
}
