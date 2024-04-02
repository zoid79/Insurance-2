package com.omnm.insurance.Controller;


import com.omnm.insurance.DTO.GetInsuranceListByInsuranceTypeAndInsuranceStatusRequest;
import com.omnm.insurance.DTO.InsuranceList;
import com.omnm.insurance.Entity.Insurance;
import com.omnm.insurance.enumeration.InsuranceStatus;
import com.omnm.insurance.Service.InsuranceService;
import com.omnm.insurance.enumeration.InsuranceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class InsuranceController {
    @Autowired
    InsuranceService insuranceService;
    @GetMapping("/insurances")
    public ResponseEntity<InsuranceList> getInsuranceList() {
        return this.insuranceService.getInsuranceList();
    }
    @GetMapping("/insurances/{status}")
    public ResponseEntity<InsuranceList> getInsuranceListByInsuranceStatus(@PathVariable InsuranceStatus status) {
        return this.insuranceService.getInsuranceListByInsuranceStatus(status);
    }
    @GetMapping("/insurances/by-type-and-status")
    public ResponseEntity<InsuranceList> getInsuranceListByInsuranceTypeAndInsuranceStatus(
            @RequestBody GetInsuranceListByInsuranceTypeAndInsuranceStatusRequest
                    getInsuranceListByInsuranceTypeAndInsuranceStatusRequest) {
        return this.insuranceService.getInsuranceListByInsuranceTypeAndInsuranceStatus(
                getInsuranceListByInsuranceTypeAndInsuranceStatusRequest.getType(),
                getInsuranceListByInsuranceTypeAndInsuranceStatusRequest.getStatus());
    }
    @GetMapping("/insurance/{selectedInsuranceId}")
    public ResponseEntity<Insurance> getInsuranceById(@PathVariable Integer selectedInsuranceId) {
        return this.insuranceService.getInsuranceById(selectedInsuranceId);
    }
    @PostMapping("/insurance")
    public ResponseEntity<Integer> postInsurance(@RequestBody Insurance insurance) {
        return this.insuranceService.postInsurance(insurance);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PatchMapping("/authorization")
    public ResponseEntity<Boolean> patchInsuranceStatusInInsuranceById(@RequestBody HashMap<String, String> param) {
        Integer id = Integer.valueOf(param.get("id"));
        InsuranceStatus status = InsuranceStatus.valueOf(param.get("status"));
        return this.insuranceService.patchInsuranceStatusInInsuranceById(id, status);
    }
    @GetMapping("/check-name")
    public ResponseEntity<Boolean> getInsuranceByName(@Param("name") String name) {
        return this.insuranceService.getInsuranceByName(name);
    }
    @GetMapping("/insurance/type/{id}")
    public ResponseEntity<InsuranceType> getInsuranceTypeById(@PathVariable Integer id) {
        return this.insuranceService.getInsuranceTypeById(id);
    }
}
