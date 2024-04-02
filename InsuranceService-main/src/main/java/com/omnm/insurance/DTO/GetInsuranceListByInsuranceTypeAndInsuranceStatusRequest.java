package com.omnm.insurance.DTO;

import com.omnm.insurance.enumeration.InsuranceStatus;
import com.omnm.insurance.enumeration.InsuranceType;

public class GetInsuranceListByInsuranceTypeAndInsuranceStatusRequest {
    InsuranceType type;
    InsuranceStatus status;
    public GetInsuranceListByInsuranceTypeAndInsuranceStatusRequest(InsuranceType type, InsuranceStatus status) {
        this.status = status;
        this.type = type;
    }
    public InsuranceType getType() {
        return type;
    }
    public void setType(InsuranceType type) {
        this.type = type;
    }
    public InsuranceStatus getStatus() {
        return status;
    }
    public void setStatus(InsuranceStatus status) {
        this.status = status;
    }
}
