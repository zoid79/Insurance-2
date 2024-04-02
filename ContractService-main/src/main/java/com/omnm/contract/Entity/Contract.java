package com.omnm.contract.Entity;

import com.omnm.contract.enumeration.ContractStatus;
import com.omnm.contract.enumeration.ContractTerm;
import com.omnm.contract.enumeration.PaymentCycle;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "contract")
public class Contract implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "customer_id", nullable = false)
    private String customerId;
    @Column(name = "insurance_id", nullable = false)
    private Integer insuranceId;
    @Column(name = "sale_employee_id", nullable = true)
    private String saleEmployeeId;
    private ContractTerm term;
    @Column(name = "start_date", nullable = true)
    private Timestamp startDate;
    @Column(name = "expiration_date", nullable = true)
    private Timestamp expirationDate;
    @Column(name = "payment_fee", nullable = false)
    private Integer paymentFee;
    @Column(name = "payment_cycle", nullable = false)
    private PaymentCycle paymentCycle;
    @Column(name = "payment_deadline", nullable = false)
    private Timestamp paymentDeadline;
    private Integer compensation;
    @Column(name = "contract_status", nullable = false)
    private ContractStatus contractStatus;

    public Contract(Integer id, String customerId, Integer insuranceId, String saleEmployeeId, ContractTerm term, Timestamp startDate, Timestamp expirationDate, Integer paymentFee, PaymentCycle paymentCycle, Timestamp paymentDeadline, Integer compensation, ContractStatus contractStatus) {
        this.id = id;
        this.customerId = customerId;
        this.insuranceId = insuranceId;
        this.saleEmployeeId = saleEmployeeId;
        this.term = term;
        this.startDate = startDate;
        this.expirationDate = expirationDate;
        this.paymentFee = paymentFee;
        this.paymentCycle = paymentCycle;
        this.paymentDeadline = paymentDeadline;
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public PaymentCycle getPaymentCycle() {
        return paymentCycle;
    }

    public void setPaymentCycle(PaymentCycle paymentCycle) {
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
}