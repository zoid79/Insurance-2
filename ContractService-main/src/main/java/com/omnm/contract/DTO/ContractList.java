package com.omnm.contract.DTO;

import com.omnm.contract.Entity.Contract;

import java.util.List;


public class ContractList {
    List<Contract> contractList;

    public ContractList(List<Contract> contractList) {
        this.contractList = contractList;
    }

    public List<Contract> getContractList() {
        return contractList;
    }

    public void setContractList(List<Contract> contractList) {
        this.contractList = contractList;
    }
}