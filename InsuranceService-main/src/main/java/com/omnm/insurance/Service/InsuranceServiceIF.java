package com.omnm.insurance.Service;

import com.omnm.insurance.DTO.InsuranceList;
import com.omnm.insurance.enumeration.InsuranceType;
import com.omnm.insurance.enumeration.InsuranceStatus;
import com.omnm.insurance.Entity.Insurance;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InsuranceServiceIF {

    ResponseEntity<InsuranceList> getInsuranceList();

    ResponseEntity<InsuranceList> getInsuranceListByInsuranceStatus(InsuranceStatus insuranceStatus);

    ResponseEntity<InsuranceList> getInsuranceListByInsuranceTypeAndInsuranceStatus(InsuranceType type, InsuranceStatus status);

    ResponseEntity<Insurance> getInsuranceById(Integer selectedInsuranceId);

    ResponseEntity<Integer> postInsurance(Insurance insurance);

    ResponseEntity<Boolean> patchInsuranceStatusInInsuranceById(Integer id, InsuranceStatus status);

    ResponseEntity<Boolean> getInsuranceByName(String name);

    ResponseEntity<InsuranceType> getInsuranceTypeById(Integer id);
}
