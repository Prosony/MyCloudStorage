package io.prsn.toolkit.services;

import javax.enterprise.context.Dependent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Dependent
public class PropertiesService {

    private final Properties property = new Properties();

    public PropertiesService(){

        FileInputStream stream = null;
        try {
            stream = new FileInputStream("src/main/resources/config.properties");
            property.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return property.getProperty(key);
    }
}
