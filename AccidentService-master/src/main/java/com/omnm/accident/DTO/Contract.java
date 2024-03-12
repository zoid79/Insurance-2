package com.omnm.accident.DTO;



import com.omnm.accident.enumeration.contract.ContractStatus;
import com.omnm.accident.enumeration.contract.ContractTerm;
import com.omnm.accident.enumeration.contract.PaymentCycle;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


public class Contract implements Serializable {
    private Integer id;
    private String customerId;
    private Integer insuranceId;
    private String saleEmployeeId;
    private ContractTerm term;
    private Timestamp startDate;
    private Timestamp expirationDate;
    private Integer paymentFee;
    private PaymentCycle paymentCycle;
    private Timestamp paymentDeadline;
    private Integer compensation;
    private ContractStatus contractStatus;

    public Contract(Integer insuranceId, String saleEmployeeId, String customerId, ContractTerm term, Integer paymentFee, PaymentCycle paymentCycle, Integer compensation, ContractStatus contractStatus) {
        this.insuranceId = insuranceId;
        this.saleEmployeeId = saleEmployeeId;
        this.customerId=customerId;
        this.term = term;
        this.paymentFee = paymentFee;
        this.paymentCycle = paymentCycle;
        this.compensation = compensation;
        this.contractStatus = contractStatus;
    }

    public Contract() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Integer insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getSaleEmployeeId() {
        return saleEmployeeId;
    }

    public void setSaleEmployeeId(String saleEmployeeId) {
        this.saleEmployeeId = saleEmployeeId;
    }

    public ContractTerm getTerm() {
        return term;
    }

    public void setTerm(ContractTerm term) {
        this.term = term;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Timestamp expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getPaymentFee() {
        return paymentFee;
    }

    public void setPaymentFee(Integer paymentFee) {
        this.paymentFee = paymentFee;
    }

    public PaymentCycle getPayCycle() {
        return paymentCycle;
    }

    public void setPayCycle(PaymentCycle paymentCycle) {
        this.paymentCycle = paymentCycle;
    }

    public Timestamp getPaymentDeadline() {
        return paymentDeadline;
    }

    public void setPaymentDeadline(Timestamp paymentDeadline) {
        this.paymentDeadline = paymentDeadline;
    }

    public Integer getCompensation() {
        return compensation;
    }

    public void setCompensation(Integer compensation) {
        this.compensation = compensation;
    }

    public ContractStatus getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(ContractStatus contractStatus) {
        this.contractStatus = contractStatus;
    }

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

    public PaymentCycle getPaymentCycle() {
        return paymentCycle;
    }

    public void setPaymentCycle(PaymentCycle paymentCycle) {
        this.paymentCycle = paymentCycle;
    }
}
