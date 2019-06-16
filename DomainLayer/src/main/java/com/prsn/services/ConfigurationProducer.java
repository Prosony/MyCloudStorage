package com.prsn.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@ApplicationScoped
public class ConfigurationProducer {


    private final Properties PROPERTIES = new Properties();
    private final String RESOURCE_NAME = "config.properties";

    public ConfigurationProducer(){
        loadConfig();
    }

    @Produces
    public Properties getConfiguration() {
        return PROPERTIES;
    }

    private void loadConfig(){

        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try(InputStream resourceStream = loader.getResourceAsStream(RESOURCE_NAME)) {
            PROPERTIES.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

    @Inject
    private ServletContext servletContext;
}