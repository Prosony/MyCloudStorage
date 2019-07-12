package com.prsn.server.api;


import com.prsn.domain.models.ContractLoginRequest;
import com.prsn.domain.models.ContractRegistrationRequest;
import com.prsn.domain.services.ContractService;

import org.jboss.resteasy.annotations.cache.NoCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.Map;

/**
 * @author プロソニーPRSN
 */
@RequestScoped
@Path("/api/v1")
public class ContractResources {

    @GET
    @Path( value = "/echo")
    public String checkDirectory( @Context HttpServletRequest request){
        log.debug("hey there(:");
        String auth = request.getAuthType();
        log.debug("request.getAuthType(): {}",auth);
        return (new Date()).toString();
    }

    @GET
    @NoCache
    @Path( value = "/get-token")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> authenticate(@Valid ContractLoginRequest model) {
        return service.createToken(model);
    }

    @POST
    @Path( value = "/create-contract")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> createAccount(@Valid ContractRegistrationRequest model,
                                             @Context HttpServletRequest requestContext,
                                             @Context HttpServletResponse response) {
        return service.createContract(model);
    }

    @Inject
    private ContractService service;

    private static final Logger log = LoggerFactory.getLogger(ContractResources.class);
}

