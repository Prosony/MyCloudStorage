package com.prsn.api;

import com.prsn.services.ContractService;

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
        return Response.status(200).entity("hello "+service.createToken()).build();
    }

    @Inject
    private ContractService service;
}
