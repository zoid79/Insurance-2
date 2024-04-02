package com.omnm.insurance.DTO;

import com.omnm.insurance.Entity.Insurance;

import java.util.List;

public class InsuranceList {
    List<Insurance> insuranceList;
    public InsuranceList(List<Insurance> insuranceList) {
        this.insuranceList = insuranceList;
    }
    public List<Insurance> getInsuranceList() {
        return insuranceList;
    }
    public void setInsuranceList(List<Insurance> insuranceList) {
        this.insuranceList = insuranceList;
    }
}
