package io.prns.domain.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.prns.domain.ResultCodes;
import io.prns.domain.db.ConnectionDB;
import io.prns.domain.models.Contract;
import io.prns.domain.models.ContractLogin;

import org.bson.Document;

import javax.inject.Inject;
import java.util.Date;
import java.util.UUID;

public class ContractDAO {

    public long createContract(ContractLogin contractLogin){
        ObjectMapper mapper = new ObjectMapper();
        UUID idContract = UUID.randomUUID();

        contractLogin.set_id(UUID.randomUUID());
        contractLogin.setIdContract(idContract);

        Contract contract = new Contract();

        contract.set_id(idContract);
        contract.setDate(new Date());
        contract.setDateLastConnect(null);
        contract.setPhone(contractLogin.getPhone());
        contract.setLastCode(null);

        try {
            Document contractDoc = Document.parse(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(contract));
            Document contractLoginDoc = Document.parse(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(contractLogin));

            connection.insertDocument("Contract",contractDoc);
            connection.insertDocument("ContractLogin",contractLoginDoc);

            return ResultCodes.STATUS_CREATED;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResultCodes.STATUS_FORBIDDEN;
        }
    }


    @Inject
    ConnectionDB connection;

}
