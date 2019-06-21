package com.prsn.services;


import com.prsn.config.ConfigurationLoaded;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.event.ConfigurationEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.ext.Provider;

@Provider
@Dependent
public class ConfigurationProducer {


    @Produces
    @ApplicationScoped
    public CompositeConfiguration getConfiguration() {
        final CompositeConfiguration config = new CompositeConfiguration();
        ConfigurationUtilities.loadConfig(CompositeConfiguration.class, config, servletContext.getRealPath("/config.properties"));
        return config;
    }

    @Inject
    private ServletContext servletContext;

    @Inject
    @ConfigurationLoaded
    private Event<ConfigurationEvent> configurationLoadedEvent;
}