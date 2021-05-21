package com.react.pnld.controller.response;

import java.io.Serializable;

public class TrainingInfoRegion implements Serializable {

    private int id;
    private String name;
    private int numberInstitutionsInPNLD;
    private float percentageInstitutions;
    private float percentageFirstTimeInPNLD;

    public TrainingInfoRegion(){
        super();
    }

    public TrainingInfoRegion(int id, String name, int numberInstitutionsInPNLD, float percentageInstitutions,
                              float percentageFirstTimeInPNLD) {
        super();
        this.id = id;
        this.name = name;
        this.numberInstitutionsInPNLD = numberInstitutionsInPNLD;
        this.percentageInstitutions = percentageInstitutions;
        this.percentageFirstTimeInPNLD = percentageFirstTimeInPNLD;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberInstitutionsInPNLD() {
        return numberInstitutionsInPNLD;
    }

    public void setNumberInstitutionsInPNLD(int numberInstitutionsInPNLD) {
        this.numberInstitutionsInPNLD = numberInstitutionsInPNLD;
    }

    public float getPercentageInstitutions() {
        return percentageInstitutions;
    }

    public void setPercentageInstitutions(float percentageInstitutions) {
        this.percentageInstitutions = percentageInstitutions;
    }

    public float getPercentageFirstTimeInPNLD() {
        return percentageFirstTimeInPNLD;
    }

    public void setPercentageFirstTimeInPNLD(float percentageFirstTimeInPNLD) {
        this.percentageFirstTimeInPNLD = percentageFirstTimeInPNLD;
    }

    @Override
    public String toString() {
        return "TrainingInfoRegion{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberInstitutionsInPNLD=" + numberInstitutionsInPNLD +
                ", percentageInstitutions=" + percentageInstitutions +
                ", percentageFirstTimeInPNLD=" + percentageFirstTimeInPNLD +
                '}';
    }
}
