package com.prsn.services;


import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.prsn.dao.ContractDAO;
import com.prsn.models.ContractLogin;
import com.prsn.security.AuthorizationService;
import com.prsn.security.PasswordEncryptor;
import org.apache.commons.codec.binary.Base64;
import org.jasypt.encryption.pbe.PBEStringEncryptor;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;
import java.security.SecureRandom;
import java.util.Date;

/**
 * @author プロソニーPRSN
 */
@Dependent
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

    public long createContract(String phone, String code) {

        ContractLogin contractLogin = new ContractLogin();
        contractLogin.setPhone(phone);
        contractLogin.setPhone(code); //encryptor.encryptPassword()
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