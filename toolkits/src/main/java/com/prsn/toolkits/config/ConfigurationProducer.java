package com.prsn.toolkits.config;


import org.apache.commons.configuration.CompositeConfiguration;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.ext.Provider;


//@Provider
//@Dependent

@ApplicationScoped
public class ConfigurationProducer {

    private CompositeConfiguration configuration;

    @Produces
    public CompositeConfiguration getConfiguration() {
        if (configuration == null){
            configuration = new CompositeConfiguration();
        } else {
            return configuration;
        }
        ConfigurationUtilities.loadConfig(CompositeConfiguration.class, configuration, servletContext.getRealPath("/config.properties"));
        return configuration;
    }

    @Inject
    private ServletContext servletContext;

}