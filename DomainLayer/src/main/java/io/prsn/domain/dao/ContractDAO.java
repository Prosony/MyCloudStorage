package io.prsn.domain.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.prsn.domain.ResultCodes;
//import io.prsn.domain.db.ConnectionDB;
import io.prsn.domain.models.Contract;
import io.prsn.domain.models.ContractLogin;

import org.bson.Document;

//import javax.inject.Inject;
import javax.enterprise.context.Dependent;
import java.util.Date;
import java.util.UUID;

@Dependent
public class ContractDAO {

    public ContractDAO(){}

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

//            connection.insertDocument("Contract",contractDoc);
//            connection.insertDocument("ContractLogin",contractLoginDoc);

            return ResultCodes.STATUS_CREATED;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResultCodes.STATUS_FORBIDDEN;
        }
    }

//   private  ConnectionDB connection = ConnectionDB.getInstance();
}
