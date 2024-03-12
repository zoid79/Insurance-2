package com.omnm.accident.DTO;

import com.omnm.accident.enumeration.accident.AccidentStatus;

public class PatchStatusByIdRequest {
    int accidentId;
    AccidentStatus status;

    public PatchStatusByIdRequest(int accidentId, AccidentStatus accidentStatus) {
        this.accidentId = accidentId;
        this.status = accidentStatus;
    }

    public int getAccidentId() {
        return accidentId;
    }

    public void setAccidentId(int accidentId) {
        this.accidentId = accidentId;
    }

    public AccidentStatus getStatus() {
        return status;
    }

    public void setStatus(AccidentStatus status) {
        this.status = status;
    }


}
