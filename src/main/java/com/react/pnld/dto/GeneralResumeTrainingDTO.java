package com.react.pnld.dto;

import com.univocity.parsers.annotations.BooleanString;
import com.univocity.parsers.annotations.Parsed;

public class GeneralResumeTrainingDTO {

    @Parsed(field = {"Rut", "RUT"})
    private String rut;

    @Parsed(field = {"Region", "REGION"})
    private int regionId;

    @Parsed(field = {"Rbd", "RBD"})
    private String rbd;

    @Parsed(field = {"Nombres", "NOMBRES"})
    private String names;

    @Parsed(field = {"Asiste Jornada", "ASISTE JORNADA"})
    @BooleanString(trueStrings = {"si", "SI"}, falseStrings = {"no", "NO"})
    private boolean attendsInPerson;

    @Parsed(field = {"anno capacitacion", "Anno Capacitacion,ANNO CAPACITACION"})
    private int trainingYear;

    @Parsed(field = {"estado final", "Estado Final", "ESTADO FINAL"})
    @BooleanString(trueStrings = {"aprobado", "Aprobado", "APROBADO"}, falseStrings = {"reprobado", "Reprobado", "REPROBADO"})
    private boolean isApproved;

    public GeneralResumeTrainingDTO(){
        super();
    }

    public GeneralResumeTrainingDTO(String rut, int regionId, String rbd, String names, boolean attendsInPerson,
                                    int trainingYear, boolean isApproved) {
        this.rut = rut;
        this.regionId = regionId;
        this.rbd = rbd;
        this.names = names;
        this.attendsInPerson = attendsInPerson;
        this.trainingYear = trainingYear;
        this.isApproved = isApproved;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public String getRbd() {
        return rbd;
    }

    public void setRbd(String rbd) {
        this.rbd = rbd;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public boolean isAttendsInPerson() {
        return attendsInPerson;
    }

    public void setAttendsInPerson(boolean attendsInPerson) {
        this.attendsInPerson = attendsInPerson;
    }

    public int getTrainingYear() {
        return trainingYear;
    }

    public void setTrainingYear(int trainingYear) {
        this.trainingYear = trainingYear;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}
