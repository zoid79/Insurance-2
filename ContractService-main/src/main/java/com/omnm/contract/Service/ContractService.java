package com.omnm.contract.Service;

import com.omnm.contract.DAO.ContractDAO;
import com.omnm.contract.DTO.ContractList;
import com.omnm.contract.DTO.GetContractAndInsuranceTypeByIdResponse;
import com.omnm.contract.Entity.Contract;
import com.omnm.contract.enumeration.ContractStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class ContractService implements ContractServiceIF{
    @Autowired
    ContractDAO contractDao;
    @Override
    public ResponseEntity<ContractList> getContractListByContractStatus(ContractStatus status) {
        long beforeTime = System.currentTimeMillis();
        List<Contract> contractList = this.contractDao.findContractByContractStatus(status);
        if(contractList.isEmpty()) return new ResponseEntity<>(new ContractList(contractList), new HttpHeaders(), HttpStatus.valueOf(404));
        long afterTime = System.currentTimeMillis();
        long secDiffTime = (afterTime - beforeTime)/1000;
        if(secDiffTime>=7) new ResponseEntity<>(contractList, new HttpHeaders(), HttpStatus.valueOf(500));
        return new ResponseEntity<>(new ContractList(contractList), new HttpHeaders(), HttpStatus.valueOf(200));
    }
    @Override
    public ResponseEntity<ContractList> getContractListByCustomerId(String customerId) {
        long beforeTime = System.currentTimeMillis();
        ArrayList<Contract> contractList = new ArrayList<>();
        for(Contract contract : this.contractDao.findContract()){
            if(contract.getCustomerId().equals(customerId)) contractList.add(contract);
        }
        if(contractList.isEmpty()) new ResponseEntity<>(new ContractList(contractList), new HttpHeaders(), HttpStatus.valueOf(404));
        long afterTime = System.currentTimeMillis();
        long secDiffTime = (afterTime - beforeTime)/1000;
        if(secDiffTime>=7) new ResponseEntity<>(contractList, new HttpHeaders(), HttpStatus.valueOf(500));
        return new ResponseEntity<>(new ContractList(contractList), new HttpHeaders(), HttpStatus.valueOf(200));
    }
    @Override
    public ResponseEntity<ContractList> getContractListByCustomerIdAndContractStatus(String customerId, ContractStatus status) {
        long beforeTime = System.currentTimeMillis();
        List<Contract> contractList = new ArrayList<>();
        for(Contract contract : this.contractDao.findContractByContractStatus(status)){
            if(contract.getCustomerId().equals(customerId)) contractList.add(contract);
        }
        if(contractList.isEmpty()) new ResponseEntity<>(new ContractList(contractList), new HttpHeaders(), HttpStatus.valueOf(404));
        long afterTime = System.currentTimeMillis();
        long secDiffTime = (afterTime - beforeTime)/1000;
        if(secDiffTime>=7) new ResponseEntity<>(contractList, new HttpHeaders(), HttpStatus.valueOf(500));
        return new ResponseEntity<>(new ContractList(contractList), new HttpHeaders(), HttpStatus.valueOf(200));
    }

    @Override
    public ResponseEntity<ContractList> getUnpaidContractListByCustomerId(String customerId) {
        long beforeTime = System.currentTimeMillis();
        List<Contract> contractList = this.contractDao.findContractByCustomerId(customerId);
        List<Contract> unpaidContractList = new ArrayList<>();
        if(contractList.isEmpty())  new ResponseEntity<>(new ContractList(contractList), new HttpHeaders(), HttpStatus.valueOf(404));
        Timestamp now=new Timestamp(System.currentTimeMillis());
        for(Contract contract:contractList) {
            if(contract.getContractStatus()==ContractStatus.Conclude){
                Timestamp deadlineStamp=contract.getPaymentDeadline();
                LocalDateTime deadline = deadlineStamp.toLocalDateTime();
                LocalDateTime nowTime = now.toLocalDateTime();
                long daysDifference = ChronoUnit.DAYS.between(nowTime,deadline);
                if(daysDifference<=7) unpaidContractList.add(contract);
            }
        }
        if(unpaidContractList.isEmpty()) new ResponseEntity<>(new ContractList(contractList), new HttpHeaders(), HttpStatus.valueOf(404));
        long afterTime = System.currentTimeMillis();
        long secDiffTime = (afterTime - beforeTime)/1000;
        if(secDiffTime>=7) new ResponseEntity<>(new ContractList(contractList), new HttpHeaders(), HttpStatus.valueOf(500));
        return new ResponseEntity<>(new ContractList(contractList), new HttpHeaders(), HttpStatus.valueOf(200));
    }
    @Override
    public ResponseEntity<Contract> getContractById(Integer id) {
        Contract contract = this.contractDao.findContractById(id);
        if(contract == null){ new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.valueOf(404));}
        return new ResponseEntity<>(contract, new HttpHeaders(), HttpStatus.valueOf(200));
    }
    @Override
    public ResponseEntity<Integer> postContract(Contract contract) {
        return new ResponseEntity<>(this.contractDao.createContract(contract), new HttpHeaders(), HttpStatus.valueOf(200));
    }
    public GetContractAndInsuranceTypeByIdResponse getContractAndInsuranceTypeById(Integer id) {
        Contract contract = this.contractDao.findContractById(id);
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9999")
                .path("insurance/insurance/type/" + contract.getInsuranceId().toString())
                .encode()
                .build()
                .toUri();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> requestMessage = new HttpEntity<>(httpHeaders);
        HttpEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, requestMessage, String.class);
        return new GetContractAndInsuranceTypeByIdResponse(contract, response.getBody());
    }
    @Override
    public ResponseEntity<Boolean> patchStartDateAndExpirationDateAndDeadlineAndContractStatusInContractById(Integer id) {
        Contract contract = this.contractDao.findContractById(id);
        if(contract == null) new ResponseEntity<>(false, new HttpHeaders(), HttpStatus.valueOf(404));
        Timestamp startDate = new Timestamp(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(startDate.getTime());
        cal.add(Calendar.YEAR, contract.getTerm().getYear());
        Timestamp expirationDate = new Timestamp(cal.getTime().getTime());
        cal.setTimeInMillis(startDate.getTime());
        cal.add(Calendar.WEEK_OF_MONTH, 1);
        Timestamp deadline = new Timestamp(cal.getTime().getTime());
        return new ResponseEntity<>(
                this.contractDao.updateStartDateAndExpirationDateAndDeadlineAndContractStatusInContractById(id,startDate,expirationDate,deadline, ContractStatus.Conclude),
                new HttpHeaders(), HttpStatus.valueOf(200)
        );
    }

    @Override
    public ResponseEntity<Boolean> patchContractStatusInContractById(Integer id, ContractStatus status) {
        return new ResponseEntity<>(contractDao.updateContractStatusInContractById(id, status), new HttpHeaders(), HttpStatus.valueOf(200));
    }

    @Override
    public ResponseEntity<Boolean> patchDeadlineInContractById(Integer id, Timestamp deadline) {
        return new ResponseEntity<>(contractDao.updateDeadlineInContractById(id, deadline), new HttpHeaders(), HttpStatus.valueOf(200));
    }
}
