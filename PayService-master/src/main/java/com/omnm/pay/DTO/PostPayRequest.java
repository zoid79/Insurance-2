package com.omnm.pay.DTO;


import com.omnm.pay.Entity.Contract;
import com.omnm.pay.Entity.Pay;

public class PostPayRequest {

    private Contract contract;
    private Pay pay;

    public Contract getContract() {return contract;}

    public void setContract(Contract contract) {this.contract = contract;}

    public Pay getPay() {return pay;}

    public void setPay(Pay pay) {this.pay = pay;}
}
