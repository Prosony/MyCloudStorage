package io.prsn.domain.models;

import java.util.Date ;
import java.util.UUID;

public class Contract{

    private UUID _id;
    private Date date;
    private Date dateLastConnect;
    private String phone;
    private String lastCode;

    public Contract() {}
    public Contract(UUID _id, Date date, Date dateLastConnect, String phone, String lastCode) {
        this._id = _id;
        this.date = date;
        this.dateLastConnect = dateLastConnect;
        this.phone = phone;
        this.lastCode = lastCode;
    }
    
    public void set_id(UUID _id){
        this._id = _id;
    }
    public UUID get_id(){
        return _id;
    }

    public void setDate(Date date){
        this.date = date;
    }
    public Date getDate(){
        return date;
    }

    public void setDateLastConnect(Date dateLastConnect){
        this.dateLastConnect = dateLastConnect;
    }
    public Date getDateLastConnect(){
        return dateLastConnect;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }
    public String getPhone(){
        return phone;
    }

    public String getLastCode() {
        return lastCode;
    }
    public void setLastCode(String lastCode) {
        this.lastCode = lastCode;
    }

}