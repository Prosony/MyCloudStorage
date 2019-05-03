package io.prns.server_side.services;

import java.security.SecureRandom;
import java.util.Date;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;

import io.prns.domain.dao.ContractDAO;
import io.prns.domain.models.ContractLogin;
import io.prns.server_side.models.ContractRegistrationRequest;
import io.prsn.toolkit.security.AuthorizationService;
import io.prsn.toolkit.security.PasswordEncryptor;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.helpers.ISO8601DateFormat;
import org.jasypt.encryption.pbe.PBEStringEncryptor;

public class ContractService {

    public String createToken(String phone, String lastCode){
        
        final SecureRandom random = new SecureRandom();		
        
        final byte[] saltBytes = new byte[20]; 
        random.nextBytes(saltBytes);
        
		final String salt = Base64.encodeBase64String(saltBytes);		
        String nowDate = new ISO8601DateFormat().format(new Date());
        String encryptesTicket = encryptorProvider.get().encrypt(salt  + "\0" + nowDate );
        return encryptesTicket;
    }

    public long createContract(ContractRegistrationRequest model){

        ContractLogin contractLogin = new ContractLogin();
        contractLogin.setPhone(model.getPhone());
        contractLogin.setPhone(encryptor.encryptPassword(model.getCode()));
        return contractDAO.createContract(contractLogin);
    }

    @Inject
	@Named(AuthorizationService.ENCRYPTOR_NAME)
	private Instance<PBEStringEncryptor> encryptorProvider;

    @Inject
    private PasswordEncryptor encryptor;

    @Inject
    private ContractDAO contractDAO;
}