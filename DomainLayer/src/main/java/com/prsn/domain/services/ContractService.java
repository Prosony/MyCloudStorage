package com.prsn.domain.services;


import com.prsn.domain.dao.ContractDAO;
import com.prsn.domain.models.Contract;
import com.prsn.domain.models.ContractLogin;
import com.prsn.domain.models.ContractLoginRequest;
import com.prsn.domain.models.ContractRegistrationRequest;
import com.prsn.domain.security.AuthenticationService;
import com.prsn.toolkits.security.PasswordEncryptor;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.Date;
import java.util.Map;
import java.util.UUID;


/**
 * @author プロソニーPRSN
 */
@Dependent
public class ContractService {

    public Map<String, String> createToken(ContractLoginRequest model){

        ContractLogin contractLogin = new ContractLogin();
        contractLogin.setPhone(model.getPhone());
        contractLogin.setCodeHash(encryptor.encryptCode(model.getCode()));

        //TODO get contract from DB, then create token
        Contract contract = getContract(model.getPhone());
        if (contract == null){
            log.debug("contract not find by phone: {}",model.getPhone());
            return null;
        }
        return authenticationService.createJWT(contract);
    }

    public Map<String, Object> createContract(ContractRegistrationRequest model) {

        ContractLogin contractLogin = new ContractLogin();
        contractLogin.setPhone(model.getPhone());
        contractLogin.setCodeHash(encryptor.encryptCode(model.getCode()));
        log.debug("create contract phone: {}",model.getPhone());
        return contractDAO.createContract(contractLogin);
    }


    public Contract getContract(String phone) {
        Document document = contractDAO.getContract(phone);
        return (Contract) convert(document, "Contract");
    }

    public Contract getContract(UUID _id) {
        Document document = contractDAO.getContract(_id);
        return (Contract) convert(document, "Contract");
    }

    public ContractLogin getContractLogin(String phone) {
        Document document = contractDAO.getContractLogin(phone);
        return (ContractLogin) convert(document, "ContractLogin");
    }

    private Object convert(Document document, String type) {
        if (document == null){
            return null;
        }
        switch (type) {
            case "Contract":

                log.debug("document.getDate(date): {}",document.getLong("date"));

                //TODO rewrite this shit
                Contract contract = new Contract();

                if (document.getString("_id") != null)              contract.set_id(UUID.fromString(document.getString("_id")));
                if (document.getLong("date") != null)               contract.setDate(new Date(document.getLong("date")));
                if (document.getLong("dateLastConnect") != null)    contract.setDateLastConnect(new Date(document.getLong("dateLastConnect")));
                if (document.getString("phone") != null)            contract.setPhone(document.getString("phone"));
                if (document.getString("lastCode") != null)         contract.setLastCode(document.getString("lastCode"));

                return contract;

            case "ContractLogin":
                ContractLogin contractLogin = new ContractLogin();

                if (document.getString("_id") != null)          contractLogin.set_id(UUID.fromString(document.getString("_id")));
                if (document.getString("phone") != null)        contractLogin.setPhone(document.getString("phone"));
                if (document.getString("passwordHash") != null) contractLogin.setCodeHash(document.getString("passwordHash"));
                if (document.getDate("last") != null)           contractLogin.setLast(document.getDate("last"));

                return contractLogin;

            default: return null;
        }
    }


    @Inject
	private AuthenticationService authenticationService;

    @Inject
    private PasswordEncryptor encryptor;

    @Inject
    private ContractDAO contractDAO;

    private static final Logger log = LoggerFactory.getLogger(ContractService.class);
}