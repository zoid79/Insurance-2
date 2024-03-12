package com.omnm.pay.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
@Entity
public class Pay implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "contract_id",nullable = false)
    private int contractId;
    @Column(name = "cardNumber",nullable = false)
    private String cardNumber;

    public Pay(int contractId, String cardNumber) {
        this.contractId = contractId;
        this.cardNumber = cardNumber;
    }

    public Pay() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
