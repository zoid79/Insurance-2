package com.omnm.insurance.Service;

import com.omnm.insurance.DTO.InsuranceList;
import com.omnm.insurance.enumeration.InsuranceStatus;
import com.omnm.insurance.enumeration.InsuranceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.omnm.insurance.DAO.InsuranceDAO;
import com.omnm.insurance.Entity.Insurance;

import java.util.ArrayList;
import java.util.List;

@Service
public class InsuranceService implements InsuranceServiceIF {
    @Autowired
    InsuranceDAO insuranceDAO;
    @Override
    public ResponseEntity<InsuranceList> getInsuranceList() {
        long beforeTime = System.currentTimeMillis();
        List<Insurance> insuranceList = this.insuranceDAO.findInsurance();
        if(insuranceList.isEmpty()) return new ResponseEntity<>(new InsuranceList(insuranceList), new HttpHeaders(), HttpStatus.valueOf(404));

        long afterTime = System.currentTimeMillis();
        long secDiffTime = (afterTime - beforeTime)/1000;
        if(secDiffTime>=7) return new ResponseEntity<>(new InsuranceList(insuranceList), new HttpHeaders(), HttpStatus.valueOf(500));
        return new ResponseEntity<>(new InsuranceList(insuranceList), new HttpHeaders(), HttpStatus.valueOf(200));
    }
    @Override
    public ResponseEntity<InsuranceList> getInsuranceListByInsuranceStatus(InsuranceStatus insuranceStatus) {
        long beforeTime = System.currentTimeMillis();
        List<Insurance> insuranceList = this.insuranceDAO.findByStatus(insuranceStatus);
        if(insuranceList.isEmpty()) return new ResponseEntity<>(new InsuranceList(insuranceList), new HttpHeaders(), HttpStatus.valueOf(404));
        long afterTime = System.currentTimeMillis();
        long secDiffTime = (afterTime - beforeTime)/1000;
        if(secDiffTime>=7) return new ResponseEntity<>(new InsuranceList(insuranceList), new HttpHeaders(), HttpStatus.valueOf(500));
        return new ResponseEntity<>(new InsuranceList(insuranceList), new HttpHeaders(), HttpStatus.valueOf(200));
    }
    @Override
    public ResponseEntity<InsuranceList> getInsuranceListByInsuranceTypeAndInsuranceStatus(InsuranceType type, InsuranceStatus status) {
        long beforeTime = System.currentTimeMillis();
        ArrayList<Insurance> insuranceList = new ArrayList<>();
        for(Insurance insurance : this.insuranceDAO.findByStatus(status)){
            if(insurance.getType()==type) insuranceList.add(insurance);
        }
        if(insuranceList.isEmpty()) return new ResponseEntity<>(new InsuranceList(insuranceList), new HttpHeaders(), HttpStatus.valueOf(404));

        long afterTime = System.currentTimeMillis();
        long secDiffTime = (afterTime - beforeTime)/1000;
        if(secDiffTime>=7) return new ResponseEntity<>(new InsuranceList(insuranceList), new HttpHeaders(), HttpStatus.valueOf(500));

        return new ResponseEntity<>(new InsuranceList(insuranceList), new HttpHeaders(), HttpStatus.valueOf(200));
    }
    @Override
    public ResponseEntity<Insurance> getInsuranceById(Integer selectedInsuranceId) {
        Insurance insurance = this.insuranceDAO.findInsuranceById(selectedInsuranceId);
        if(insurance == null) return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.valueOf(404));
        return new ResponseEntity<>(insurance, new HttpHeaders(), HttpStatus.valueOf(200));
    }
    @Override
    public ResponseEntity<Integer> postInsurance(Insurance insurance) {
        Insurance findByNameInsurance = this.insuranceDAO.findInsuranceByName(insurance.getName());
        if(findByNameInsurance != null) return new ResponseEntity<>(insurance.getId(), new HttpHeaders(), HttpStatus.valueOf(500));
        else insuranceDAO.createInsurance(insurance);
        return new ResponseEntity<>(insurance.getId(), new HttpHeaders(), HttpStatus.valueOf(200));
    }
    @Override
    public ResponseEntity<Boolean> patchInsuranceStatusInInsuranceById(Integer id, InsuranceStatus status) {
        Insurance insurance = this.insuranceDAO.findInsuranceById(id);
        insurance.setStatus(status);
        this.insuranceDAO.updateInsuranceStatusInInsuranceById(id, status);
        return new ResponseEntity<>(insurance.getStatus() == status, new HttpHeaders(), HttpStatus.valueOf(200));
    }
    @Override
    public ResponseEntity<Boolean> getInsuranceByName(String name) {
        Insurance findByNameInsurance = this.insuranceDAO.findInsuranceByName(name);
        if(findByNameInsurance != null) return new ResponseEntity<>(false, new HttpHeaders(), HttpStatus.valueOf(500));
        return new ResponseEntity<>(true, new HttpHeaders(), HttpStatus.valueOf(200));
    }
    @Override
    public ResponseEntity<InsuranceType> getInsuranceTypeById(Integer id) {
        Insurance insurance = this.insuranceDAO.findInsuranceById(id);
        return  new ResponseEntity<>(insurance.getType(), new HttpHeaders(), HttpStatus.valueOf(200));
    }
}
