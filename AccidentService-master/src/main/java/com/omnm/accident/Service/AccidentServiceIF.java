package com.omnm.accident.Service;



import com.omnm.accident.DTO.AccidentList;
import com.omnm.accident.Entity.Accident;
import com.omnm.accident.enumeration.accident.AccidentStatus;
import org.springframework.http.ResponseEntity;

import java.rmi.Remote;

public interface AccidentServiceIF extends Remote{
    ResponseEntity<AccidentList> getAccidentListByStatus(AccidentStatus status);

    ResponseEntity<AccidentList> getAccidentListByCustomerId(int customerId);

    ResponseEntity<Accident> getAccidentById(int id);

    ResponseEntity<Integer> postAccident(Accident accident);

    ResponseEntity<Boolean> patchStatusById(int accidentId, AccidentStatus status);
}
