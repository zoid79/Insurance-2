package com.omnm.accident.DTO;

import com.omnm.accident.Entity.Accident;

import java.util.ArrayList;

public class AccidentList {
    ArrayList<Accident> accidentList;

    public AccidentList(ArrayList<Accident> accidentList) {
        this.accidentList = accidentList;
    }

    public ArrayList<Accident> getAccidentList() {
        return accidentList;
    }

    public void setAccidentList(ArrayList<Accident> accidentList) {
        this.accidentList = accidentList;
    }
}
