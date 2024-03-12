package com.omnm.accident.Entity;



import com.omnm.accident.enumeration.accident.AccidentStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
@Entity
public class Accident implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "contract_id",nullable = false)
    private int contractId;
    @Column(name = "date",nullable = false)
    private Timestamp date;
    @Column(name = "location",nullable = false)
    private String location;
    @Column(name = "cause",nullable = false)
    private String cause;
    @Column(name = "content",nullable = false)
    private String content;
    @Column(name = "damage",nullable = false)
    private long damage;
    @Column(name = "accountNumber",nullable = false)
    private String accountNumber;
    @Column(name = "status",nullable = false)
    private AccidentStatus status;

    public Accident(int contractId, Timestamp date, String location, String cause, String content, long damage, String  accountNumber, AccidentStatus status) {
        this.contractId = contractId;
        this.date = date;
        this.location = location;
        this.cause = cause;
        this.content = content;
        this.damage = damage;
        this.accountNumber = accountNumber;
        this.status = status;
    }

    public Accident() {

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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getDamage() {
        return damage;
    }

    public void setDamage(long damage) {
        this.damage = damage;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String  accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccidentStatus getStatus() {
        return status;
    }

    public void setStatus(AccidentStatus status) {
        this.status = status;
    }


}