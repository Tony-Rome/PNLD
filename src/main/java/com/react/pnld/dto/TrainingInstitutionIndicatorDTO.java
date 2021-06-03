package com.react.pnld.dto;

import java.io.Serializable;

public class TrainingInstitutionIndicatorDTO implements Serializable {

    private int id;
    private int year;
    private String regionName;
    private int institutionNumberPNLD;
    private float percentageInstitutions;
    private float percentageFirstTimeInPNLD;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
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
        return "TrainingInstitutionIndicatorDTO{" +
                "year=" + year +
                ", regionName='" + regionName + '\'' +
                ", institutionNumberPNLD=" + institutionNumberPNLD +
                ", percentageInstitutions=" + percentageInstitutions +
                ", percentageFirstTimeInPNLD=" + percentageFirstTimeInPNLD +
                '}';
    }
}
