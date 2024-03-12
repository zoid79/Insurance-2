package com.omnm.accident.Repository;


import com.omnm.accident.Entity.Accident;
import com.omnm.accident.enumeration.accident.AccidentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AccidentRepository extends JpaRepository<Accident,Integer> {
    ArrayList<Accident> findByStatus(AccidentStatus status);

    ArrayList<Accident> findByContractId(Integer contractId);
    //   Accident findById(int id);

}