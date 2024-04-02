package com.omnm.contract.DTO;

import java.sql.Timestamp;

public class PatchDeadlineInContractByIdRequest {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getPaymentDeadline() {
        return paymentDeadline;
    }

    public void setPaymentDeadline(Timestamp paymentDeadline) {
        this.paymentDeadline = paymentDeadline;
    }

    Integer id;
    Timestamp paymentDeadline;

    public PatchDeadlineInContractByIdRequest(Integer id, Timestamp paymentDeadline) {
        this.id = id;
        this.paymentDeadline = paymentDeadline;
    }
}
