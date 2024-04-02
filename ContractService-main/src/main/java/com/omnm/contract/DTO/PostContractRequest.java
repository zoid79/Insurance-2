package com.omnm.contract.DTO;

import com.omnm.contract.Entity.Contract;

public class PostContractRequest {
    private Contract contract;

    public PostContractRequest(Contract contract) {
        this.contract = contract;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
