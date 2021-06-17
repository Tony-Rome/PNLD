package com.react.pnld.dto;

import java.io.Serializable;

public class TrainingIndicatorInstitutionData implements Serializable {

    private int year;
    private int institutionNumberPNLD;
    private float percentageInstitutions;
    private int firstTimeInstitutionNumber;

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

    public int getFirstTimeInstitutionNumber() {
        return firstTimeInstitutionNumber;
    }

    public void setFirstTimeInstitutionNumber(int firstTimeInstitutionNumber) {
        this.firstTimeInstitutionNumber = firstTimeInstitutionNumber;
    }

    @Override
    public String toString() {
        return "TrainingIndicatorInstitutionData{" +
                "year=" + year +
                ", institutionNumberPNLD=" + institutionNumberPNLD +
                ", percentageInstitutions=" + percentageInstitutions +
                ", firstTimeInstitutionNumber=" + firstTimeInstitutionNumber +
                '}';
    }
}
