package com.omnm.accident.Service;


import com.omnm.accident.DAO.AccidentDAO;
import com.omnm.accident.DTO.AccidentList;
import com.omnm.accident.DTO.Contract;
import com.omnm.accident.DTO.ContractList;
import com.omnm.accident.Entity.Accident;
import com.omnm.accident.configuration.Constants;
import com.omnm.accident.enumeration.accident.AccidentStatus;
import com.omnm.accident.exception.EmptyListException;
import com.omnm.accident.exception.TimeDelayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
@EnableDiscoveryClient
public class AccidentService implements AccidentServiceIF{
    @Autowired
    AccidentDAO accidentDao;


    @Override
    public ResponseEntity<AccidentList> getAccidentListByStatus(AccidentStatus status) {
        long beforeTime = System.currentTimeMillis();
        ArrayList<Accident> accidents = this.accidentDao.findAccidentByStatus(status);
        AccidentList accidentList = new AccidentList(accidents);
        try{
            if(accidentList.getAccidentList().size()==0) new EmptyListException("");
            long afterTime = System.currentTimeMillis();
            long secDiffTime = (afterTime - beforeTime)/1000;
            if(secDiffTime>=7) new TimeDelayException("");
        }catch (Exception e){
            return new ResponseEntity<>(null,new HttpHeaders(),HttpStatus.valueOf(500));
        }


        return new ResponseEntity<>(accidentList,new HttpHeaders(),HttpStatus.valueOf(200));
    }
    @Override
    public ResponseEntity<AccidentList> getAccidentListByCustomerId(int customerId) {
        long beforeTime = System.currentTimeMillis();
        ArrayList<Accident> accidents = new ArrayList<Accident>();
        for(Contract contract:this.getContractList(customerId).getContractList()){
            ArrayList<Accident> accidentList = accidentDao.findAccidentByContract(contract);
            if(accidentList.size()!=0) accidents.addAll(accidentList);
        }
        AccidentList accidentList = new AccidentList(accidents);
        try{
            if(accidentList.getAccidentList().size()==0)  new EmptyListException("");
            long afterTime = System.currentTimeMillis();
            long secDiffTime = (afterTime - beforeTime)/1000;
            if(secDiffTime>=7)new TimeDelayException("");
        }catch (Exception e){
            return new ResponseEntity<>(null,new HttpHeaders(),HttpStatus.valueOf(200));
        }

        return new ResponseEntity<>(accidentList,new HttpHeaders(),HttpStatus.valueOf(200));
    }

    private ContractList getContractList(int customerId) {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = UriComponentsBuilder
                .fromUriString(Constants.BASE_URL)
                .path(Constants.CONTRACT_SERVICE_URL+Constants.LIST_URL+Constants.GET_CONTRACTS_URL + customerId)
                .encode()
                .build()
                .toUri();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity requestEntity = new HttpEntity(header);
        ResponseEntity<ContractList> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, ContractList.class);
        return result.getBody();
    }

    @Override
    public ResponseEntity<Accident> getAccidentById(int id){
        Accident accident = this.accidentDao.findAccidentById(id);
        if(accident == null) return new ResponseEntity<>(accident,new HttpHeaders(),HttpStatus.valueOf(500));
        return new ResponseEntity<>(accident,new HttpHeaders(),HttpStatus.valueOf(200));
    }
    @Override
    public ResponseEntity<Integer> postAccident(Accident accident){
        return new ResponseEntity<>(this.accidentDao.createAccident(accident),new HttpHeaders(),HttpStatus.valueOf(200));
    }

    @Override
    public ResponseEntity<Boolean> patchStatusById(int accidentId, AccidentStatus status){
        return new ResponseEntity<>(this.accidentDao.updateStatusInAccidentById(accidentId,status),new HttpHeaders(),HttpStatus.valueOf(200));
    }
}
