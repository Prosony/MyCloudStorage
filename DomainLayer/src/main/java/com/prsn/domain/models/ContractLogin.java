package com.prsn.domain.models;

import java.util.Date;
import java.util.UUID;

/**
 * @author プロソニーPRSN
 */
public class ContractLogin {

    public ContractLogin(){}


    public ContractLogin(UUID _id, String username, String passwordHash, Date last){
        this._id = _id;
        this.phone = username;
        this.codeHash = passwordHash;
        this.last = last;
    }

    public UUID get_id() {
        return _id;
    }
    public void set_id(UUID _id) {
        this._id = _id;
    }

    public UUID getIdContract() {
        return idContract;
    }
    public void setIdContract(UUID idContract) {
        this.idContract = idContract;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }
    public String getPhone(){
        return phone;
    }

    public void setCodeHash(String codeHash){
        this.codeHash = codeHash;
    }
    public String getCodeHash(){
        return codeHash;
    }

    public Date getLast() {
        return last;
    }
    public void setLast(Date last) {
        this.last = last;
    }


    private UUID _id; // id ContractLogin
    private UUID idContract;
    private String phone;
    private String codeHash;
    private Date last;


}