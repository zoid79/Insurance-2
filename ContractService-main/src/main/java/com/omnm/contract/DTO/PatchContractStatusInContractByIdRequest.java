package com.omnm.contract.DTO;

import com.omnm.contract.enumeration.ContractStatus;

public class PatchContractStatusInContractByIdRequest {
    Integer id;
    ContractStatus status;
    public PatchContractStatusInContractByIdRequest(Integer id, ContractStatus status) {
        this.id = id;
        this.status = status;
    }
    public Integer getContractId() {
        return id;
    }
    public void setContractId(Integer contractId) {
        this.id = contractId;
    }
    public ContractStatus getStatus() {
        return status;
    }
    public void setStatus(ContractStatus status) {
        this.status = status;
    }
}
