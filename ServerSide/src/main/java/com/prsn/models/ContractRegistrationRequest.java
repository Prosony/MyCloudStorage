package com.prsn.models;

import com.prsn.validation.Phone;

import javax.validation.constraints.NotNull;

public class ContractRegistrationRequest {

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    @Phone
    @NotNull
    private String phone;

    @NotNull
    private String code;
}
