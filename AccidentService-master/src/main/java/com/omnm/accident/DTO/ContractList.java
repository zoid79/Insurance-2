package com.omnm.accident.DTO;


import java.util.List;


public class ContractList {
    List<Contract> contractList;

    public ContractList(List<Contract> contractList) {
        this.contractList = contractList;
    }

    public ContractList() {
    }

    public List<Contract> getContractList() {
        return contractList;
    }

    public void setContractList(List<Contract> contractList) {
        this.contractList = contractList;
    }
}