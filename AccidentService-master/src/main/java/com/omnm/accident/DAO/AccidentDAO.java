package com.omnm.accident.DAO;//package dao;



import com.omnm.accident.DTO.Contract;
import com.omnm.accident.Entity.Accident;
import com.omnm.accident.Repository.AccidentRepository;
import com.omnm.accident.enumeration.accident.AccidentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public class AccidentDAO {
    @Autowired
    private AccidentRepository accidentRepository;

    public AccidentDAO(AccidentRepository accidentRepository) {
        this.accidentRepository = accidentRepository;
    }

    public boolean updateStatusInAccidentById(int id, AccidentStatus status) {
        Optional<Accident> accidents  = accidentRepository.findById(id);
        if(accidents.get().equals(Optional.empty())) return false;
        Accident accident = accidents.get();
        accident.setStatus(status);
        accidentRepository.save(accident);
        if(accidentRepository.save(accident)==null) return false;
        return true;
    }
    public int createAccident(Accident accident) { // createAccident
        return accidentRepository.save(accident).getId();
    }
    public ArrayList<Accident> findAccidentByStatus(AccidentStatus accidentStatus) {
        return accidentRepository.findByStatus(accidentStatus);
    }

    public Accident findAccidentById(int id) { //findAccidentById
        if(accidentRepository.findById(id).equals(Optional.empty()))return null;
       return accidentRepository.findById(id).get();
    }

    public ArrayList<Accident> findAccidentByContract(Contract contract) {
        return accidentRepository.findByContractId(contract.getId());
    }

    public ArrayList<Accident> findAll() {
        return (ArrayList<Accident>) accidentRepository.findAll();
    }
}
