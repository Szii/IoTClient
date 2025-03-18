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
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private static final Logger LOGGER = Logger.getLogger(HttpClient.class.getName());

    private final RestTemplate restTemplate;
    private final String basePath;
    private final ConfigLoader loader;

    public HttpClient() {
        this.loader = new ConfigLoader();
        this.restTemplate = new RestTemplate();
        this.basePath = loader.getAddress() + ":" + loader.getPort();
    }

    private HttpHeaders setToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + ConstantsList.token.trim());
        return headers;
    }

    private <T> ResponseEntity<T> sendRequest(String url, HttpMethod method, Object payload, Class<T> responseType) {
        try {
            HttpEntity<Object> entity = (payload == null)
                    ? new HttpEntity<>(setToken())
                    : new HttpEntity<>(payload, setToken());

            return restTemplate.exchange(url, method, entity, responseType);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error calling API: " + url, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ArrayList<Device> getAllDevices() {
        String url = basePath + "/api/devices/getAll";
        ResponseEntity<Payload> response = sendRequest(url, HttpMethod.GET, null, Payload.class);
        return response.getBody() != null ? new ArrayList<>(response.getBody().getDevices()) : new ArrayList<>();
    }

    public ArrayList<Device> getAllDevicesInGroup(String group) {
        String url = basePath + "/api/devices/getAllInGroup";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("groupName", group);
        ResponseEntity<Payload> response = sendRequest(builder.toUriString(), HttpMethod.GET, null, Payload.class);
        return response.getBody() != null ? new ArrayList<Device>(response.getBody().getDevices()) : new ArrayList<>();
    }

    public Payload login(String username, String password) {
        String url = basePath + "/api/user/login";
        return sendRequest(url, HttpMethod.POST, new CredentialsRequest(username, password), Payload.class).getBody();
    }

    public Payload register(String username, String password) {
        String url = basePath + "/api/user/register";
        return sendRequest(url, HttpMethod.POST, new CredentialsRequest(username, password), Payload.class).getBody();
    }

    public Code updateDevice(String device, String value, DeviceUpdateField field) {
        String url = basePath + "/api/devices/update";
        DeviceRequest payload = new DeviceRequest();
        payload.setDevice(device);

        switch (field) {
            case THRESHOLD -> payload.setTreshold(value);
            case GROUP -> payload.setNewGroup(value);
            case NICKNAME -> payload.setDeviceNickname(value);
            case IRRIGATION_TIME -> payload.setIrrigationTime(value);
        }

        ResponseEntity<Payload> response = sendRequest(url, HttpMethod.POST, payload, Payload.class);
        return response.getBody() != null ? response.getBody().getCode() : null;
    }

    public ArrayList<String> getGroups() {
        String url = basePath + "/api/groups/get";
        ResponseEntity<Payload> response = sendRequest(url, HttpMethod.GET, null, Payload.class);
        return response.getBody() != null ? new ArrayList<>(response.getBody().getGroups()) : new ArrayList<>();
    }

    public Code renameGroup(String group, String newGroup) {
        String url = basePath + "/api/groups/rename";
        GroupRequest payload = new GroupRequest(group, newGroup);
        ResponseEntity<Payload> response = sendRequest(url, HttpMethod.POST, payload, Payload.class);
        return response.getBody() != null ? response.getBody().getCode() : null;
    }

    public Code removeGroup(String group) {
        String url = UriComponentsBuilder.fromHttpUrl(basePath + "/api/groups/remove")
                .queryParam("group", group)
                .toUriString();
        ResponseEntity<Payload> response = sendRequest(url, HttpMethod.DELETE, null, Payload.class);
        return response.getBody() != null ? response.getBody().getCode() : null;
    }

    public Code addGroup(String group) {
        String url = basePath + "/api/groups/add";
        ResponseEntity<Payload> response = sendRequest(url, HttpMethod.POST, new GroupRequest(group), Payload.class);
        return response.getBody() != null ? response.getBody().getCode() : null;
    }

    public Code registerDevice(String device) {
        String url = basePath + "/api/devices/register";
        ResponseEntity<Payload> response = sendRequest(url, HttpMethod.POST, new DeviceRequest(device), Payload.class);
        return response.getBody() != null ? response.getBody().getCode() : null;
    }

    public Code removeDevice(String device) {
        String url = basePath + "/api/devices/unregister";
        ResponseEntity<Payload> response = sendRequest(url, HttpMethod.POST, new DeviceRequest(device), Payload.class);
        return response.getBody() != null ? response.getBody().getCode() : null;
    }

    public ArrayList<Measurement> getMeasurements(String device, String type) {
        return getMeasurements(device, null, null, type);
    }

    public ArrayList<Measurement> getMeasurements(String device, String from, String to, String type) {
        String url = basePath + "/api/measurement/get";
        MeasurementRequest payload = new MeasurementRequest(device, from, to, type);
        ResponseEntity<Payload> response = sendRequest(url, HttpMethod.POST, payload, Payload.class);
        return response.getBody() != null ? new ArrayList<>(response.getBody().getMeasurements()) : new ArrayList<>();
    }
}
