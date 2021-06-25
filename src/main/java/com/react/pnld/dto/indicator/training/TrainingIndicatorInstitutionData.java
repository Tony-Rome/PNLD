package com.react.pnld.dto.indicator.training;

import java.io.Serializable;

public class TrainingIndicatorInstitutionData implements Serializable {

    private int year;
    private int institutionCounterPNLD;
    private float percentageInstitutions;
    private int firstTimeInstitutionCounter;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getInstitutionCounterPNLD() {
        return institutionCounterPNLD;
    }

    public void setInstitutionCounterPNLD(int institutionCounterPNLD) {
        this.institutionCounterPNLD = institutionCounterPNLD;
    }

    public float getPercentageInstitutions() {
        return percentageInstitutions;
    }

    public void setPercentageInstitutions(float percentageInstitutions) {
        this.percentageInstitutions = percentageInstitutions;
    }

    public int getFirstTimeInstitutionCounter() {
        return firstTimeInstitutionCounter;
    }

    public void setFirstTimeInstitutionCounter(int firstTimeInstitutionCounter) {
        this.firstTimeInstitutionCounter = firstTimeInstitutionCounter;
    }

    @Override
    public String toString() {
        return "TrainingIndicatorInstitutionData{" +
                "year=" + year +
                ", institutionCounterPNLD=" + institutionCounterPNLD +
                ", percentageInstitutions=" + percentageInstitutions +
                ", firstTimeInstitutionCounter=" + firstTimeInstitutionCounter +
                '}';
    }
}
