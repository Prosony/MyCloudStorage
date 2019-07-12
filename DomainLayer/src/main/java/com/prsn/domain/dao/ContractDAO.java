package com.prsn.domain.dao;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.prsn.domain.ResultCodes;
import com.prsn.domain.db.FunctionsDB;
import com.prsn.domain.models.Contract;
import com.prsn.domain.models.ContractLogin;
import com.prsn.domain.services.ContractService;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


import static com.mongodb.client.model.Filters.eq;

/**
 * @author プロソニーPRSN
 */
@Dependent
public class ContractDAO {

    public ContractDAO() {}

    public Map<String, Object> createContract(ContractLogin contractLogin) {

        ObjectMapper mapper = new ObjectMapper();
        UUID idContract = UUID.randomUUID();

        contractLogin.set_id(UUID.randomUUID());
        contractLogin.setIdContract(idContract);

        Date date = new Date(System.currentTimeMillis());
        log.debug("ContractDAO date: {}",date);

        Contract contract = new Contract(
                idContract,
                date,
                contractLogin.getLast(),
                contractLogin.getPhone(),
                contractLogin.getCodeHash()
        );


        Map<String, Object> result = new HashMap<>();

        try {
            Document contractDoc = Document.parse(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(contract));
            Document contractLoginDoc = Document.parse(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(contractLogin));

            Document document = getContractLogin(contract.getPhone());

            if (document != null) {
                result.put("status", ResultCodes.STATUS_CONFLICT);
                result.put("message", "some shit");
                return result;
            }

            functions.insertDocument("Contract",contractDoc);
            functions.insertDocument("ContractLogin",contractLoginDoc);

            result.put("status", ResultCodes.STATUS_CREATED);

            return result;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return  null;
        }
    }

    public Document getContract(String phone) {
        return functions.getCollection("Contract").find()
                .filter(eq("phone", phone)).first();
    }

    public Document getContract(UUID _id) {
        return functions.getCollection("Contract").find()
                .filter(eq("_id", _id)).first();
    }

    public Document getContractLogin(String phone) {
        return functions.getCollection("ContractLogin").find()
                .filter(eq("phone", phone)).first();
    }

    public boolean checkContract(UUID _id) {
        Document document = functions.getCollection("Contract").find()
                .filter(eq("_id", _id)).first();
        return document != null;
    }

    private static final Logger log = LoggerFactory.getLogger(ContractDAO.class);

    @Inject
    private FunctionsDB functions;

}

