package com.prsn.api;

import com.prsn.db.ConnectionDB;
import com.prsn.services.ContractService;
import com.prsn.services.PropertiesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/")
@RequestScoped
public class HelloResources {

    @GET
    @Path("/hello")
    public Response hello() {
        return Response.status(200).entity("hello").build();
    }

    @GET
    @Path("/echo")
    public Response echo(){
        log.debug("properties: "+properties.getProperty("DataBase.POOL_CONNECTION_MAX"));
        return Response.status(200).build();//entity("hello "+service.createToken("9150333401", "qwerty")).build();
    }


    private static final Logger log = LoggerFactory.getLogger(HelloResources.class);

    @Inject
    private ContractService service;
    @Inject
    private PropertiesService properties;
}
