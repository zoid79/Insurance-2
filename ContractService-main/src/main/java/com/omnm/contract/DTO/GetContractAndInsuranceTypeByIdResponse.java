package com.omnm.contract.DTO;

import com.omnm.contract.Entity.Contract;

public class GetContractAndInsuranceTypeByIdResponse {
    Contract contract;
    String insuranceType;
    public GetContractAndInsuranceTypeByIdResponse(Contract contract, String insuranceType) {
        this.contract = contract;
        this.insuranceType = insuranceType;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }
}
