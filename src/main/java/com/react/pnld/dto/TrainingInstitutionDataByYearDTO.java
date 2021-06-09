package com.react.pnld.dto;

import java.io.Serializable;

public class TrainingInstitutionDataByYearDTO implements Serializable {

    private int year;
    private int institutionNumberPNLD;
    private float percentageInstitutions;
    private float percentageFirstTimeInPNLD;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getInstitutionNumberPNLD() {
        return institutionNumberPNLD;
    }

    public void setInstitutionNumberPNLD(int institutionNumberPNLD) {
        this.institutionNumberPNLD = institutionNumberPNLD;
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
        return "TrainingInstitutionDataByYearDTO{" +
                "year=" + year +
                ", institutionNumberPNLD=" + institutionNumberPNLD +
                ", percentageInstitutions=" + percentageInstitutions +
                ", percentageFirstTimeInPNLD=" + percentageFirstTimeInPNLD +
                '}';
    }
}
