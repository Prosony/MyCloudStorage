package com.prsn.api;


import com.prsn.ResultCodes;
import com.prsn.models.ContractLoginRequest;
import com.prsn.models.ContractRegistrationRequest;
import com.prsn.services.ContractService;

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
import java.util.HashMap;
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

    @POST
    @NoCache
    @Path( value = "/get-token")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String authenticate(@Valid ContractLoginRequest model) {
        return service.createToken(model.getPhone(), model.getCode());
    }

    @POST
    @Path( value = "/create-account")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> createAccount(@Valid ContractRegistrationRequest model,
                                             @Context HttpServletRequest requestContext,
                                             @Context HttpServletResponse response) {

        long status = service.createContract(model.getPhone(), model.getCode());
        Map<String, Object> result = new HashMap<>();

        if (status == ResultCodes.STATUS_CREATED){
            result.put("status", ResultCodes.STATUS_CREATED);
        } else {
            result.put("status", status);
        }
        return result;
    }

    @Inject
    private ContractService service;

    private static final Logger log = LoggerFactory.getLogger(ContractResources.class);
}

