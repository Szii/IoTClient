/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author brune
 */
import java.io.*;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigLoader {
    private final Properties properties = new Properties();
    private final String configFilePath;

    public ConfigLoader() {
 
        configFilePath = Paths.get(System.getProperty("user.dir"), "config.properties").toString();
        loadProperties();
    }

    private void loadProperties() {
        File configFile = new File(configFilePath);
        if (configFile.exists()) {
            try (InputStream input = new FileInputStream(configFile)) {
                properties.load(input);
                System.out.println("Loaded config from: " + configFilePath);
            } catch (IOException e) {
                System.err.println("Could not load config.properties! " + e.getMessage());
            }
        } else {
            System.out.println("WARNING: config.properties not found! Using defaults.");
        }
    }

    public String getProperty(String key, String defaultValue) {
        return System.getenv().getOrDefault(key, properties.getProperty(key, defaultValue));
    }

    public String getAddress() {
        return properties.getProperty("processingServer.address", "localhost");
    }

    public String getPort() {
        return properties.getProperty("processingServer.port", "8080");
    }
}
