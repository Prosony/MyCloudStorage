package com.prsn.toolkits.security;

import javax.ws.rs.container.ContainerRequestContext;
import java.util.Date;

public interface AuthorizationService {

    public static final String ENCRYPTOR_NAME = "AuthorizationServiceEncryptor";

    int getTTL();

    String getKey();

    String setupSecurityContext(ContainerRequestContext request, String salt, String data, Date ticketDate);

    Long getContractIdFromData(String data);
}

