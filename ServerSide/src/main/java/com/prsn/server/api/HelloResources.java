package com.prsn.server.api;

import com.prsn.domain.db.ConnectionDB;
import com.prsn.domain.services.ContractService;
import com.prsn.toolkits.config.ConfigurationProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * for testing
 * f#ck junit
 * @author プロソニーPRSN
 */
@Path("/")
@RequestScoped
public class HelloResources {

    @GET
    @Path("/hello")
    public Response hello() {
        log.debug("HELLO WORLD");
        log.debug("producer: "+producer.getConfiguration().getProperty("DataBase.POOL_CONNECTION_MAX"));
        return Response.status(200).entity("hello").build();
    }

    @GET
    @Path("/echo")
    public Response echo(){
        return Response.status(200).build();
    }


    private static final Logger log = LoggerFactory.getLogger(HelloResources.class);

    @Inject
    private ContractService service;

    @Inject
    private ConfigurationProducer producer;

    @Inject
    private ConnectionDB connectionDB;

}
