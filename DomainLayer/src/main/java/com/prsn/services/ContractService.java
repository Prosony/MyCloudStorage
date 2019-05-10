package com.prsn.services;


import javax.enterprise.context.Dependent;


@Dependent
public class ContractService {

    public String createToken(){

        return "NEW STRING";
    }

    public long createContract(String phone, String code) {
        return 0;
    }
}
