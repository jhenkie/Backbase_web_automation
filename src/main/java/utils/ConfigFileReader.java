package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
    private Properties properties;

    /**
     * Initializes a ConfigFileReader object reading all properties in the specified property file
     */
    public ConfigFileReader() throws IOException {
        String propertyFilePath = "src/main/resources/config.properties";
        try (BufferedReader reader = new BufferedReader(new FileReader(propertyFilePath))) {
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
            } finally {
                reader.close();
            }
        }
    }

    public String getBackBaseLoginURL() {
        return properties.getProperty("backbase.login.url");
    }

    public String getPassword() {
        String value = properties.getProperty("backbase.login.password");
        if (value != null) return value;
        else throw new RuntimeException("Password isn't specified in the property file");
    }
}