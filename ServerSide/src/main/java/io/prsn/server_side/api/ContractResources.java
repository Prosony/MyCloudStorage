package io.prsn.server_side.api;

import java.util.Date;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import io.prsn.domain.services.ContractService;
import io.prsn.server_side.models.ContractRegistrationRequest;
import io.prsn.server_side.models.ContractLoginRequest;
import org.jboss.resteasy.annotations.cache.NoCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestScoped
@Path("/api/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
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
        
//        String token = service.createToken(model.getPhone(), model.getCode());
//
//        return token;
        return null;
    } 

    @POST
    @Path( value = "/create-account")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> createAccount(@Valid ContractRegistrationRequest model,
                                             @Context HttpServletRequest requestContext,
                                             @Context HttpServletResponse response) {


//        long status = service.createContract(model.getPhone(), model.getCode());
//        Map<String, Object> result = new HashMap<>();
//
//        if (status == ResultCodes.STATUS_CREATED){
//            result.put("status", ResultCodes.STATUS_CREATED);
//        } else {
//            result.put("status", status);
//        }

        return null;//result;
    }

    @Inject
    private ContractService service;

    private static final Logger log = LoggerFactory.getLogger(ContractResources.class);
}
